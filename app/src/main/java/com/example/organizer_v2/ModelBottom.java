package com.example.organizer_v2;

public class ModelBottom {
    private int idB;
    private String nameB, tagsB;
    private byte[] imageB;

    public ModelBottom(int i, String n, String t, byte[] img){ //id, name, tag, image)
        this.idB = i;
        this.nameB = n;
        this.tagsB = t;
        this.imageB = img;
    }

    public int getIdB() {return idB;}
    public String getNameB() {return nameB;}
    public String getTagsB() {return tagsB;}
    public byte[] getImageB() {return imageB;}
}
