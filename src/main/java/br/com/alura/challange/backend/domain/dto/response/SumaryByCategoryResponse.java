package br.com.alura.challange.backend.domain.dto.response;

import java.math.BigDecimal;

import br.com.alura.challange.backend.domain.enums.CategoryEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SumaryByCategoryResponse {

	@ApiModelProperty(position = 1, value = "category", name = "categoria da despesa", dataType = "String", example = "ALIMENTACAO")
	private CategoryEnum categoryEnum;

	@ApiModelProperty(position = 2, value = "value", name = "valor da receita", dataType = "String", example = "800.00")
	private BigDecimal value;

}
