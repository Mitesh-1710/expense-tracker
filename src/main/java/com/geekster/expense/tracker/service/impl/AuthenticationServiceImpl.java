package com.geekster.expense.tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekster.expense.tracker.entity.AuthenticationEntity;
import com.geekster.expense.tracker.entity.UserEntity;
import com.geekster.expense.tracker.repository.AuthenticationRepository;
import com.geekster.expense.tracker.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	AuthenticationRepository authenticationRepository;

	public void saveToken(AuthenticationEntity authenticationEntity) {
		authenticationRepository.save(authenticationEntity);
	}

	public AuthenticationEntity getToken(UserEntity user) {
		return authenticationRepository.findByUser(user);
	}

	public boolean authenticate(String token) {
		AuthenticationEntity authToken = authenticationRepository.findFirstByToken(token);
		boolean isAuthenticated = false;
		if (authToken != null) {
			isAuthenticated = true;
		}
		return isAuthenticated;
	}

	@Override
	public UserEntity getUserByToken(String token) {
		AuthenticationEntity authToken = authenticationRepository.findFirstByToken(token);
		return authToken.getUser();
	}

}
