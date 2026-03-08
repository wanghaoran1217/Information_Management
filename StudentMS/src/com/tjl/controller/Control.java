package com.tjl.controller;

import com.tjl.bean.User;
import com.tjl.dao.UserDao_Imp;
import com.tjl.view.View;

public class Control {
    public static void main(String[] args) throws Exception{
        while(true){
            User user= View.indexView();
            UserDao_Imp userDaoImp=new UserDao_Imp();
            int type=userDaoImp.login(user);
            switch (type){
                case -1:
                    System.out.println("输入错误,请重新输入");
                    break;
                case 1:
                    System.out.println("老板登录成功");
                    managerServer();
                    break;
                case 2:
                    System.out.println("员工登录成功");
                default:
                    break;
            }
        }

    }

    private static void managerServer() throws Exception{
        UserDao_Imp userDaoImp=new UserDao_Imp();
        while(true){
            int item=View.managerMenuView();
            boolean flag;
            switch (item){
                case 0:
                    System.exit(-1);
                    break;
                case 1:
                    User user=View.addMenuView();
                    flag=userDaoImp.insert(user);
                    System.out.println(flag?"添加成功":"添加失败");
                    break;
                case 2:
                    String uname=View.deleteMenuView();
                    flag=userDaoImp.delete(uname);
                    System.out.println(flag?"删除成功":"删除失败");
                    break;
                case 3:
                    User updateUser=View.updateMenuView();
                    flag=userDaoImp.update(updateUser);
                    System.out.println(flag?"更新成功":"更新失败");
                    break;
                case 4:
                    String uname1=View.selectMenuView();
                    userDaoImp.select(uname1);
                    break;
                default:
                    break;
            }
        }
    }

}
