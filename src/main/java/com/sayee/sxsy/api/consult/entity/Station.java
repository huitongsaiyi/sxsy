package com.sayee.sxsy.api.consult.entity;

import java.io.Serializable;

/**
 * @Description
 */
public class Station implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String person;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
