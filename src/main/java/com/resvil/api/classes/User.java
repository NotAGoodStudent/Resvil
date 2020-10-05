package com.resvil.api.classes;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userID;
    String name;
    String surname;
    String email;
    String password;
    String role;
    float credit;
    @ManyToMany(cascade=CascadeType.ALL)
    List<Sale> boughtProducts = new ArrayList<>();
    @ManyToMany(cascade=CascadeType.ALL)
    List<Product> cart = new ArrayList<>();

    public User()
    {

    }
    public User(int userID, String name, String surname, String email, String password, String role)
    {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getUserIDId() {
        return userID;
    }

    public void setUserIDId(int id) {
        this.userID = id;
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

    public List<Sale> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(List<Sale> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }
}
