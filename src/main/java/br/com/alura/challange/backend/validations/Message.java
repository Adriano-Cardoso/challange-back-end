package br.com.alura.challange.backend.validations;

import org.springframework.http.HttpStatus;

import br.com.alura.challange.backend.exception.BusinessException;

public enum Message {
	
	
	DESCRIPTION_EXISTS("A receita já foi criada no mês atual ", HttpStatus.BAD_REQUEST),
	NOT_FOUND_ID("Id não encontrado ", HttpStatus.NOT_FOUND),
	DESCRIPTION_EXISTS_EXPENSE("A despesa já foi criada no mês atual ", HttpStatus.BAD_REQUEST),
	NOT_FOUND_DESCRIPTION("A Descrição da despesa não existe na base ", HttpStatus.NOT_FOUND), 
	NOT_FOUND_MONTH_OR_YEAR("O mês ou o ano não existe na base ", HttpStatus.NOT_FOUND),
	CATEGORY_NOT_FOUND("A categoria não foi encontrada! ", HttpStatus.NOT_FOUND);
	
	private String value;
	private String description;
	private HttpStatus statusCode;

	private Message(String value, HttpStatus statusCode) {
		this.value = value;
		this.statusCode = statusCode;
	}

	private Message(String value, String description, HttpStatus statusCode) {
		this.value = value;
		this.description = description;
		this.statusCode = statusCode;
	}

	private Message(String value) {
		this.value = value;
	}

	public String getMessage() {
		return this.value;
	}

	public HttpStatus getStatus() {
		return this.statusCode;
	}

	public String getDescription() {
		return description;
	}

	public BusinessException asBusinessException() {
		return BusinessException.builder().httpStatusCode(this.getStatus())
				.code(String.valueOf(this.getStatus().value())).message(this.getMessage())
				.description(this.getDescription()).build();
	}

}
