package com.openclassrooms.dataLayer.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/***
 "Created by Moinahadidja Mohamed Chakir on "15/10/2022
 */

@Entity
@Table(name="categorie")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="categorie_id")
    private int categoryId;

    @Column(name="nom")
    private String name;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},
                  fetch = FetchType.LAZY)
    @JoinTable(name = "categorie_produit",
               joinColumns =@JoinColumn(name= "categorie_id"),
                inverseJoinColumns = @JoinColumn(name = "produit_id"))

    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProduct(Product product) {
        products.add(product);
        product.getCategories().add(this);
    }

    public void removeProduit(Product product) {
        products.remove(product);
        product.getCategories().remove(this);
    }

}
