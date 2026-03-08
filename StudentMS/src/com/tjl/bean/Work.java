package com.tjl.bean;

public class Work {
    private String uname;
    private String position;

    public Work() {
    }
    public Work(String uname) {
        this.uname = uname;
    }
    public Work(String position, String uname) {
        this.position = position;
        this.uname = uname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
