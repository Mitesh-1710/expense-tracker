package com.geekster.expense.tracker.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.geekster.expense.tracker.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	@Query(value = "SELECT * FROM product p WHERE p.date >= :startOfTheMonth AND p.date <= :endOfTheMonth", nativeQuery = true)
	public List<ProductEntity> findAllProductsByMonth(LocalDate startOfTheMonth, LocalDate endOfTheMonth);

	public List<ProductEntity> findAllByDate(LocalDate parse);

}
