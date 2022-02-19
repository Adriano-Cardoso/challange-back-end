package br.com.alura.challange.backend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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

import br.com.alura.challange.backend.feature.RevenueScenarioFactory;
import br.com.alura.challange.backend.service.RevenueService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RevenueControllerTest {

	@MockBean
	private RevenueService revenueService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Listar todos os receitas")
	public void listAllRevenue_WhenListIsValid_ExpectedOk() throws Exception {

		when(this.revenueService.listAllRevenue(anyInt(), anyInt(), any())).thenReturn(RevenueScenarioFactory.LIST_ALL);

		this.mockMvc.perform(get("/receitas")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	@DisplayName("Listar receitas por mes e ano")
	public void listByRevenue_WhenListIsValid_ExpectedOk() throws Exception {

		when(this.revenueService.listByRevenueYearAndMonth(anyInt(), anyInt(), anyInt(), anyInt()))
				.thenReturn(RevenueScenarioFactory.PAGE_REVENUE);

		this.mockMvc.perform(get("/receitas/2/2022")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	@DisplayName("Buscar receita por id válido")
	public void listByIdRevenue_WhenListIdIsValid_ExpectedOk() throws Exception {

		when(this.revenueService.findById(anyLong())).thenReturn(RevenueScenarioFactory.REVENUE_RESPONSE);

		this.mockMvc.perform(get("/receitas/1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	@DisplayName("Buscar receita por id inválido")
	public void listByIdRevenue_WhenListIdIsInValid_ExpectedException() throws Exception {

		when(this.revenueService.findById(anyLong())).thenReturn(RevenueScenarioFactory.REVENUE_RESPONSE);

		this.mockMvc.perform(get("/receitas/10")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	@DisplayName("Atualizar receita por id válido")
	public void update_WhenRevenueExists_ExpectedOk() throws Exception {

		when(this.revenueService.updateRevenue(anyLong(), any())).thenReturn(RevenueScenarioFactory.REVENUE_RESPONSE);

		this.mockMvc.perform(put("/receitas/1").content(asJsonString(RevenueScenarioFactory.REVENUE_REQUEST))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	@DisplayName("Atualizar receita por id inválido")
	public void update_WhenRevenueIdNotExists_ExpectedException() throws Exception {

		when(this.revenueService.updateRevenue(anyLong(), any())).thenReturn(RevenueScenarioFactory.REVENUE_RESPONSE);

		this.mockMvc.perform(put("/receitas/1").content(asJsonString(RevenueScenarioFactory.REVENUE_REQUEST))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	@DisplayName("Criar uma nova receita")
	public void createRevenue_WhenRevenueValidationNotExistisMonth_ExpectedOk() throws Exception {

		when(this.revenueService.createRevenue(any())).thenReturn(RevenueScenarioFactory.REVENUE_RESPONSE);
		this.mockMvc.perform(post("/receitas").content(asJsonString(RevenueScenarioFactory.CREATE))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

	}

	@Test
	@DisplayName("Deleta receita por id")
	public void delete_WhenRevenueIdIsInvalid_ExpectedOk() throws Exception {

		doNothing().when(revenueService).delete(4L);

		mockMvc.perform(delete("/receitas/4").contentType(MediaType.APPLICATION_JSON))
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
