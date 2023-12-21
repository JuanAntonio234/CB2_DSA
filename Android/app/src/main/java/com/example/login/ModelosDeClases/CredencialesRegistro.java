package com.example.login.ModelosDeClases;

public class CredencialesRegistro {
    private String username;
    private String email;
    private String password;

    public CredencialesRegistro(String username, String password, String mail) {
        this.username = username;
        this.password = password;
        this.email = mail;
    }
    public String getUsername(){return this.username;}
    public String getPassword(){return this.password;}
    public String getEmail(){return this.email;}
    public void setUsername(String username){this.username = username;}
    public void setPassword(String password){this.password = password;}
    public void setEmail(String mail){this.email = mail;}
}
