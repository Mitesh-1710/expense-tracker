package com.geekster.expense.tracker.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.geekster.expense.tracker.constants.ExpenseTrackerConstants;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = ExpenseTrackerConstants.USER_TABLE_NAME)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ExpenseTrackerConstants.USER_ID)
	private Long id;

	@Column(name = ExpenseTrackerConstants.USER_NAME, nullable = false)
	private String name;

	@Column(name = ExpenseTrackerConstants.USER_EMAIL, nullable = false, unique = true)
	private String email;

	@Column(name = ExpenseTrackerConstants.USER_PASSWORD, nullable = false, unique = true)
	private String password;

	@OneToOne(mappedBy = ExpenseTrackerConstants.FIELD_USER, cascade = CascadeType.ALL)
	private AuthenticationEntity authentication;

	@OneToMany(mappedBy = ExpenseTrackerConstants.FIELD_USER, cascade = CascadeType.ALL)
	private List<ProductEntity> product;

}
