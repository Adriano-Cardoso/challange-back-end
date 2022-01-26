package br.com.alura.challange.backend.domain.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ExpenseResponse {
	
	private Long id;
	
	private String description;

	private BigDecimal value;

	private LocalDate date;
	
	private Long categoryId;

}
