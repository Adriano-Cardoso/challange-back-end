package br.com.alura.challange.backend.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.alura.challange.backend.domain.dto.response.SummaryByCategoryResponse;
import br.com.alura.challange.backend.domain.dto.response.SummaryResponse;
import br.com.alura.challange.backend.repository.ExpenseRepository;
import br.com.alura.challange.backend.repository.RevenueRepository;
import lombok.AllArgsConstructor;

@Service("SummariesService")
@AllArgsConstructor
@Validated
public class SummariesService {

	private ExpenseRepository expenseRepository;
	private RevenueRepository revenueRepository;

	public SummaryResponse summaryMonth(Integer ano, Integer mes) {
		BigDecimal sumRevenue = this.revenueRepository.sumMoth(ano, mes).orElse(BigDecimal.ZERO);

		BigDecimal sumExpense = this.expenseRepository.sumMoth(ano, mes).orElse(BigDecimal.ZERO);

		BigDecimal finalBalance = sumRevenue.subtract(sumExpense);

		List<SummaryByCategoryResponse> summaryByCategoryResponses = this.expenseRepository
				.expensesByCategoryAtTheEndOfTheMonth(ano, mes);

		return new SummaryResponse(sumRevenue, sumExpense, finalBalance, summaryByCategoryResponses);
	}

}
