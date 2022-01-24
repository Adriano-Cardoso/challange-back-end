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
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.challange.backend.domain.request.ExpenseRequest;
import br.com.alura.challange.backend.domain.response.ExpenseResponse;
import br.com.alura.challange.backend.service.ExpenseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@Api(value = "Despesas Endpoint", description = "Endpoints da despesa", tags = { "Despesa Endpoint" })
@RestController
@AllArgsConstructor
@RequestMapping("/despesas")
public class ExpenseController {

	private ExpenseService expenseService;
	
	
	@ApiOperation(value = "Cria uma nova despesa")
	@PostMapping
	public ResponseEntity<ExpenseResponse> createRevenue(@RequestBody ExpenseRequest expenseRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.expenseService.createExpense(expenseRequest));

	}

	@ApiOperation(value = "Listagem de despesas")
	@GetMapping
	public ResponseEntity<Page<ExpenseResponse>> listAllExpense() {
		return ResponseEntity.status(HttpStatus.OK).body(this.expenseService.listAllExpense());
	}

	@ApiOperation(value = "Detalhamento de despesas")
	@GetMapping("/{id}")
	public ResponseEntity<ExpenseResponse> findById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.expenseService.findById(id));
	}

	@ApiOperation(value = "Atualização de despesas por id")
	@PutMapping("/{id}")
	public ResponseEntity<ExpenseResponse> update(@PathVariable("id") Long id,
			@RequestBody ExpenseRequest expenseRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(this.expenseService.update(id, expenseRequest));
	}
	
	@ApiOperation(value = "Exclusão de despesas por id")
	@DeleteMapping("/{id}")
	public ResponseEntity<ExpenseResponse> delete(@PathVariable("id") Long id) {
		this.expenseService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
