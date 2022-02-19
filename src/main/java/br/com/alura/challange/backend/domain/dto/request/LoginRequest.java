package br.com.alura.challange.backend.domain.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoginRequest {
	
	@NotNull(message = "O campo 'email' esta incorreto no corpo da requisicao")
	@Email( message = "Formato do campo 'email'esta incorreto no corpo da requisicao")
    @ApiModelProperty(position = 1, required = false, value = "login do usuario", name = "email", dataType = "String", example = "admin@email.com")
    private String email;

	@NotNull(message = "O campo 'password' esta incorreto no corpo da requisicao!")
    @ApiModelProperty(position = 1, required = false, value = "senha do usuario", name = "password", dataType = "String", example = "123456")
    private String password;

}
