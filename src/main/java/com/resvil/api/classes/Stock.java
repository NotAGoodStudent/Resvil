package com.resvil.api.classes;

import javax.persistence.*;

@Entity
public class Stock
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int prodID;
    @OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    Product product;
    int prodStock;


    public Stock(int prodID, Product product, int prodStock)
    {
        this.prodID = prodID;
        this.product = product;
        this.prodStock = prodStock;
    }

    public Stock() {

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProdStock() {
        return prodStock;
    }

    public void setProdStock(int prodStock) {
        this.prodStock = prodStock;
    }

    public int getProdID() {
        return prodID;
    }

    public void setProdID(int prodID) {
        this.prodID = prodID;
    }
}
