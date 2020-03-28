package com.example.organizer_v2;

public class Model_matched {
    private int id_m;
    private String name_m;//,nameTOP, nameBOTTOM, tagsTOP, tagsBOTTOM;
    private byte[] image_t, image_b;

    //mmList.add(new Model_matched(id, name_m, image_t, image_b));

    public Model_matched(int i,
                         String n,
                         byte[] t,
                         byte[] b
                        )
    {
        this.id_m       = i;
        this.name_m     = n;
        this.image_t    = t;
        this.image_b    = b;
    }

    public int getId_m() {
        return id_m;
    }

    public String getName_m() {
        return name_m;
    }

    public byte[] getImage_t() {
        return image_t;
    }

    public byte[] getImage_b() {
        return image_b;
    }

}
