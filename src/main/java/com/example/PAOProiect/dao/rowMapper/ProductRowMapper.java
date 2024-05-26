package com.example.PAOProiect.dao.rowMapper;

import com.example.PAOProiect.model.DiscountedProduct;
import com.example.PAOProiect.model.LastChanceItem;
import com.example.PAOProiect.model.Product;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        String discriminator = rs.getString("discriminator");
        if ("DISCOUNTED_PRODUCT".equals(discriminator)) {
            DiscountedProduct discountedProduct = new DiscountedProduct();
            discountedProduct.setId(rs.getLong("id"));
            discountedProduct.setName(rs.getString("name"));
            discountedProduct.setPrice(rs.getDouble("price"));
            discountedProduct.setDescription(rs.getString("description"));
            discountedProduct.setCategory(rs.getString("category"));
            discountedProduct.setCondition(rs.getString("condition"));
            discountedProduct.setDiscount(rs.getDouble("discount"));
            return discountedProduct;
        } else if ("LAST_CHANCE_ITEM".equals(discriminator)) {
            LastChanceItem lastChanceItem = new LastChanceItem();
            lastChanceItem.setId(rs.getLong("id"));
            lastChanceItem.setName(rs.getString("name"));
            lastChanceItem.setPrice(rs.getDouble("price"));
            lastChanceItem.setDescription(rs.getString("description"));
            lastChanceItem.setCategory(rs.getString("category"));
            lastChanceItem.setCondition(rs.getString("condition"));
            lastChanceItem.setLimitedStock(rs.getInt("limitedStock"));
            return lastChanceItem;
        } else {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setDescription(rs.getString("description"));
            product.setCategory(rs.getString("category"));
            product.setCondition(rs.getString("condition"));
            return product;
        }
    }
}
