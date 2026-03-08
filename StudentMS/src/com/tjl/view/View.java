package com.tjl.view;

import com.tjl.bean.User;
import com.tjl.bean.Work;

import java.util.Scanner;

public class View {
    private static Scanner input=new Scanner(System.in);

    public static User indexView(){
        System.out.println("**************************************");
        System.out.println("*****"+"\t\t员工信息管理系统\t\t"+"*****");
        System.out.println("*****"+"\t\t请根据提示操作\t\t"+"*****");
        System.out.println("*****"+"\t\t请输入账号\t\t"+"*****");
        String uname=input.nextLine();
        System.out.println("*****"+"\t\t请输入密码\t\t"+"*****");
        String upass=input.nextLine();
        System.out.println("**************************************");
        return new User(uname,upass);
    }

    public static int managerMenuView(){
        System.out.println("**************************************");
        System.out.println("*****"+"\t\t欢迎老板回家\t\t"+"*****");
        System.out.println("*****"+"\t\t请根据提示操作\t\t"+"*****");
        System.out.println("*****"+"\t\t0退出\t\t"+"*****");
        System.out.println("*****"+"\t\t1添加员工信息\t\t"+"*****");
        System.out.println("*****"+"\t\t2删除员工信息\t\t"+"*****");
        System.out.println("*****"+"\t\t3修改员工信息\t\t"+"*****");
        System.out.println("*****"+"\t\t4查询员工信息\t\t"+"*****");
        String type=input.nextLine();
        System.out.println("**************************************");
        int item=Integer.parseInt(type);
        if(item<0||item>4){
            System.out.println("输入错误,请重新输入");
            return managerMenuView();
        }
        return item;
    }

    public static User addMenuView(){
        System.out.println("**************************************");
        System.out.println("\t\t请根据提示操作\t\t");
        System.out.println("\t\t请输入添加的账号\t\t");
        String uname=input.nextLine();
        System.out.println("\t\t请输入添加的密码\t\t");
        String upass=input.nextLine();
        System.out.println("**************************************");
        return new User(uname,upass);
    }

    public static String deleteMenuView(){
        System.out.println("**************************************");
        System.out.println("\t\t请根据提示操作\t\t");
        System.out.println("\t\t请输入要删除的账号\t\t");
        String uname=input.nextLine();
        System.out.println("**************************************");
        return uname;
    }

    public static User updateMenuView(){
        System.out.println("**************************************");
        System.out.println("\t\t请根据提示操作\t\t");
        System.out.println("\t\t请输入旧账号\t\t");
        String uname=input.nextLine();
        System.out.println("\t\t请输入更新的密码\t\t");
        String upass=input.nextLine();
        System.out.println("**************************************");
        return new User(uname,upass);
    }

    public static String selectMenuView(){
        System.out.println("**************************************");
        System.out.println("\t\t请根据提示操作\t\t");
        System.out.println("\t\t请输入要查询的账号\t\t");
        String uname=input.nextLine();
        System.out.println("**************************************");
        return uname;
    }



}
