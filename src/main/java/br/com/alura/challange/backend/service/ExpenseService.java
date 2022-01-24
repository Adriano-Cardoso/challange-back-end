package br.com.alura.challange.backend.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.alura.challange.backend.domain.Expense;
import br.com.alura.challange.backend.domain.request.ExpenseRequest;
import br.com.alura.challange.backend.domain.response.ExpenseResponse;
import br.com.alura.challange.backend.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("ExpenseService")
@AllArgsConstructor
@Validated
@Slf4j
public class ExpenseService {
	
	
	private ExpenseRepository expenseRepository;
	
	@Validated
	public ExpenseResponse create (@Valid ExpenseRequest expenseRequest) {
		this.expenseRepository.findByDescriptionAndValue(expenseRequest.getDescription(), expenseRequest.getValue());
		
		Expense expense = Expense.of(expenseRequest);
		
		this.expenseRepository.save(expense);
		
		log.info("method=createRevenue Id={} description={} value={} date={}", expense.getId(),
				expense.getDescription(), expense.getValue(), expense.getDate());
		
		return expense.toResponse();
	}

}
