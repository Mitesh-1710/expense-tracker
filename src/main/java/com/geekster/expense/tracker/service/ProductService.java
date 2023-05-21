package com.geekster.expense.tracker.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.geekster.expense.tracker.model.Product;

@Service
public interface ProductService {

	public ResponseEntity<Object> saveProduct(Product product , String token);

	public ResponseEntity<Object> updateProductById(Product product, Long productId, String token);

	public ResponseEntity<Object> deleteProductById(Long productId, String token);

	public ResponseEntity<Object> getProductById(Long productId, String token);

	public ResponseEntity<Object> getTotalExpenseByMonth(Integer month, String token);

	public ResponseEntity<Object> getAllProductsByDate(String date, String token);

	public ResponseEntity<Object> generateReportByMonth(Integer month, String token);

}
