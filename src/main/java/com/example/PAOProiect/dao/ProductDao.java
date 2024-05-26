package com.example.PAOProiect.dao;

import com.example.PAOProiect.dao.rowMapper.ProductRowMapper;
import com.example.PAOProiect.model.DiscountedProduct;
import com.example.PAOProiect.model.LastChanceItem;
import com.example.PAOProiect.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

@Service
public class ProductDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Product")
                .usingGeneratedKeyColumns("id");
    }
    public List<Product> selectProducts() {
        var sql = """
                SELECT *
                FROM Product
                LIMIT 100;
                 """;
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    public Product selectProductById(long id) {
        var sql = """
                SELECT *
                FROM Product
                WHERE id = ?
                """;
        return jdbcTemplate.queryForObject(sql, new ProductRowMapper(), id);
    }

    public int deleteProductById(long id) {
        var sql = "DELETE FROM Product WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public long insertProduct(Product product) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", product.getName());
        parameters.put("price", product.getPrice());
        parameters.put("description", product.getDescription());
        parameters.put("category", product.getCategory());
        parameters.put("condition", product.getCondition());

        if (product instanceof DiscountedProduct discountedProduct) {
            parameters.put("discount", discountedProduct.getDiscount());
            parameters.put("discriminator", "DISCOUNTED_PRODUCT");
        } else if (product instanceof LastChanceItem lastChanceItem) {
            parameters.put("limitedStock", lastChanceItem.getLimitedStock());
            parameters.put("discriminator", "LAST_CHANCE_ITEM");
        } else {
            parameters.put("discriminator", "PRODUCT");
        }

        Number generatedId = simpleJdbcInsert
                .executeAndReturnKey(parameters);
        return generatedId.longValue(); // Return the generated ID
    }

    public int updateProduct(Product product) {
        String sql;
        Object[] parameters;

        if (product instanceof DiscountedProduct discountedProduct) {
            sql = "UPDATE Product SET name = ?, price = ?, description = ?, category = ?, condition = ?, discount = ? WHERE id = ?";
            parameters = new Object[]{discountedProduct.getName(), discountedProduct.getPrice(), discountedProduct.getDescription(), discountedProduct.getCategory(), discountedProduct.getCondition(), discountedProduct.getDiscount(), discountedProduct.getId()};
        } else if (product instanceof LastChanceItem lastChanceItem) {
            sql = "UPDATE Product SET name = ?, price = ?, description = ?, category = ?, condition = ?, limitedStock = ? WHERE id = ?";
            parameters = new Object[]{lastChanceItem.getName(), lastChanceItem.getPrice(), lastChanceItem.getDescription(), lastChanceItem.getCategory(), lastChanceItem.getCondition(), lastChanceItem.getLimitedStock(), lastChanceItem.getId()};
        } else {
            sql = "UPDATE Product SET name = ?, price = ?, description = ?, category = ?, condition = ? WHERE id = ?";
            parameters = new Object[]{product.getName(), product.getPrice(), product.getDescription(), product.getCategory(), product.getCondition(), product.getId()};
        }

        return jdbcTemplate.update(sql, parameters);
    }

}


