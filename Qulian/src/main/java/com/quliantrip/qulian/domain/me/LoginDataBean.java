package com.quliantrip.qulian.domain.me;

import java.util.List;

/**
 * 用户登录的信息
 */
public class LoginDataBean {

    private int id;
    private String username;
    private String auth_key;
    private String mobile;
    private String email;
    private int code;
    private int status;
    private List<String> password;

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuth_key(String auth_key) {
        this.auth_key = auth_key;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPassword(List<String> password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public int getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }

    public List<String> getPassword() {
        return password;
    }
}
