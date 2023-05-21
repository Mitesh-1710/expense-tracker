package com.geekster.expense.tracker.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.geekster.expense.tracker.constants.ExpenseTrackerConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = ExpenseTrackerConstants.AUTHENTICATION_TABLE_NAME)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AuthenticationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ExpenseTrackerConstants.AUTHENTICATION_ID)
	private Long id;

	@Column(name = ExpenseTrackerConstants.AUTHENTICATION_TOKEN, nullable = false)
	private String token;

	@Column(name = ExpenseTrackerConstants.AUTHENTICATION_CREATED_DATE, nullable = false)
	private LocalDateTime createdDate;

	@OneToOne()
	@JoinColumn(name = ExpenseTrackerConstants.AUTHENTICATION_USER, nullable = false)
	private UserEntity user;

}
