package com.example.PAOProiect.model;

import lombok.*;
import java.util.Objects;

@Getter
@Setter
public class LastChanceItem extends Product {
    private int limitedStock;

    public LastChanceItem() {
    }

    public LastChanceItem(int id, String name, double price, String description, String category, String condition, int limitedStock) {
        super(id, name, price, description, category, condition);
        this.limitedStock = limitedStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LastChanceItem that)) return false;
        if (!super.equals(o)) return false;
        return limitedStock == that.limitedStock;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), limitedStock);
    }

    @Override
    public String toString() {
        return "LastChanceItem{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", description='" + getDescription() + '\'' +
                ", category='" + getCategory() + '\'' +
                ", condition='" + getCondition() + '\'' +
                ", limitedStock=" + limitedStock +
                '}';
    }
}