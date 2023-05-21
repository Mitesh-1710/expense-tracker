package com.geekster.expense.tracker.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.geekster.expense.tracker.constants.ExpenseTrackerConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
@Table(name = ExpenseTrackerConstants.PRODUCT_TABLE_NAME)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ExpenseTrackerConstants.PRODUCT_ID)
	private Long id;

	@Column(name = ExpenseTrackerConstants.PRODUCT_TITLE, nullable = false)
	private String title;

	@Column(name = ExpenseTrackerConstants.PRODUCT_DESCRIPTION, nullable = false)
	private String description;

	@Column(name = ExpenseTrackerConstants.PRODUCT_PRICE, nullable = false)
	private Double price;

	@Column(name = ExpenseTrackerConstants.PRODUCT_DATE, nullable = false)
	private LocalDate date;

	@Column(name = ExpenseTrackerConstants.PRODUCT_TIME, nullable = false)
	private LocalTime time;

	@ManyToOne()
	@JoinColumn(name = ExpenseTrackerConstants.PRODUCT_USER, referencedColumnName = ExpenseTrackerConstants.USER_ID)
	private UserEntity user;

}
