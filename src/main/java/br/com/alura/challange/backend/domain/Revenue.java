package br.com.alura.challange.backend.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_receitas")
public class Revenue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "despesas_id", nullable = false)
	private Long id;
	
	@Column(name = "descrição", nullable = false)
	private String description;

	@Column(name = "valor", nullable = false)
	private BigDecimal value;

	@Column(name = "data", nullable = false)
	private LocalDate date;
}