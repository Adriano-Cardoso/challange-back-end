package br.com.alura.challange.backend.feature;

import br.com.alura.challange.backend.domain.dto.response.LoginResponse;

public class LoginScenarioFactory {
	
	public static final LoginResponse LOGIN_RESPONSE = loadLoginResponse();

	private static LoginResponse loadLoginResponse() {
		return new LoginResponse("fkdasofkoadskf0oadsf", "Bearer");
	}

}
