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

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_despesas")
public class Despesas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "despesas_id", nullable = false)
	private Long expensesId;
	
	@Column(name = "descricao", length = 100, nullable = false)
	private String description;
	
	@Column(name = "valor", nullable = false)
	private BigDecimal value;
	
	@Column(name = "data", nullable = false)
	@JsonFormat(pattern = "dd/mm/yyyy")
	private LocalDate date;
	
	@PrePersist
	public void prePersist() {
		this.date = LocalDate.now();
	}

}
