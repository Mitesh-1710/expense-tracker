package com.geekster.expense.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geekster.expense.tracker.constants.ApiPath;
import com.geekster.expense.tracker.model.Product;
import com.geekster.expense.tracker.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiPath.BASE_API)
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping(ApiPath.PRODUCT_API)
	public ResponseEntity<Object> saveProduct(@Valid @RequestBody Product product, @PathVariable String token) {
		return productService.saveProduct(product, token);
	}

	@PutMapping(ApiPath.PRODUCT_API_PRODUCT_ID_PATH_VARIABLE)
	public ResponseEntity<Object> updateProductById(@Valid @RequestBody Product product, @PathVariable String token,
			@PathVariable Long productId) {
		return productService.updateProductById(product, productId, token);
	}

	@DeleteMapping(ApiPath.PRODUCT_API_PRODUCT_ID_PATH_VARIABLE)
	public ResponseEntity<Object> deleteProductById(@PathVariable String token, @PathVariable Long productId) {
		return productService.deleteProductById(productId, token);
	}

	@GetMapping(ApiPath.PRODUCT_API_PRODUCT_ID_PATH_VARIABLE)
	public ResponseEntity<Object> getProductById(@PathVariable String token, @PathVariable Long productId) {
		return productService.getProductById(productId, token);
	}

	@GetMapping(ApiPath.PRODUCT_API_MONTH_PATH_VARIABLE)
	public ResponseEntity<Object> getTotalExpenseByMonth(@PathVariable String token, @PathVariable Integer month) {
		return productService.getTotalExpenseByMonth(month, token);
	}

	@GetMapping(ApiPath.PRODUCT_API_DATE_PATH_VARIABLE)
	public ResponseEntity<Object> getAllProductsByDate(@PathVariable String token, @PathVariable String date) {
		return productService.getAllProductsByDate(date, token);
	}

	@GetMapping(ApiPath.PRODUCT_API_GENERATE_REPORT_MONTH_PATH_VARIABLE)
	public ResponseEntity<Object> generateReportByMonth(@PathVariable String token, @PathVariable Integer month) {
		return productService.generateReportByMonth(month, token);
	}

}
