package br.com.alura.challange.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.challange.backend.domain.dto.response.SummaryResponse;
import br.com.alura.challange.backend.service.SummariesService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

@Api(value = "Resumo Endpoint", description = "Resumo", tags = { "Resumo Endpoint" })
@RestController
@AllArgsConstructor
@RequestMapping("/resumo")
public class SummaryController {
	
	
	private SummariesService summariesService;
	
	@GetMapping("/{year}/{month}")
	public ResponseEntity<SummaryResponse> summaryMonth(@PathVariable("year") Integer year, @PathVariable("month") Integer month){
		return ResponseEntity.status(HttpStatus.OK).body(this.summariesService.summaryMonth(year, month));
	}

}
