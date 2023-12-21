package edu.upc.dsa.models;

public class CredencialesRegistro {
    private String username;
    private String password;
    private String email;

    public CredencialesRegistro(){

    }

    public CredencialesRegistro(String usrname, String pswd, String email){
        this.email = email;
        this.password = pswd;
        this.username = usrname;
    }
    public String getUsername(){return this.username;}
    public String getPassword(){return this.password;}
    public String getEmail(){return this.email;}
    public void setUsername(String usrname){this.username = usrname;}
    public void setPassword(String pswd){this.password = pswd;}
    public void setEmail(String mail){this.email = mail;}
}
