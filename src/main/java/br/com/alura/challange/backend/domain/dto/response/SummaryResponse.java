package br.com.alura.challange.backend.domain.dto.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class SummaryResponse {
	
	
	private BigDecimal totalRevenueAmount;
	
	private BigDecimal totalRevenueExpense;
	
	private BigDecimal finalBalance;
	
	private List<SumaryByCategoryResponse> spendingByCategories;

}
