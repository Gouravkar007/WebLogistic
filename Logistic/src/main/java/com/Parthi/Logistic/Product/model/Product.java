package com.Parthi.Logistic.Product.model;


public class Product {
    

    private String id;

    private String category;

    private String name;

    private String description;

    private double costPrice = 0;

    private double sellingPrice = 0;

    private double mrp; 


    public Product() {
    }

    public Product(String id, String category, String name, String description, double costPrice, double sellingPrice, double mrp) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.mrp = mrp;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCostPrice() {
        return this.costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSellingPrice() {
        return this.sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getMrp() {
        return this.mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }
    

}

