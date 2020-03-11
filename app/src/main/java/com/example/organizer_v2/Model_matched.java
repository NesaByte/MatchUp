package com.example.organizer_v2;

public class Model_matched {
    private int idMATCH;
    private String nameMATCHED;//,nameTOP, nameBOTTOM, tagsTOP, tagsBOTTOM;
    private byte[] imageTOP, imageBOTTOM;

    public Model_matched(int id_m, String name_m,
                         //String name_t, String tags_t,
                         byte[] img_t,
                         //String name_b, String tags_b,
                         byte[] img_b
                        )
    {
        this.idMATCH    = id_m;    this.nameMATCHED = name_m;
        //this.nameTOP    = name_t;   this.tagsTOP    = tags_t;
        this.imageTOP    = img_t;
        //this.nameBOTTOM = name_b;   this.tagsBOTTOM = tags_b;
        this.imageBOTTOM = img_b;
    }


    public int getIdMATCH(){return idMATCH;}
    public String getNameMATCHED() {return nameMATCHED;}
    //public String getNameTOP() {return nameTOP;}
    //public String getNameBOTTOM() {return nameBOTTOM;}
    //public String getTagsTOP() {return tagsTOP;}
    //public String getTagsBOTTOM() {return tagsBOTTOM;}
    public byte[] getImageTOP() {return imageTOP;}
    public byte[] getImageBOTTOM() {return imageBOTTOM;}

}
