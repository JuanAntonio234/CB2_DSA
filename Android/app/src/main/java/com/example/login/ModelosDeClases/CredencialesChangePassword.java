package com.example.login.ModelosDeClases;

public class CredencialesChangePassword {

    String username;
    String password;
    String newPassword;

    public CredencialesChangePassword(String username, String password, String newPassword) {
        this.username = username;
        this.password = password;
        this.newPassword = newPassword;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
