package com.tjl.bean;

public class User {
    private int id;
    private String uname;
    private String upass;
    private int type;//1 老板  2 员工

    public User() {
    }
    public User(String uname, String upass) {
        this.uname = uname;
        this.upass = upass;
    }
    public User(int type, String uname, String upass) {
        this.type = type;
        this.uname = uname;
        this.upass = upass;
    }
    public User(int id, int type, String uname, String upass) {
        this.id = id;
        this.type = type;
        this.uname = uname;
        this.upass = upass;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", upass='" + upass + '\'' +
                ", type=" + type +
                '}';
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getUpass() {
        return upass;
    }
    public void setUpass(String upass) {
        this.upass = upass;
    }
}
