package br.com.alura.challange.backend.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.challange.backend.domain.Expense;
import br.com.alura.challange.backend.domain.response.ExpenseResponse;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{
	
	
	@Query("select new br.com.alura.challange.backend.domain.response.ExpenseResponse(r.id,r.description,r.value, r.date, r.categoryId) "
			+ "From Expense r "
			+ "where r.description=:description"
			+ " AND r.value=:value"
			+ " AND r.date=:date")
	Optional<ExpenseResponse> findByDescriptionAndValue(@Param(value = "description")String description, @Param(value = "value") BigDecimal value, @Param(value = "date") LocalDate date);
	
	
	@Query("select new br.com.alura.challange.backend.domain.response.ExpenseResponse(r.id,r.description,r.value, r.date, r.categoryId)"
			+ " From Expense r"
			+ " where r.description=:description)")
	Page<ExpenseResponse> listAllExpense(Pageable pageable, @Param(value = "description")String description);



}
