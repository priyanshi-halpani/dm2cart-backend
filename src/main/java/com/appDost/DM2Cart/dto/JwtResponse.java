package com.appDost.DM2Cart.dto;

public class JwtResponse {
    private boolean success;
    private boolean otpSent;    // NEW → for USER OTP flow

    private String token;
    private String username;
    private String role;
    private String message;

    public JwtResponse() {}

    // SUCCESS with token (admin/seller OR user after OTP verification)
    public static JwtResponse success(String token, String username, String role){
        JwtResponse r = new JwtResponse();
        r.success = true;
        r.otpSent = false;
        r.token = token;
        r.username = username;
        r.role = role;
        r.message = "Login successful";
        return r;
    }

    // OTP sent response (user login step 1)
    public static JwtResponse otpSent(String mobile){
        JwtResponse r = new JwtResponse();
        r.success = true;
        r.otpSent = true;
        r.message = "OTP sent to " + mobile;
        return r;
    }

    // FAILURE
    public static JwtResponse failure(String message){
        JwtResponse r = new JwtResponse();
        r.success = false;
        r.otpSent = false;
        r.message = message;
        return r;
    }

    // getters
    public boolean isSuccess() { return success; }
    public boolean isOtpSent() { return otpSent; }
    public String getToken() { return token; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public String getMessage() { return message; }

    // setters
    public void setSuccess(boolean success) { this.success = success; }
    public void setOtpSent(boolean otpSent) { this.otpSent = otpSent; }
    public void setToken(String token) { this.token = token; }
    public void setUsername(String username) { this.username = username; }
    public void setRole(String role) { this.role = role; }
    public void setMessage(String message) { this.message = message; }
}
