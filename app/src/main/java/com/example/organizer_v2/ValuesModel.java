package com.example.organizer_v2;

import java.io.Serializable;

public class ValuesModel implements Serializable {

    private String name, tags, photos;



    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getTags() {
        return tags;
    }

    public void setTags(java.lang.String tags) {
        this.tags = tags;
    }

    public java.lang.String getPhotos() {
        return photos;
    }

    public void setPhotos(java.lang.String photos) {
        this.photos = photos;
    }
}
