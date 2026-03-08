package com.tjl.dao;

import com.tjl.bean.User;
import com.tjl.jdbc.JDBCUtils;

import java.sql.*;

public class UserDao_Imp implements UserDao{
    private static final String SQL_LOGIN="select type from user where uname=? and upass=?";
    private static final String SQL_INSERT="insert into user values(id,?,?,2)";
    private static final String SQL_DELETE="delete u1,w1 " +
            "from user u1 join work w1 on u1.uname=w1.uname " +
            "where u1.uname=?";
    private static final String CREATE_VIEW="create view select_all " +
            "as select u1.*,w1.position " +
            "from user u1 join work w1 on u1.uname=w1.uname " +
            "where u1.uname=?";
    private static final String SQL_SELECT="select * from select_all";
    private static final String CREATE_TRIGGER_SQL = "CREATE TRIGGER log_user_changes " +
                    "AFTER INSERT ON user " + "FOR EACH ROW " + "BEGIN " +
                    "   INSERT INTO work(uname, position) " +
                    "   VALUES(NEW.uname, '服务员'); " + "END";
    private static final String CREATE_PROCEDURE_SQL="CREATE PROCEDURE update_user_upass(" +
            "    IN uname1 VARCHAR(100)," + "    IN new_upass VARCHAR(100) )" +
            "BEGIN" + "    UPDATE user u1 join work w1 on u1.uname=w1.uname " +
            "    SET u1.upass = new_upass " + "    WHERE u1.uname = uname1;" + "END";

    @Override
    public int login(User user) {
        Connection conn= JDBCUtils.getConnection();
        PreparedStatement preparedStatement=null;
        ResultSet result=null;
        try {
            preparedStatement= conn.prepareStatement(SQL_LOGIN);
            preparedStatement.setString(1, user.getUname());
            preparedStatement.setString(2, user.getUpass());
            result=preparedStatement.executeQuery();
            while(result.next()){
                int type=result.getInt("type");
                return type;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn,preparedStatement,result);
        }
        return -1;
    }

    @Override
    public boolean insert(User user) {
        Connection conn= JDBCUtils.getConnection();
        PreparedStatement preparedStatement=null;
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.execute("DROP TRIGGER IF EXISTS log_user_changes");
            statement.execute(CREATE_TRIGGER_SQL);
            preparedStatement= conn.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, user.getUname());
            preparedStatement.setString(2, user.getUpass());
            int line=preparedStatement.executeUpdate();
            if(line>0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn,preparedStatement,null);
            JDBCUtils.close(conn,statement,null);
        }

        return false;
    }

    @Override
    public boolean delete(String uname) throws SQLException {
        Connection conn= JDBCUtils.getConnection();
        PreparedStatement preparedStatement=null;
        try {
            conn.setAutoCommit(false);
            preparedStatement= conn.prepareStatement(SQL_DELETE);
            preparedStatement.setString(1, uname);
            int line=preparedStatement.executeUpdate();
            if(line>0){
                conn.commit();
                return true;
            }
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("事务回滚");
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn,preparedStatement,null);
        }

        return false;
    }

    @Override
    public boolean update(User user) {
        Connection conn= JDBCUtils.getConnection();
        CallableStatement callableStatement=null;
        Statement statement = null;
        try {

            statement = conn.createStatement();
            statement.execute("DROP PROCEDURE IF EXISTS update_user_upass");
            statement.execute(CREATE_PROCEDURE_SQL);
            callableStatement=conn.prepareCall("{call update_user_upass(?, ?)}");
            callableStatement.setString(1, user.getUname());
            callableStatement.setString(2, user.getUpass());
            boolean line=callableStatement.execute();
            if(!line){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn,callableStatement,null);
            JDBCUtils.close(conn,statement,null);
        }
        return false;
    }

    @Override
    public User select(String uname) {
        Connection conn= JDBCUtils.getConnection();
        PreparedStatement preparedStatement=null;
        Statement statement=null;
        ResultSet result=null;
        try {
            preparedStatement= conn.prepareStatement(CREATE_VIEW);
            preparedStatement.setString(1, uname);
            preparedStatement.executeUpdate();
            statement=conn.createStatement();
            result=statement.executeQuery(SQL_SELECT);
            while(result.next()){
                int id= result.getInt("id");
                String name=result.getString("uname");
                String upass=result.getString("upass");
                int type=result.getInt("type");
                String position=result.getString("position");
                System.out.println("用户id:"+id);
                System.out.println("用户名:"+name);
                System.out.println("用户密码:"+upass);
                if(type==1){
                    System.out.println("用户类型:老板");
                }else {
                    System.out.println("用户类型:员工");
                }
                System.out.println("用户职位:"+position);
                return new User(id,type,name,upass);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn,preparedStatement,result);
        }
        return null;
    }

}
