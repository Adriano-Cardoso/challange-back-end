package br.com.alura.challange.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alura.challange.backend.domain.dto.response.ExpenseResponse;
import br.com.alura.challange.backend.exception.BusinessException;
import br.com.alura.challange.backend.feature.ExpenseScenarioFactory;
import br.com.alura.challange.backend.repository.ExpenseRepository;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {

	@InjectMocks
	private ExpenseService expenseService;

	@Mock
	private ExpenseRepository expenseRepository;


	@Test
	@DisplayName("Listar id que existe na base")
	void listByIdExpense_WhenIsvalid_ExpectedOk() {

		when(this.expenseRepository.findById(anyLong())).thenReturn(Optional.of(ExpenseScenarioFactory.EXPENSE));

		this.expenseService.findById(1L);

		verify(expenseRepository).findById(any());

	}
	
	@Test
	@DisplayName("Listar receita por mes e ano")
	void istByExpenseYearAndMonth_WhenIsValid_ExpectedOk() {
		
		when(this.expenseRepository.listByExpenseYearAndMonth(any(), any(),any())).thenReturn(ExpenseScenarioFactory.PAGE_RESPONSE);
		
		Page<ExpenseResponse> pageExpenseYerAndMonth = this.expenseService.listByExpenseYearAndMonth(0, 10, 2022, 02);
		
		assertNotNull(pageExpenseYerAndMonth);
		
		verify(expenseRepository).listByExpenseYearAndMonth(any(), any(), any());
	}
	
	@Test
	@DisplayName("Listar todas as desepesas")
	void listAllRevenue_WhenListValid_ExpectedOk() {

		when(this.expenseRepository.listAllExpense(any(Pageable.class), anyString()))
				.thenReturn(ExpenseScenarioFactory.PAGE_RESPONSE);

		Page<ExpenseResponse> expense = this.expenseService.listAllExpense(0, 10, "te");

		assertNotNull(expense);

		assertEquals(ExpenseScenarioFactory.PAGE_RESPONSE, expense);

		verify(expenseRepository).listAllExpense(any(), any());
	}

	@Test
	@DisplayName("Listar id que não existe na base")
	void listByIdExpense_WhenIdIsInValid_ExpectedException() {

		when(this.expenseRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(BusinessException.class, () -> this.expenseService.findById(7L));
	}

	@Test
	@DisplayName("Deletar id que existe na base")
	void deleteByIdExpense_WhenIdIsInValid_ExpecetedOk() {

		when(this.expenseRepository.findById(anyLong())).thenReturn(Optional.of(ExpenseScenarioFactory.EXPENSE));

		this.expenseService.delete(1L);

		verify(expenseRepository).delete(any());
	}

	@Test
	@DisplayName("Deletar id que não existe na base")
	void deleteByIdExpense_WhenIdIsInValid_ExpectedException() {

		when(this.expenseRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(BusinessException.class, () -> this.expenseService.delete(7L));

	}

	@Test
	@DisplayName("Atualizar despesa, com validação de id e descrição corretos!")
	void update_WhenIdIsValid_ExpectedOk() {

		when(this.expenseRepository.findById(any())).thenReturn(Optional.of(ExpenseScenarioFactory.EXPENSE));

		when(this.expenseRepository.findByDescriptionAndValue(any(), any(), any())).thenReturn(Optional.empty());

		ExpenseResponse expenseResponse = this.expenseService.update(1L, ExpenseScenarioFactory.EXPENSE_UPDATE_REQUEST);

		assertNotNull(expenseResponse);

	}

	@Test
	@DisplayName("Atualizar despesa, com validação de id e inválido")
	void update_WheIdIsInvalid_ExpectedOk() {
		
		when(this.expenseRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(BusinessException.class,
				() -> expenseService.update(10L, ExpenseScenarioFactory.EXPENSE_UPDATE_REQUEST));
	}

	@Test
	@DisplayName("Atualizar despesa, com validação descrição e data e inválido")
	void update_WhenValidationDescripionDateInValid_ExpectdException() {
		
		when(this.expenseRepository.findById(any())).thenReturn(Optional.of(ExpenseScenarioFactory.EXPENSE));

		when(this.expenseRepository.findByDescriptionAndValue(any(), any(), any())).thenReturn(Optional.of(ExpenseScenarioFactory.EXPENSE_RESPONSE));
		
		assertThrows(BusinessException.class, () -> expenseService.update(1L, ExpenseScenarioFactory.EXPENSE_UPDATE_REQUEST));
	}
	
	@Test
	@DisplayName("Criar despesa, com validação descrição e data e válida")
	void createExpense_WhenValidationDescripitionAndDateIsValid_ExpectedOk() {
		
		when(this.expenseRepository.findByDescriptionAndValue(any(), any(), any())).thenReturn(Optional.empty());
		
		ExpenseResponse expenseResponse = this.expenseService.createExpense(ExpenseScenarioFactory.CREATE);
		
		assertNotNull(expenseResponse);
		
	}
	
	@Test
	@DisplayName("Criar despesa, com validação descrição e data e inválido")
	void createExpense_WhenValidationDescripitionAndDateIsInValid_ExpectedException() {
		when(this.expenseRepository.findByDescriptionAndValue(any(), any(), any())).thenReturn(Optional.of(ExpenseScenarioFactory.EXPENSE_RESPONSE));
		
		assertThrows(BusinessException.class, ()-> this.expenseService.createExpense(ExpenseScenarioFactory.EXPENSE_REQUEST));
		
	}

}
