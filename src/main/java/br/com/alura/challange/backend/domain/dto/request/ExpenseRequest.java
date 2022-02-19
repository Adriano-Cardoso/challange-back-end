package br.com.alura.challange.backend.domain.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

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
public class ExpenseRequest {
	
	@NotEmpty(message = "O valor do campo 'description' é obrigatório no corpo da requisição")
	@NotNull(message = "O valor do campo 'description' é obrigatório no corpo da requisição")
	@ApiModelProperty(position = 1, value = "Description", name = "description", dataType = "String", example = "Compras Mes")
	private String description;

	@NotNull(message = "O valor do campo 'Value' está inválido no corpo da requisição")
	@Digits(integer = 10, fraction = 2, message = "O valor no campo 'Value' está inválido no corpo da requisição para o valor '${validatedValue}'")
	@Min(value = 1, message = "O campo 'limitValue' está com valor mínimo ('{value}') inválido para o valor '${validatedValue}'")
	@ApiModelProperty(position = 2, value = "Valor", name = "value", dataType = "BigDecimal", example = "1045.00")
	private BigDecimal value;
	
	@ApiModelProperty(position = 3, required = false, value = "Data", name = "date", dataType = "LocalDate", example = "2022-01-25")
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonIgnore
	private LocalDate date;
	
	@ApiModelProperty(position = 3, value = "categoryEnum", name = "categoryEnum", dataType = "CategoryEnum", example = "ALIMENTACAO")
	private CategoryEnum categoryEnum;

	@JsonIgnore
	public LocalDate getCurrentDate() {
		this.date = LocalDate.now();
		return date;
	}


	

}
