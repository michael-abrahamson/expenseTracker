package com.michaelabrahamson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Expense {
    private int id;
    private String description;
    private double amount;
    private String date;

    @JsonCreator
    public Expense(
        @JsonProperty("ID") int id,
        @JsonProperty("description") String description,
        @JsonProperty("amount") double amount,
        @JsonProperty("date") String date
    ) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }

}
