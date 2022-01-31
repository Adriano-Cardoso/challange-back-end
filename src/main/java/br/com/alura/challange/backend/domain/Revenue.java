package br.com.alura.challange.backend.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.alura.challange.backend.domain.request.RevenueRequest;
import br.com.alura.challange.backend.domain.response.RevenueResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_receitas")
public class Revenue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "receita_id", nullable = false)
	private Long id;
	
	@Column(name = "descricao", nullable = false)
	private String description;

	@Column(name = "valor", nullable = false)
	private BigDecimal value;

	@Column(name = "data", nullable = false)
	@JsonFormat(pattern = "dd/mm/yyyy")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate date;
	
	@PrePersist
	public void prePersist() {
		this.date = LocalDate.now();
	}
	
	
	public RevenueResponse toResponse() {
		return RevenueResponse.builder().id(this.id).description(this.description).value(this.value).date(this.date).build();
	}
	
	public static Revenue of(RevenueRequest revenueRequest) {
		return Revenue.builder().description(revenueRequest.getDescription()).value(revenueRequest.getValue()).date(LocalDate.now()).build();
	}


	public void update(RevenueRequest revenueRequest) {
		this.description = revenueRequest.getDescription();
		this.value = revenueRequest.getValue();
		this.date = revenueRequest.getCurrentDate();
		
	}
}
