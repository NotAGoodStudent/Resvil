package com.resvil.api.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prodID;
    private String prodName;
    private String prodDesc;
    private float prodPrice;
    private float prodRating;

    public Product(int prodID, String prodName, String prodDesc, Float prodPrice)
    {
        this.prodID = prodID;
        this.prodName = prodName;
        this.prodDesc = prodDesc;
        this.prodPrice = prodPrice;
    }

    public Product() {

    }

    public int getProdID() {
        return prodID;
    }

    public void setProdID(int prodID) {
        this.prodID = prodID;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }


    public Float getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(Float prodPrice) {
        this.prodPrice = prodPrice;
    }

    public float getProdRating() {
        return prodRating;
    }

    public void setProdRating(int prodRating) {
        this.prodRating = prodRating;
    }

}
