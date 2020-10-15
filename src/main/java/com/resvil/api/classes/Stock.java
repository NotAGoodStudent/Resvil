package com.resvil.api.classes;

import javax.persistence.*;

@Entity
public class Stock
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int prodPurchaseId;
    @OneToOne
    Product prod;
    int quantity;


    public Stock() {
    }

    public int getProdPurchaseId() {
        return prodPurchaseId;
    }

    public void setProdPurchaseId(int prodPurchaseId) {
        this.prodPurchaseId = prodPurchaseId;
    }

    public Product getProd() {
        return prod;
    }

    public void setProd(Product prod) {
        this.prod = prod;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
