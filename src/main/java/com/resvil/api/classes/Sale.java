package com.resvil.api.classes;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sale
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int saleID;
    @OneToMany(cascade=CascadeType.ALL)
    List<PurchaseQuantity> soldProd;
    @OneToOne(cascade=CascadeType.ALL)
    User buyer;
    LocalDateTime ldt;
    boolean prodArrived;

    public Sale(int saleID, List<PurchaseQuantity> soldProd, User buyer, LocalDateTime ldt)
    {
        this.saleID = saleID;
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

    public List<PurchaseQuantity> getSoldProd() {
        return soldProd;
    }

    public void setSoldProd(List<PurchaseQuantity> soldProd) {
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
