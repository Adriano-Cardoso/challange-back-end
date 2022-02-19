package br.com.alura.challange.backend.service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.alura.challange.backend.domain.Expense;
import br.com.alura.challange.backend.domain.dto.request.ExpenseRequest;
import br.com.alura.challange.backend.domain.dto.request.ExpenseUpdateRequest;
import br.com.alura.challange.backend.domain.dto.response.ExpenseResponse;
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

		this.expenseRepository.findByDescriptionAndValue(expenseRequest.getDescription(), expenseRequest.getValue(),
				expenseRequest.getCurrentDate()).ifPresent(d -> {
					throw Message.EXISTS_EXPENSE.asBusinessException();

				});

		Expense expense = Expense.of(expenseRequest);

		this.expenseRepository.save(expense);

		log.info("method=createExpense Id={} description={} value={} date={}", expense.getId(),
				expense.getDescription(), expense.getValue(), expense.getDate());

		return expense.toResponse();
	}

	public Page<ExpenseResponse> listAllExpense(int page, int limit, String description) {

		log.info("method=listAllExpense");

		Pageable pageable = PageRequest.of(page, limit);

		log.info("method=listAllExpense limit{}", limit);
		return this.expenseRepository.listAllExpense(pageable, description);
	}

	public ExpenseResponse findById(Long id) {
		Expense expense = this.expenseRepository.findById(id)
				.orElseThrow(() -> Message.NOT_FOUND_ID.asBusinessException());

		log.info("method=findById id={}", id);

		return expense.toResponse();
	}

	@Validated
	@Transactional
	public ExpenseResponse update(Long id, @Valid ExpenseUpdateRequest expenseUpdateRequest) {
		Expense expense = this.expenseRepository.findById(id)
				.orElseThrow(() -> Message.NOT_FOUND_ID.asBusinessException());

		this.expenseRepository.findByDescriptionAndValue(expenseUpdateRequest.getDescription(),
				expenseUpdateRequest.getValue(), expenseUpdateRequest.getDate()).ifPresent(d -> {
					throw Message.EXISTS_EXPENSE.asBusinessException();

				});

		expense.update(expenseUpdateRequest);

		return expense.toResponse();
	}

	public void delete(Long id) {
		Expense expense = this.expenseRepository.findById(id)
				.orElseThrow(() -> Message.NOT_FOUND_ID.asBusinessException());

		log.info("method=delete id={}", id);

		this.expenseRepository.delete(expense);

	}

	public Page<ExpenseResponse> listByExpenseYearAndMonth(int page, int limit, Integer year, Integer month) {

		log.info("method=listByRevenueMonth");

		Pageable pageable = PageRequest.of(page, limit);

		log.info("method=findByDateAndYear limit{}", limit);

		return this.expenseRepository.listByExpenseYearAndMonth(pageable, year, month);

	}

}
