package com.example.PAOProiect;

import com.example.PAOProiect.csv.CsvService;
import com.example.PAOProiect.dao.ProductDao;
import com.example.PAOProiect.model.DiscountedProduct;
import com.example.PAOProiect.model.LastChanceItem;
import com.example.PAOProiect.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CsvTest {
	@Test
	public void readCsv() throws IOException {
		String path = "src\\main\\resources\\data\\download.csv";
		String[] HEADERS = { "name", "age"};
		List<String[]> rows = new CsvService().readAllFromCsv(path, HEADERS);
		for (String[] row : rows) {
			for (String element : row) {
				System.out.print(element + " ");
			}
			System.out.println();
		}
	}
	@Test
	public void writeToCsv() throws IOException {
		String path = "src\\main\\resources\\data\\download.csv";
		List<String[]> rows = new ArrayList<>();
		rows.add(new String[]{"John", "11"});
		rows.add(new String[]{"Jane Smith", "10"});
		rows.add(new String[]{"Tom Johnson", "22"});
		new CsvService().writeToCsv(path, rows);
	}
}

@SpringBootTest
class ProductDaoTest{
	@Autowired
	ProductDao productDao;

	@Test
	public void testSelectProducts() {
		List<Product> list = productDao.selectProducts();
		list.forEach(System.out::println);
	}

	@Test
	void testSelectProductById() {
		Product testProduct = new Product(100, "Test Product", 10.99, "Test Description", "Test Category", "Test Condition");
		long generatedId = productDao.insertProduct(testProduct);
		Product retrievedProduct = productDao.selectProductById(generatedId);
		productDao.deleteProductById(generatedId);

		assertNotNull(retrievedProduct);
		assertEquals(testProduct.getName(), retrievedProduct.getName());
		assertEquals(testProduct.getPrice(), retrievedProduct.getPrice());
		assertEquals(testProduct.getDescription(), retrievedProduct.getDescription());
		assertEquals(testProduct.getCategory(), retrievedProduct.getCategory());
		assertEquals(testProduct.getCondition(), retrievedProduct.getCondition());
	}

	@Test
	void testDeleteProductById() {
		// Insert a test product
		Product testProduct = new Product(100, "Test Product", 10.99, "Test Description", "Test Category", "Test Condition");
		long productId = productDao.insertProduct(testProduct);
		int rowsAffected = productDao.deleteProductById(productId);
		assertEquals(1, rowsAffected);
	}

	@Test
	public void testInsertDiscountedProduct() {
		DiscountedProduct discountedProduct = new DiscountedProduct();
		discountedProduct.setName("Test Discounted Product");
		discountedProduct.setPrice(20.99);
		discountedProduct.setDescription("Test Description");
		discountedProduct.setCategory("Test Category");
		discountedProduct.setCondition("Test Condition");
		discountedProduct.setDiscount(0.1); // 10% discount

		long generatedId = productDao.insertProduct(discountedProduct);
		System.out.println(generatedId);
		productDao.deleteProductById(generatedId);
	}

	@Test
	public void testUpdateProduct() {
		LastChanceItem lastChanceItem = new LastChanceItem();
		lastChanceItem.setId(1L);
		lastChanceItem.setName("Updated LastChance Product");
		lastChanceItem.setPrice(15.99);
		lastChanceItem.setDescription("Updated Description");
		lastChanceItem.setCategory("Updated Category");
		lastChanceItem.setCondition("Updated Condition");
		lastChanceItem.setLimitedStock(5); // Set limited stock specific to LastChanceItem

		int rowsAffected = productDao.updateProduct(lastChanceItem);
		assertEquals(1, rowsAffected);
	}
}
