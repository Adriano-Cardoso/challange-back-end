package br.com.alura.challange.backend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.challange.backend.feature.ExpenseScenarioFactory;
import br.com.alura.challange.backend.service.ExpenseService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ExpenseControllerTest {

	@MockBean
	private ExpenseService expenseSerive;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Buscar despesas por id válido")
	public void listById_WhenListIdIsValid_ExpectedOk() throws Exception {

		when(this.expenseSerive.findById(anyLong())).thenReturn(ExpenseScenarioFactory.EXPENSE_RESPONSE);

		this.mockMvc.perform(get("/despesas/1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	@DisplayName("Buscar despesas por id inválido")
	public void listById_WhenListIdIsInValid_ExpectedException() throws Exception {

		when(this.expenseSerive.findById(anyLong())).thenReturn(ExpenseScenarioFactory.EXPENSE_RESPONSE);

		this.mockMvc.perform(get("/despesas/10")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	@DisplayName("Atualizar despesas por id válido")
	public void update_WhenRevenueExists_ExpectedOk() throws Exception {

		when(this.expenseSerive.update(anyLong(), any())).thenReturn(ExpenseScenarioFactory.EXPENSE_RESPONSE);

		this.mockMvc.perform(put("/despesas/1").content(asJsonString(ExpenseScenarioFactory.EXPENSE_UPDATE_REQUEST))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	@DisplayName("Atualizar receita por INválido")
	public void update_WhenRevenueIdNotExists_ExpectedException() throws Exception {

		when(this.expenseSerive.update(anyLong(), any())).thenReturn(ExpenseScenarioFactory.EXPENSE_RESPONSE);

		this.mockMvc.perform(put("/despesas/10").content(asJsonString(ExpenseScenarioFactory.EXPENSE_UPDATE_REQUEST))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	@DisplayName("Criar uma nova despesa")
	public void createExpense_WhenRevenueValidationNotExistisMonth_ExpectedOk() throws Exception {

		when(this.expenseSerive.createExpense(any())).thenReturn(ExpenseScenarioFactory.EXPENSE_RESPONSE);

		this.mockMvc.perform(post("/despesas").content(asJsonString(ExpenseScenarioFactory.CREATE))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

	@Test
	@DisplayName("Deleta receita por id")
	public void delete_WhenRevenueIdIsInvalid_ExpectedOk() throws Exception {

		doNothing().when(expenseSerive).delete(4L);

		mockMvc.perform(delete("/receitas/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
