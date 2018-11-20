package com.numus.budgee;


public class Transaction {

    private String name;
    private Double quantity;

    protected enum Category{
        DEFAULT,
        PET,
        PAYROLL,
        HEALTH,
        CLOTHES,
        SERVICES,
        TAXES,
        FOOD,
        GROCERIES
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    private Category category = Category.DEFAULT;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;


    public Transaction(){

    }

    public Transaction(String name, Double quantity, String date, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.date = date;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }



}
