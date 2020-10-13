package com.resvil.api.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProdPurchaseQuantity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int prodPurchaseId;
    int prodID;
    int quantity;


    public ProdPurchaseQuantity() {
    }

    public int getProdPurchaseId() {
        return prodPurchaseId;
    }

    public void setProdPurchaseId(int prodPurchaseId) {
        this.prodPurchaseId = prodPurchaseId;
    }

    public int getProdID() {
        return prodID;
    }

    public void setProdID(int prodID) {
        this.prodID = prodID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
