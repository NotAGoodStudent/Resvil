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
    @ElementCollection
    List<Integer> soldProd;
    @ManyToOne(cascade = CascadeType.ALL)
    User buyer;
    LocalDateTime ldt;
    boolean prodArrived;

    public Sale(int saleID, List<Integer> soldProd, User buyer)
    {
        this.saleID = saleID;
        this.soldProd = soldProd;

    }

    public Sale() {

    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public List<Integer> getSoldProd() {
        return soldProd;
    }

    public void setSoldProd(List<Integer> soldProd) {
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
