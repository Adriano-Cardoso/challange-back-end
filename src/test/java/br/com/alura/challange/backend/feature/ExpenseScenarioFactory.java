package br.com.alura.challange.backend.feature;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.challange.backend.domain.Expense;
import br.com.alura.challange.backend.domain.dto.request.ExpenseRequest;
import br.com.alura.challange.backend.domain.dto.response.ExpenseResponse;
import br.com.alura.challange.backend.domain.enums.CategoryEnum;

public class ExpenseScenarioFactory {

	public static final Expense EXPENSE = loadExpense();
	public static final ExpenseResponse EXPENSE_RESPONSE = loadExpenseResponse();
	public static final ExpenseRequest EXPENSE_REQUEST = loadExpenseUpdate();
	public static final ExpenseRequest CREATE = loadCreate();


	private static ExpenseRequest loadCreate() {
		return ExpenseRequest.builder().description("receita 01").value(BigDecimal.TEN).date(LocalDate.now()).categoryEnum(CategoryEnum.ALIMENTACAO).build();
	}

	private static ExpenseRequest loadExpenseUpdate() {
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
