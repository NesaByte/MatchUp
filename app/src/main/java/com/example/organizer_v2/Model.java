package com.example.organizer_v2;

public class Model {
    private int id;
    private String name, tags;
    private byte[] image;

    public Model(int i, String n, String t, byte[] img){ //id, name, tag, image)
        this.id = i;
        this.name = n;
        this.tags = t;
        this.image = img;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public String getTags() {return tags;}
    public byte[] getImage() {return image;}
}
