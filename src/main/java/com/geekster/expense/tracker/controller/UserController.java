package com.geekster.expense.tracker.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geekster.expense.tracker.constants.ApiPath;
import com.geekster.expense.tracker.model.SignIn;
import com.geekster.expense.tracker.model.User;
import com.geekster.expense.tracker.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiPath.BASE_API)
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(ApiPath.USER_API_SIGNUP)
	public ResponseEntity<Object> signup(@Valid @RequestBody User user) throws NoSuchAlgorithmException {
		return userService.signUp(user);
	}

	@PostMapping(ApiPath.USER_API_SIGNIN)
	public ResponseEntity<Object> signin(@Valid @RequestBody SignIn signIn) throws NoSuchAlgorithmException {
		return userService.signIn(signIn);
	}

	@PutMapping(ApiPath.USER_API_USER_ID_PATH_VARIABLE)
	public ResponseEntity<Object> updateUserById(@Valid @RequestBody User user, @PathVariable Long userId,
			@RequestParam String token) {
		return userService.updateUserById(user, userId, token);
	}

	@GetMapping(ApiPath.USER_API_USER_ID_PATH_VARIABLE)
	public ResponseEntity<Object> getUserById(@PathVariable Long userId, @RequestParam String token) {
		return userService.getUserById(userId, token);
	}

	@DeleteMapping(ApiPath.USER_API_USER_ID_PATH_VARIABLE)
	public ResponseEntity<Object> deleteUserById(@PathVariable Long userId, @RequestParam String token) {
		return userService.deleteUserById(userId, token);
	}

}
