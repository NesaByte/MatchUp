package com.example.organizer_v2;

public class Model_matched {
    private int id;
    private String name_m;//,nameTOP, nameBOTTOM, tagsTOP, tagsBOTTOM;
    private byte[] image_t, image_b;
//(id, name_m, image_t, image_b)
    public Model_matched(int id_m,
                         String name_m,
                         byte[] img_t,
                         byte[] img_b
                        )
    {
        this.id         = id_m;
        this.name_m     = name_m;
        this.image_t    = img_t;
        this.image_b    = img_b;
    }


    public int getIdMATCH(){return id;}
    public String getNameMATCHED() {return name_m;}
    //public String getNameTOP() {return nameTOP;}
    //public String getNameBOTTOM() {return nameBOTTOM;}
    //public String getTagsTOP() {return tagsTOP;}
    //public String getTagsBOTTOM() {return tagsBOTTOM;}
    public byte[] getImageTOP() {return image_t;}
    public byte[] getImageBOTTOM() {return image_b;}

}
