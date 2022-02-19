package br.com.alura.challange.backend.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.alura.challange.backend.domain.dto.request.ExpenseRequest;
import br.com.alura.challange.backend.domain.dto.response.ExpenseResponse;
import br.com.alura.challange.backend.domain.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_despesas")
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "despesas_id", nullable = false)
	private Long id;

	@Column(name = "descricao", nullable = false)
	private String description;

	@Column(name = "valor", nullable = false)
	private BigDecimal value;

	@Column(name = "data", nullable = false)
	@JsonFormat(pattern = "dd/mm/yyyy")
	private LocalDate date;

	@Enumerated(EnumType.STRING)
	@Column(name = "Categoria", nullable = false)
	private CategoryEnum categoryEnum;

	@PrePersist
	public void prePersist() {
		this.date = LocalDate.now();
	}

	public ExpenseResponse toResponse() {
		return ExpenseResponse.builder().id(this.id).description(this.description).value(this.value).date(this.date)
				.categoryEnum(this.categoryEnum).build();
	}

	public static Expense of(ExpenseRequest expenseRequest) {
		return Expense.builder().description(expenseRequest.getDescription()).value(expenseRequest.getValue())
				.categoryEnum(expenseRequest.getCategoryEnum()).build();
	}

	public void update(ExpenseRequest expenseRequest) {

		this.description = expenseRequest.getDescription();
		this.value = expenseRequest.getValue();
		this.date = expenseRequest.getDate();
		this.categoryEnum = expenseRequest.getCategoryEnum();

	}

	public void addCategory(CategoryEnum categoryEnum) {
		this.categoryEnum = categoryEnum;
	}

}
