package br.com.alura.challange.backend.domain.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RevenueResponse {
	
	@ApiModelProperty(position = 1, value = "id", name = "id da receita", dataType = "Long", example = "2")
	private Long id;
	
	@ApiModelProperty(position = 2, value = "Description", name = "descrição da receita", dataType = "String", example = "Salario mensal")
	private String description;

	@ApiModelProperty(position = 3, value = "value", name = "valor da receita", dataType = "String", example = "2000.00")
	private BigDecimal value;

	@ApiModelProperty(position = 4, value = "Description", name = "data da receita", dataType = "String", example = "2022-02-14")
	private LocalDate date;


}
