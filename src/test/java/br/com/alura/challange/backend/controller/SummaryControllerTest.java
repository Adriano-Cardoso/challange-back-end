package br.com.alura.challange.backend.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import br.com.alura.challange.backend.feature.SummaryScenarioFactory;
import br.com.alura.challange.backend.service.SummariesService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SummaryControllerTest {
	
	@MockBean
	private SummariesService summariesService;

	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	@DisplayName("Resumo mensal")
	public void loadSummaryMonth_WhenIsValid_ExpectedOk() throws Exception {
		
		when(this.summariesService.summaryMonth(anyInt(), anyInt())).thenReturn(SummaryScenarioFactory.SUMMARY_RESPONSE);
		
		this.mockMvc.perform(get("/resumo/year/2022/month/2")).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

}
