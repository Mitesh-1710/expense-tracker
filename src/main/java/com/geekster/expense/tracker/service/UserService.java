package com.geekster.expense.tracker.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.geekster.expense.tracker.model.SignIn;
import com.geekster.expense.tracker.model.User;

@Service
public interface UserService {

	public ResponseEntity<Object> signUp(User user) throws NoSuchAlgorithmException;

	public ResponseEntity<Object> signIn(SignIn signInInput) throws NoSuchAlgorithmException;

	public ResponseEntity<Object> updateUserById(User user, Long userId , String token);
	
	public ResponseEntity<Object> deleteUserById(Long userId, String token);

	public ResponseEntity<Object> getUserById(Long userId, String token);

}
