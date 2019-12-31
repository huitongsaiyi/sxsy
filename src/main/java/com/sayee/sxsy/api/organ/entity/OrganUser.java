package com.sayee.sxsy.api.organ.entity;

import java.io.Serializable;

/**
 * @Description
 */
public class OrganUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String photo;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
