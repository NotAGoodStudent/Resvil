package com.resvil.api.classes;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class User
{
    String name;
    String surname;
    @Id
    String email;
    String password;
    String role;
    float credit;
    @ManyToMany(cascade=CascadeType.ALL)
    @Column(nullable = false)
    List<Sale> tickets = new ArrayList<>();
    @ManyToMany(cascade=CascadeType.ALL)
    @Column(nullable = false)
    List<PurchaseQuantity> cart = new ArrayList<>();

    public User()
    {

    }
    public User(String name, String surname, String email, String password, String role)
    {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public List<Sale> getTickets() {
        return tickets;
    }

    public void setTickets(List<Sale> tickets) {
        this.tickets = tickets;
    }

    public List<PurchaseQuantity> getCart() {
        return cart;
    }

    public void setCart(List<PurchaseQuantity> cart) {
        this.cart = cart;
    }
}
