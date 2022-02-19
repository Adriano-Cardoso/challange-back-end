package br.com.alura.challange.backend.domain.dto.response;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class SummaryResponse {
	
	@ApiModelProperty(position = 1, value = "totalRevenueAmount", name = "Valor total da receita", dataType = "BigDecimal", example = "3000.00")
	private BigDecimal totalRevenueAmount;
	
	@ApiModelProperty(position = 1, value = "totalRevenueAmount", name = "Valor total da despesa", dataType = "BigDecimal", example = "1500.00")
	private BigDecimal totalRevenueExpense;
	
	@ApiModelProperty(position = 1, value = "finalBalance", name = "balanço final", dataType = "BigDecimal", example = "1500.00")
	private BigDecimal finalBalance;
	
	@ApiModelProperty(position = 4, value = "SumaryByCategoryResponse", name = "Gastos por categorias", dataType = "list", example = "")
	private List<SummaryByCategoryResponse> spendingByCategories;

}
