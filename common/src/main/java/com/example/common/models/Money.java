package com.example.common.models;

import java.util.Objects;

public class Money {
    private double amount;
    private String description;
    private String date;

    public Money(double amount, String description, String date) {
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public Money() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Double.compare(amount, money.amount) == 0 && Objects.equals(description, money.description) && Objects.equals(date, money.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, description, date);
    }
}
