package com.resvil.api.classes;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Sale
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int saleID;
    int quantity;
    @OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    Product soldProd;
    @OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    User buyer;
    LocalDateTime ldt;
    boolean prodArrived;

    public Sale(int saleID, int quantity, Product soldProd, User buyer, LocalDateTime ldt){}
    {
        this.saleID = saleID;
        this.quantity = quantity;
        this.soldProd = soldProd;
        this.buyer = buyer;
        this.ldt = ldt;
    }

    public Sale() {

    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getSoldProd() {
        return soldProd;
    }

    public void setSoldProd(Product soldProd) {
        this.soldProd = soldProd;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public LocalDateTime getLdt() {
        return ldt;
    }

    public void setLdt(LocalDateTime ldt) {
        this.ldt = ldt;
    }

    public boolean isProdArrived() {
        return prodArrived;
    }

    public void setProdArrived(boolean prodArrived) {
        this.prodArrived = prodArrived;
    }
}
