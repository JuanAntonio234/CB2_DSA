package edu.upc.dsa.models;

public class CredencialesUpdatePassword {
    String username;
    String password;
    String newPassword;

    public CredencialesUpdatePassword(){

    }
    public CredencialesUpdatePassword(String username, String password, String newPassword) {
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
