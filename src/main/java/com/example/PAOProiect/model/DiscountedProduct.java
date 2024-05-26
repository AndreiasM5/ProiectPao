package com.example.PAOProiect.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Objects;

@Getter
@Setter
public class DiscountedProduct extends Product {
    private double discount;

    public DiscountedProduct() {
    }

    public DiscountedProduct(int id, String name, double price, String description, String category, String condition, double discount) {
        super(id, name, price, description, category, condition);
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscountedProduct that)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(that.discount, discount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discount);
    }

    @Override
    public String toString() {
        return "DiscountedProduct{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", description='" + getDescription() + '\'' +
                ", category='" + getCategory() + '\'' +
                ", condition='" + getCondition() + '\'' +
                ", discount=" + discount +
                '}';
    }

}