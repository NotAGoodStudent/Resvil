package com.resvil.api.classes;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Sale
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int saleID;
    @OneToMany(cascade=CascadeType.ALL)
    List<Product> soldProd;
    @OneToMany(cascade=CascadeType.ALL)
    List<ProdPurchaseQuantity> purchaseQuantity;
    @OneToOne(cascade=CascadeType.ALL)
    User buyer;
    LocalDateTime ldt;
    boolean prodArrived;

    public Sale(int saleID, Product soldProd, User buyer, LocalDateTime ldt){}
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

    public List<Product> getSoldProd() {
        return soldProd;
    }

    public void setSoldProd(List<Product> soldProd) {
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
