package br.com.alura.challange.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.challange.backend.domain.dto.request.LoginRequest;
import br.com.alura.challange.backend.domain.dto.response.LoginResponse;
import br.com.alura.challange.backend.service.LoginService;
import lombok.AllArgsConstructor;

@RequestMapping("/auth")
@AllArgsConstructor
@RestController
public class LoginController {

	private LoginService loginService;

	@PostMapping
	public ResponseEntity<LoginResponse> auth(@RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(loginService.auth(loginRequest));
	}
}
