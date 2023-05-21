package com.geekster.expense.tracker.response;

import java.util.List;

import com.geekster.expense.tracker.model.Product;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MonthlyReportResponse extends ApplicationResponse {

	private String month;
	private Double totalExpense;
	private List<Product> productList;

}
