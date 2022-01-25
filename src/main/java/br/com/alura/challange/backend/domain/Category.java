package br.com.alura.challange.backend.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

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
public class Category {
	
	private String alimentação;
	private String saude;
	private String moradia;
	private String transporte;
	private String educação;
	private String lazer;
	private String imprevistos;
	private String outras;

}
