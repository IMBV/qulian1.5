package com.quliantrip.qulian.domain;

/**
 * Created by Qulian5 on 2016/1/15.
 */
public class Test {
    public Test(boolean ischeck, String name) {
        this.ischeck = ischeck;
        this.name = name;
    }

    private boolean ischeck;
    private String name;

    public boolean ischeck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
