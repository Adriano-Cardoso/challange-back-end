package br.com.alura.challange.backend.service;

import java.time.LocalDate;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.alura.challange.backend.domain.Expense;
import br.com.alura.challange.backend.domain.request.ExpenseRequest;
import br.com.alura.challange.backend.domain.response.ExpenseResponse;
import br.com.alura.challange.backend.repository.ExpenseRepository;
import br.com.alura.challange.backend.validations.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("ExpenseService")
@AllArgsConstructor
@Validated
@Slf4j
public class ExpenseService {

	private ExpenseRepository expenseRepository;

	@Validated
	public ExpenseResponse createExpense(@Valid ExpenseRequest expenseRequest) {

		LocalDate date = LocalDate.now();
		
		this.expenseRepository.findByDescriptionAndValue(expenseRequest.getDescription(), expenseRequest.getValue(), date)
				.ifPresent(d -> {
					throw Message.DESCRIPTION_EXISTS_EXPENSE.asBusinessException();

				});

		Expense expense = Expense.of(expenseRequest);

		this.expenseRepository.save(expense);

		log.info("method=createRevenue Id={} description={} value={} date={}", expense.getId(),
				expense.getDescription(), expense.getValue(), expense.getDate());

		return expense.toResponse();
	}

	public Page<ExpenseResponse> listAllExpense() {
		int limit = 5;
		int page = 0;

		log.info("method=listAllExpense");

		Pageable pageable = PageRequest.of(page, limit);

		log.info("method=findAllVideoFree limit{}", limit);
		return this.expenseRepository.listAllExpense(pageable);
	}

	public ExpenseResponse findById(Long id) {
		Expense expense = this.expenseRepository.findById(id)
				.orElseThrow(() -> Message.NOT_FOUND_ID.asBusinessException());

		log.info("method=findById id={}", id);

		return expense.toResponse();
	}

	@Validated
	@Transactional
	public ExpenseResponse update(Long id, @Valid ExpenseRequest expenseRequest) {
		Expense expense = this.expenseRepository.findById(id)
				.orElseThrow(() -> Message.NOT_FOUND_ID.asBusinessException());

		LocalDate date = LocalDate.now();
		
		this.expenseRepository.findByDescriptionAndValue(expenseRequest.getDescription(), expenseRequest.getValue(), date)
				.ifPresent(d -> {
					throw Message.DESCRIPTION_EXISTS_EXPENSE.asBusinessException();

				});

		expense.update(expenseRequest);

		return expense.toResponse();
	}
	
	public void delete(Long id) {
		Expense expense = this.expenseRepository.findById(id).orElseThrow(() -> Message.NOT_FOUND_ID.asBusinessException());
		
		log.info("method=delete id={}", id);
		
		this.expenseRepository.delete(expense);
		
	}

}
