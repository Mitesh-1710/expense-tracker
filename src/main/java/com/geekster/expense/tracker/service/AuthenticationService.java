package com.geekster.expense.tracker.service;

import org.springframework.stereotype.Service;

import com.geekster.expense.tracker.entity.AuthenticationEntity;
import com.geekster.expense.tracker.entity.UserEntity;

@Service
public interface AuthenticationService {

	public void saveToken(AuthenticationEntity authenticationEntity);

	public AuthenticationEntity getToken(UserEntity user);

	public boolean authenticate(String token);
	
	public UserEntity getUserByToken(String token);

}
