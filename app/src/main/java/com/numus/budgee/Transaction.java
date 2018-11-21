package com.numus.budgee;


public class Transaction {

    private String name;
    private Double quantity;
    private String date;
    private Category category = Category.DEFAULT;

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

    public Transaction(String name, Double quantity, String date, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.date = date;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public String getDate(){
        return date;
    }

}
