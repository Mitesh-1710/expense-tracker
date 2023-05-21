package com.geekster.expense.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geekster.expense.tracker.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>{

	  public UserEntity findFirstByEmail(String email);
	  
	  public UserEntity findFirstByPassword(String password);
	
}
