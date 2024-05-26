package com.example.PAOProiect.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Getter
@Setter
public class Product {
    private long id;
    private String name;
    private double price;
    private String description;
    private String category;
    private String condition;

    public Product() {
    }

    public Product(int id, String name, double price, String description, String category, String condition) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.condition = condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return id == product.id && Double.compare(product.price, price) == 0 && name.equals(product.name) && description.equals(product.description) && category.equals(product.category) && condition.equals(product.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, description, category, condition);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", condition='" + condition + '\'' +
                '}';
    }
}