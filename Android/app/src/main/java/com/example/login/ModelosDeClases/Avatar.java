package com.example.login.ModelosDeClases;

public class Avatar {
    String nombre;
    int idArma;
    int health;
    int damg;
    int visible;    // Visible "0" Invisible "1"
    int speed;

    // Constructores
    public Avatar(){}
    public Avatar(String nombre, int idArma, int health, int damg, int speed){
        this();
        this.nombre = nombre;
        this.idArma = idArma;
        this.health = health;
        this.damg = damg;
        this.speed = speed;
        this.visible = 0;
    }
    public String getNombre(){return this.nombre;}
    // Setters y Getters
    public int getIdArma(){return this.idArma;}
    public void setIdArma(int idArma){this.idArma = idArma;}
    public int getHealth(){return this.health;}
    public void setHealth(int life){this.health = life;}
    public int getDamg(){return this.damg;}
    public void setDamg(int damg){this.damg = damg;}
    public int getSpeed(){return this.speed;}
    public void setSpeed(int speed){this.speed = speed;}
    public int getVisible(){return this.visible;}
    public void setVisible(int visible){this.visible = visible;}
}
