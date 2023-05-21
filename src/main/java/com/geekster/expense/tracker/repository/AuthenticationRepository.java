package com.geekster.expense.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geekster.expense.tracker.entity.AuthenticationEntity;
import com.geekster.expense.tracker.entity.UserEntity;

@Repository
public interface AuthenticationRepository extends JpaRepository<AuthenticationEntity, Long> {

	public AuthenticationEntity findByUser(UserEntity user);

	public AuthenticationEntity findFirstByToken(String token);

}
