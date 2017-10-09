package com.example.lcassiol.android_phonebook;

import java.io.Serializable;

/**
 * Created by lcass on 10/8/2017.
 */

public class Contact implements Serializable {
    private String name;
    private String email;
    private String site;
    private String phone;
    private String adress;
    private String photo;

    private Long id;

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", site='" + site + '\'' +
                ", phone='" + phone + '\'' +
                ", adress='" + adress + '\'' +
                ", photo='" + photo + '\'' +
                ", name=" + name +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSite() {
        return site;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhoto() {
        return photo;
    }

    public Long getId() {
        return id;
    }
}
