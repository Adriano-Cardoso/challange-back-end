package br.com.alura.challange.backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.challange.backend.domain.dto.request.RevenueRequest;
import br.com.alura.challange.backend.domain.dto.response.RevenueResponse;
import br.com.alura.challange.backend.service.RevenueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@Api(value = "Receita Endpoint", description = "Receita", tags = { "Receita Endpoint" })
@RestController
@AllArgsConstructor
@RequestMapping("/receitas")
public class RevenueController {

	private RevenueService revenueService;

	@ApiOperation(value = "Cria uma nova receita")
	@PostMapping
	public ResponseEntity<RevenueResponse> createRevenue(@RequestBody RevenueRequest revenueRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.revenueService.createRevenue(revenueRequest));

	}

	@ApiOperation(value = "Listagem de receitas")
	@GetMapping
	public ResponseEntity<Page<RevenueResponse>> listAllRevenue(
			@RequestParam(required = false, defaultValue = "0", name = "page") int page,
			@RequestParam(required = false, defaultValue = "10", name = "limit") int limit,
			@RequestParam(required = false, name = "description") String description) {
		return ResponseEntity.status(HttpStatus.OK).body(this.revenueService.listAllRevenue(page, limit, description));
	}

	@ApiOperation(value = "Detalhamento de receita")
	@GetMapping("/{id}")
	public ResponseEntity<RevenueResponse> findById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.revenueService.findById(id));
	}

	@ApiOperation(value = "Atualização de receita por id")
	@PutMapping("/{id}")
	public ResponseEntity<RevenueResponse> updateRevenue(@PathVariable("id") Long id,
			@RequestBody RevenueRequest revenueRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(this.revenueService.updateRevenue(id, revenueRequest));
	}

	@ApiOperation(value = "Listar receitas por ano e mes")
	@GetMapping("{year}/{month}")
	public ResponseEntity<Page<RevenueResponse>> listByRevenueMonth(
			@RequestParam(required = false, defaultValue = "0", name = "page") int page,
			@RequestParam(required = false, defaultValue = "10", name = "limit") int limit,
			@RequestParam(required = false, name = "year") Integer year,
			@RequestParam(required = false, name = "month") Integer month) {
		return ResponseEntity.status(HttpStatus.OK).body(this.revenueService.listByRevenueYearAndMonth(page, limit, year, month));
	}

	@ApiOperation(value = "Exclusão de receita por id")
	@DeleteMapping("/{id}")
	public ResponseEntity<RevenueResponse> delete(@PathVariable("id") Long id) {
		this.revenueService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

}
