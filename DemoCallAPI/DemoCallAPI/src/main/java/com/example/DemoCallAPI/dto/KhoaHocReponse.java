package com.example.DemoCallAPI.dto;

import jdk.jfr.DataAmount;

import java.io.Serializable;



public class KhoaHocReponse implements Serializable {
    private int id;
    private String name;
    private String description;
    private double oldPrice;
    private double price;
    private String image;

    public KhoaHocReponse() {
    }

    public KhoaHocReponse(int id, String name, String description, double oldPrice, double price, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.oldPrice = oldPrice;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "KhoaHocReponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", oldPrice=" + oldPrice +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
