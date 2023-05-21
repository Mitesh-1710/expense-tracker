package com.geekster.expense.tracker.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class Product {

	@NotEmpty
	@Size(min = 3, message = "Product title should have at least 3 characters")
	private String title;

	@NotEmpty
	@Size(min = 5, message = "Product description should have at least 5 characters")
	private String description;

	@NotNull
	private Double price;

	@NotEmpty
	@Pattern(regexp = "^(?:[0-9]{2})?[0-9]{2}-[0-3]?[0-9]-[0-3]?[0-9]$", message = "Date should follow pattern 'yyyy-mm-dd' ")
	private String date;

	@NotEmpty
	@Size(min = 5, message = "Time should follow 24hr pattern 'hh:mm'")
	@Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "Time should follow 24hr pattern 'hh:mm' ")
	private String time;

}
