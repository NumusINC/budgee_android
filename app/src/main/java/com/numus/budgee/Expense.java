package com.numus.budgee;

import java.util.Date;

public class Expense {
    public String name;
    public double value;
    public Date date;
    public String type;
    public String token;
    public boolean isIncome;

    public Expense(String name, double value, Date date, String type, String token, boolean isIncome) {
        this.name = name;
        this.value = value;
        this.date = date;
        this.type = type;
        this.token = token;
        this.isIncome = isIncome;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getToken() {
        return token;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
