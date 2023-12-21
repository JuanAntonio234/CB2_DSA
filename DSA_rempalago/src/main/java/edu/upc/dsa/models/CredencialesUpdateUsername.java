package edu.upc.dsa.models;

public class CredencialesUpdateUsername {
    String username;
    String newUsername;
    String password;

    public CredencialesUpdateUsername(){

    }
    public CredencialesUpdateUsername(String username, String newUsername, String password) {
        this.username = username;
        this.newUsername = newUsername;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
