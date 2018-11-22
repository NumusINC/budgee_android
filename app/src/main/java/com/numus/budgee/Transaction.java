package com.numus.budgee;

public class Transaction {

    private String name, date, type;
    private Double quantity;
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
        GROCERIES,
        FUN
    }

    public Transaction(String name, Double quantity, String date, Category category, String type) {
        this.name = name;
        this.quantity = quantity;
        this.date = date;
        this.category = category;
        this.type = type;
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

    public String getType() {
        return type;
    }
}
