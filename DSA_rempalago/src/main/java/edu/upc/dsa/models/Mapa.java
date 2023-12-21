package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Mapa {
    String id;
    // Constructor
    public Mapa(){this.id = RandomUtils.getId();}
    public String getId(){return this.id;}
}
