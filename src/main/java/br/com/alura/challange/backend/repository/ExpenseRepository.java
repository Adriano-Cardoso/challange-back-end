package br.com.alura.challange.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.challange.backend.domain.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{

}
