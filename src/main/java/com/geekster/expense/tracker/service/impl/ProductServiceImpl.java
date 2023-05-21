package com.geekster.expense.tracker.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.geekster.expense.tracker.constants.ExpenseTrackerConstants;
import com.geekster.expense.tracker.entity.ProductEntity;
import com.geekster.expense.tracker.entity.UserEntity;
import com.geekster.expense.tracker.model.Product;
import com.geekster.expense.tracker.repository.ProductRepository;
import com.geekster.expense.tracker.response.ApplicationResponse;
import com.geekster.expense.tracker.response.MonthlyExpenseResponse;
import com.geekster.expense.tracker.response.MonthlyReportResponse;
import com.geekster.expense.tracker.response.ProductResponse;
import com.geekster.expense.tracker.service.AuthenticationService;
import com.geekster.expense.tracker.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public ResponseEntity<Object> saveProduct(Product product, String token) {
		UserEntity user = authenticationService.getUserByToken(token);
		ApplicationResponse applicationResponse = new ApplicationResponse();
		ProductEntity productEntity = new ProductEntity();
		HttpStatus status = null;
		if (user != null) {
			productEntity = productEntity.toBuilder().title(product.getTitle()).description(product.getDescription())
					.price(product.getPrice()).date(LocalDate.parse(product.getDate()))
					.time(LocalTime.parse(product.getTime())).user(user).build();
			productRepository.save(productEntity);
			applicationResponse.setMessage(ExpenseTrackerConstants.PRODUCT_CREATE_RESPONSE);
			status = HttpStatus.OK;
		} else {
			applicationResponse.setMessage(ExpenseTrackerConstants.USER_NOT_REGISTRED_RESPONSE);
			status = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<>(applicationResponse, status);
	}

	@Override
	public ResponseEntity<Object> updateProductById(Product product, Long productId, String token) {
		UserEntity user = authenticationService.getUserByToken(token);
		ApplicationResponse applicationResponse = new ApplicationResponse();
		HttpStatus status = null;
		if (user != null) {
			Optional<ProductEntity> optionalProductEntity = productRepository.findById(productId);
			if (optionalProductEntity.isPresent()) {
				ProductEntity productEntity = optionalProductEntity.get();
				productEntity = productEntity.toBuilder().id(productId).title(product.getTitle())
						.description(product.getDescription()).price(product.getPrice())
						.date(LocalDate.parse(product.getDate())).time(LocalTime.parse(product.getTime())).user(user)
						.build();
				productRepository.save(productEntity);
				applicationResponse.setMessage(ExpenseTrackerConstants.PRODUCT_UPDATE_RESPONSE);
				status = HttpStatus.OK;
			} else {
				applicationResponse.setMessage(ExpenseTrackerConstants.PRODUCT_NOT_FOUND_RESPONSE);
				status = HttpStatus.OK;
			}
		} else {
			applicationResponse.setMessage(ExpenseTrackerConstants.USER_NOT_REGISTRED_RESPONSE);
			status = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<>(applicationResponse, status);
	}

	@Override
	public ResponseEntity<Object> deleteProductById(Long productId, String token) {
		UserEntity user = authenticationService.getUserByToken(token);
		ApplicationResponse applicationResponse = new ApplicationResponse();
		HttpStatus status = null;
		if (user != null) {
			Optional<ProductEntity> optionalProductEntity = productRepository.findById(productId);
			if (optionalProductEntity.isPresent()) {
				productRepository.deleteById(productId);
				applicationResponse.setMessage(ExpenseTrackerConstants.PRODUCT_DELETE_RESPONSE);
				status = HttpStatus.OK;
			} else {
				applicationResponse.setMessage(ExpenseTrackerConstants.PRODUCT_NOT_FOUND_RESPONSE);
				status = HttpStatus.OK;
			}
		} else {
			applicationResponse.setMessage(ExpenseTrackerConstants.USER_NOT_REGISTRED_RESPONSE);
			status = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<>(applicationResponse, status);
	}

	@Override
	public ResponseEntity<Object> getProductById(Long productId, String token) {
		UserEntity user = authenticationService.getUserByToken(token);
		ProductResponse productResponse = new ProductResponse();
		HttpStatus status = null;
		if (user != null) {
			Optional<ProductEntity> optionalProductEntity = productRepository.findById(productId);
			if (optionalProductEntity.isPresent()) {
				ProductEntity productEntity = optionalProductEntity.get();
				List<Product> productList = new ArrayList<>();
				Product product = new Product();
				product = product.toBuilder().title(productEntity.getTitle())
						.description(productEntity.getDescription()).price(productEntity.getPrice())
						.date(productEntity.getDate().toString()).time(productEntity.getTime().toString()).build();
				productList.add(product);
				productResponse.setProducts(productList);
				productResponse.setMessage(ExpenseTrackerConstants.PRODUCT_GET_RESPONSE);
				status = HttpStatus.OK;
			} else {
				productResponse.setMessage(ExpenseTrackerConstants.PRODUCT_NOT_FOUND_RESPONSE);
				status = HttpStatus.OK;
			}
		} else {
			productResponse.setMessage(ExpenseTrackerConstants.USER_NOT_REGISTRED_RESPONSE);
			status = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<>(productResponse, status);
	}

	@Override
	public ResponseEntity<Object> getTotalExpenseByMonth(Integer month, String token) {
		UserEntity user = authenticationService.getUserByToken(token);
		MonthlyExpenseResponse monthlyExpenseResponse = new MonthlyExpenseResponse();
		HttpStatus status = null;
		if (user != null) {
			LocalDate startOfTheMonth = LocalDate.of(LocalDate.now().getYear(), Month.of(month), 1);
			LocalDate endOfTheMonth = startOfTheMonth.withDayOfMonth(startOfTheMonth.lengthOfMonth());
			List<ProductEntity> product = productRepository.findAllProductsByMonth(startOfTheMonth, endOfTheMonth);
			if (!product.isEmpty()) {
				Double totalExpense = product.stream().map(ProductEntity::getPrice).reduce(0.0, Double::sum);
				monthlyExpenseResponse.setTotalExpense(totalExpense);
				monthlyExpenseResponse.setMessage(ExpenseTrackerConstants.PRODUCT_GET_TOTAL_EXPENSE_RESPONSE);
				status = HttpStatus.OK;
			} else {
				monthlyExpenseResponse.setMessage(ExpenseTrackerConstants.NO_PRODUCTS_FOUND_RESPONSE);
				status = HttpStatus.OK;
			}

		} else {
			monthlyExpenseResponse.setMessage(ExpenseTrackerConstants.USER_NOT_REGISTRED_RESPONSE);
			status = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<>(monthlyExpenseResponse, status);
	}

	@Override
	public ResponseEntity<Object> getAllProductsByDate(String date, String token) {
		UserEntity user = authenticationService.getUserByToken(token);
		ProductResponse productResponse = new ProductResponse();
		HttpStatus status = null;
		if (user != null) {
			List<ProductEntity> products = productRepository.findAllByDate(LocalDate.parse(date));
			if (!products.isEmpty()) {
				List<Product> productList = new ArrayList<>();
				products.stream().forEach(productEntity -> {
					Product product = new Product();
					product = product.toBuilder().title(productEntity.getTitle())
							.description(productEntity.getDescription()).price(productEntity.getPrice())
							.date(productEntity.getDate().toString()).time(productEntity.getTime().toString()).build();
					productList.add(product);
				});
				productResponse.setProducts(productList);
				productResponse.setMessage(ExpenseTrackerConstants.PRODUCT_GET_ALL_RESPONSE);
				status = HttpStatus.OK;
			} else {
				productResponse.setMessage(ExpenseTrackerConstants.NO_PRODUCTS_FOUND_RESPONSE);
				status = HttpStatus.OK;
			}
		} else {
			productResponse.setMessage(ExpenseTrackerConstants.USER_NOT_REGISTRED_RESPONSE);
			status = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<>(productResponse, status);
	}

	@Override
	public ResponseEntity<Object> generateReportByMonth(Integer month, String token) {
		UserEntity user = authenticationService.getUserByToken(token);
		MonthlyReportResponse monthlyReportResponse = new MonthlyReportResponse();
		HttpStatus status = null;
		if (user != null) {
			LocalDate startOfTheMonth = LocalDate.of(LocalDate.now().getYear(), Month.of(month), 1);
			LocalDate endOfTheMonth = startOfTheMonth.withDayOfMonth(startOfTheMonth.lengthOfMonth());
			List<ProductEntity> products = productRepository.findAllProductsByMonth(startOfTheMonth, endOfTheMonth);
			if (!products.isEmpty()) {
				Double totalExpense = products.stream().map(ProductEntity::getPrice).reduce(0.0, Double::sum);
				List<Product> productList = new ArrayList<>();
				products.stream().forEach(productEntity -> {
					Product product = new Product();
					product = product.toBuilder().title(productEntity.getTitle())
							.description(productEntity.getDescription()).price(productEntity.getPrice())
							.date(productEntity.getDate().toString()).time(productEntity.getTime().toString()).build();
					productList.add(product);
				});
				monthlyReportResponse.setMonth(Month.of(month).toString());
				monthlyReportResponse.setTotalExpense(totalExpense);
				monthlyReportResponse.setProductList(productList);
				monthlyReportResponse.setMessage(ExpenseTrackerConstants.PRODUCT_MONTHLY_REPORT_RESPONSE);
				status = HttpStatus.OK;
			} else {
				monthlyReportResponse.setMessage(ExpenseTrackerConstants.NO_PRODUCTS_FOUND_RESPONSE);
				status = HttpStatus.OK;
			}

		} else {
			monthlyReportResponse.setMessage(ExpenseTrackerConstants.USER_NOT_REGISTRED_RESPONSE);
			status = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<>(monthlyReportResponse, status);
	}

}
