package br.com.alura.challange.backend.feature;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.challange.backend.domain.Expense;
import br.com.alura.challange.backend.domain.dto.request.ExpenseRequest;
import br.com.alura.challange.backend.domain.dto.response.ExpenseResponse;

public class ExpenseScenarioFactory {

//	public static final Page<ExpenseResponse> LIST_ALL = loadListAll();
	public static final Expense EXPENSE = loadExpense();
	public static final ExpenseResponse EXPENSE_RESPONSE = loadExpenseResponse();
	public static final ExpenseRequest EXPENSE_REQUEST = loadExpenseUpdate();
	public static final ExpenseRequest CREATE = loadCreate();

//	private static Page<ExpenseResponse> loadListAll() {
//
//		PageRequest page = PageRequest.of(0, 10);
//
//		ExpenseResponse expenseResponse = new ExpenseResponse(1l, "teste", BigDecimal.TEN, LocalDate.now());
//
//		List<ExpenseResponse> list = new ArrayList<>();
//
//		list.add(expenseResponse);
//
//		return new PageImpl<>(list, page, 5);
//	}

	private static ExpenseRequest loadCreate() {
		return ExpenseRequest.builder().description("receita 01").value(BigDecimal.TEN).date(LocalDate.now()).build();
	}

	private static ExpenseRequest loadExpenseUpdate() {
		return ExpenseRequest.builder().description("Despesas teste").value(BigDecimal.TEN).date(LocalDate.now()).build();
	}

	private static ExpenseResponse loadExpenseResponse() {
		return ExpenseResponse.builder().id(1L).description("Salario").value(BigDecimal.TEN).date(LocalDate.of(2021, 10, 17))
				.build();
	}

	private static Expense loadExpense() {
		return Expense.builder().id(1L).description("Salario").value(BigDecimal.TEN).date(LocalDate.of(2021, 10, 17))
				.build();
	}

}
