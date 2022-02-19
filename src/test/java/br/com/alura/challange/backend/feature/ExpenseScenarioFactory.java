package br.com.alura.challange.backend.feature;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.alura.challange.backend.domain.Expense;
import br.com.alura.challange.backend.domain.dto.request.ExpenseRequest;
import br.com.alura.challange.backend.domain.dto.request.ExpenseUpdateRequest;
import br.com.alura.challange.backend.domain.dto.response.ExpenseResponse;
import br.com.alura.challange.backend.domain.enums.CategoryEnum;

public class ExpenseScenarioFactory {

	public static final Expense EXPENSE = loadExpense();
	public static final ExpenseResponse EXPENSE_RESPONSE = loadExpenseResponse();
	public static final ExpenseRequest EXPENSE_REQUEST = loadExpenseRequest();
	public static final ExpenseUpdateRequest EXPENSE_UPDATE_REQUEST = loadExpenseUpdate();
	public static final ExpenseRequest CREATE = loadCreate();
	public static final Page<ExpenseResponse> PAGE_RESPONSE = loadPageResponse();



	private static Page<ExpenseResponse> loadPageResponse() {
		PageRequest page = PageRequest.of(0, 10);
		
		ExpenseResponse expenseResponse = new ExpenseResponse(1L, "teste", BigDecimal.TEN, LocalDate.now(), CategoryEnum.OUTRAS);
		
		List<ExpenseResponse> list = new ArrayList<>();
		
		list.add(expenseResponse);
		
		return new PageImpl<>(list, page, 10);
	}
	
	private static ExpenseRequest loadCreate() {
		return ExpenseRequest.builder().description("receita 01").value(BigDecimal.TEN).date(LocalDate.now())
				.categoryEnum(CategoryEnum.ALIMENTACAO).build();
	}

	private static ExpenseUpdateRequest loadExpenseUpdate() {
		return ExpenseUpdateRequest.builder().description("Compras atacado dos presente").value(BigDecimal.TEN).date(LocalDate.now()).categoryEnum(CategoryEnum.OUTRAS).build();
	}

	private static ExpenseRequest loadExpenseRequest() {
		return ExpenseRequest.builder().description("Despesas teste").value(BigDecimal.TEN).date(LocalDate.now()).categoryEnum(CategoryEnum.ALIMENTACAO).build();
	}

	private static ExpenseResponse loadExpenseResponse() {
		return ExpenseResponse.builder().id(1L).description("Salario").value(BigDecimal.TEN).date(LocalDate.of(2021, 10, 17)).categoryEnum(CategoryEnum.ALIMENTACAO)
				.build();
	}

	private static Expense loadExpense() {
		return Expense.builder().id(1L).description("Salario").value(BigDecimal.TEN).date(LocalDate.of(2021, 10, 17)).categoryEnum(CategoryEnum.ALIMENTACAO)
				.build();
	}

}
