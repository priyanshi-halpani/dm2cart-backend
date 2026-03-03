package com.appDost.DM2Cart.dto;

public class LoginRequest {
    private String mobile; // can send mobile or username
    private String password;

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
