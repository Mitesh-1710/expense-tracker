package com.geekster.expense.tracker.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
public class User {

	@NotEmpty
	@Size(min = 3, message = "student's first name should have at least 3 characters including a space between first and last name")
	@Pattern(regexp = "[A-Z][a-zA-Z]* [A-Z][a-zA-Z]*", message = "Name must follow pattern 'Firstname Lastname' ")
	private String name;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	@Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "Password must have at least eight characters including one uppercase letter, one lowercase letter, and one number or a special character")
	private String password;

}
