package br.com.alura.challange.backend.domain.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.challange.backend.domain.enums.CategoryEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ExpenseResponse {
	
	@ApiModelProperty(position = 1, value = "id", name = "id da despesa", dataType = "Long", example = "2")
	private Long id;
	
	@ApiModelProperty(position = 2, value = "Description", name = "description da despesa", dataType = "String", example = "Compras Mes")
	private String description;

	@ApiModelProperty(position = 3, value = "value", name = "valor da despesa", dataType = "String", example = "800.00")
	private BigDecimal value;

	@ApiModelProperty(position = 4, value = "Description", name = "description", dataType = "String", example = "2022-02-14")
	private LocalDate date;
	
	@ApiModelProperty(position = 5, value = "category", name = "categoria da despesa", dataType = "String", example = "ALIMENTACAO")
	private CategoryEnum categoryEnum;

}
