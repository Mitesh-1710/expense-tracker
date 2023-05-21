package com.geekster.expense.tracker.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.geekster.expense.tracker.constants.ExpenseTrackerConstants;
import com.geekster.expense.tracker.entity.AuthenticationEntity;
import com.geekster.expense.tracker.entity.UserEntity;
import com.geekster.expense.tracker.model.SignIn;
import com.geekster.expense.tracker.model.User;
import com.geekster.expense.tracker.repository.UserRepository;
import com.geekster.expense.tracker.response.ApplicationResponse;
import com.geekster.expense.tracker.response.SignInResponse;
import com.geekster.expense.tracker.response.UserResponse;
import com.geekster.expense.tracker.service.AuthenticationService;
import com.geekster.expense.tracker.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationService authenticationService;

	public ResponseEntity<Object> signUp(User user) throws NoSuchAlgorithmException {
		UserEntity emailExists = userRepository.findFirstByEmail(user.getEmail());
		ApplicationResponse applicationResponse = new ApplicationResponse();
		HttpStatus status = null;
		if (emailExists != null) {
			applicationResponse.setMessage(ExpenseTrackerConstants.USER_ALREADY_EXISTS_RESPONSE);
			status = HttpStatus.OK;
		} else {

			UserEntity passwordExists = userRepository.findFirstByPassword(user.getPassword());
			if (passwordExists == null) {
				String encryptedPassword = encryptPassword(user.getPassword());

				UserEntity newUser = new UserEntity();
				newUser = newUser.toBuilder().name(user.getName()).email(user.getEmail()).password(encryptedPassword)
						.build();

				userRepository.save(newUser);

				AuthenticationEntity authentication = new AuthenticationEntity();
				authentication = authentication.toBuilder().createdDate(LocalDateTime.now())
						.token(UUID.randomUUID().toString()).user(newUser).build();

				authenticationService.saveToken(authentication);
				applicationResponse.setMessage(ExpenseTrackerConstants.USER_REGISTRATION_RESPONSE);
				status = HttpStatus.OK;
			} else {
				applicationResponse.setMessage(ExpenseTrackerConstants.USER_ENTER_STRONG_PASSWORD_RESPONSE);
				status = HttpStatus.OK;
			}

		}
		return new ResponseEntity<>(applicationResponse, status);
	}

	private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(userPassword.getBytes());
		byte[] digested = md5.digest();
		return DatatypeConverter.printHexBinary(digested);
	}

	public ResponseEntity<Object> signIn(SignIn signInInput) throws NoSuchAlgorithmException {

		UserEntity user = userRepository.findFirstByEmail(signInInput.getEmail());
		SignInResponse signInResponse = new SignInResponse();
		HttpStatus status = null;
		if (user == null) {
			signInResponse.setMessage(ExpenseTrackerConstants.USER_NOT_REGISTRED_RESPONSE);
			signInResponse.setToken(null);
			status = HttpStatus.FORBIDDEN;
		} else {
			String encryptedPassword = encryptPassword(signInInput.getPassword());

			boolean isPasswordValid = encryptedPassword.equals(user.getPassword());

			if (!isPasswordValid) {
				signInResponse.setMessage(ExpenseTrackerConstants.INVALID_USER_PASSWORD_RESPONSE);
				signInResponse.setToken(null);
				status = HttpStatus.FORBIDDEN;
			} else {
				AuthenticationEntity token = authenticationService.getToken(user);
				signInResponse.setMessage(ExpenseTrackerConstants.USER_AUTHENTICATION_RESPONSE);
				signInResponse.setToken(token.getToken());
				status = HttpStatus.OK;
			}
		}
		return new ResponseEntity<>(signInResponse, status);
	}

	public ResponseEntity<Object> updateUserById(User user, Long userId, String token) {
		String message = "";
		HttpStatus status;
		if (authenticationService.authenticate(token)) {
			Optional<UserEntity> userEntity = userRepository.findById(userId);
			if (userEntity.isPresent()) {
				UserEntity updatedUser = userEntity.get();
				updatedUser = updatedUser.toBuilder().name(user.getName()).email(user.getEmail())
						.password(user.getPassword()).build();
				userRepository.save(updatedUser);
			}
			message = ExpenseTrackerConstants.USER_UPDATE_RESPONSE;
			status = HttpStatus.OK;
		} else {
			message = ExpenseTrackerConstants.INVALID_USER_RESPONSE;
			status = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<>(message, status);
	}

	@Override
	public ResponseEntity<Object> deleteUserById(Long userId, String token) {
		String message = "";
		HttpStatus status;
		if (authenticationService.authenticate(token)) {
			Optional<UserEntity> userEntity = userRepository.findById(userId);
			if (userEntity.isPresent()) {
				userRepository.deleteById(userId);
			}
			message = ExpenseTrackerConstants.USER_DELETE_RESPONSE;
			status = HttpStatus.OK;
		} else {
			message = ExpenseTrackerConstants.INVALID_USER_RESPONSE;
			status = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<>(message, status);
	}

	@Override
	public ResponseEntity<Object> getUserById(Long userId, String token) {
		HttpStatus status;
		UserResponse userResponse = new UserResponse();
		if (authenticationService.authenticate(token)) {
			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
			User user = new User();
			if (optionalUserEntity.isPresent()) {
				UserEntity userEntity = optionalUserEntity.get();
				user = user.toBuilder().name(userEntity.getName()).email(userEntity.getEmail())
						.password(userEntity.getPassword()).build();
			}
			userResponse.setMessage(ExpenseTrackerConstants.USER_GET_RESPONSE);
			userResponse.setUser(user);
			status = HttpStatus.OK;
		} else {
			userResponse.setMessage(ExpenseTrackerConstants.INVALID_USER_RESPONSE);
			status = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<>(userResponse, status);
	}

}
