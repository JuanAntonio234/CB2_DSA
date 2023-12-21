package edu.upc.dsa.models;

public class Credenciales {

    private String username;
    private String password;


    public Credenciales(){
    }

    public Credenciales(String username,  String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
}

