package br.com.alura.challange.backend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "tb_categoria")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoria_id", nullable = false)
	private Long id;
	
	@Column(name = "nome_categoria", nullable = false)
	private String nameCategory;
	
//	@Column(name = "alimentacao", nullable = false)
//	private String food;
//	
//	@Column(name = "saude", nullable = false)
//	private String health;
//	
//	@Column(name = "moradia", nullable = false)
//	private String home;
//	
//	@Column(name = "transporte", nullable = false)
//	private String transport;
//	
//	@Column(name = "educacao", nullable = false)
//	private String education;
//	
//	@Column(name = "lazer", nullable = false)
//	private String leisure;
//	
//	@Column(name = "imprevistos", nullable = false)
//	private String unforeseen;
//	
//	@Column(name = "outras", nullable = false)
//	private String others;

}
