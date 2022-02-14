package br.com.alura.challange.backend.domain.dto.response;

import java.math.BigDecimal;

import br.com.alura.challange.backend.domain.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SumaryByCategoryResponse {

	private CategoryEnum categoryEnum;

	private BigDecimal value;

}
