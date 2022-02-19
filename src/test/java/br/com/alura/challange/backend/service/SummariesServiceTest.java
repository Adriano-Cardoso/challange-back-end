package br.com.alura.challange.backend.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.challange.backend.domain.dto.response.SummaryResponse;
import br.com.alura.challange.backend.feature.SummaryScenarioFactory;
import br.com.alura.challange.backend.repository.ExpenseRepository;
import br.com.alura.challange.backend.repository.RevenueRepository;

@ExtendWith(MockitoExtension.class)
public class SummariesServiceTest {
	
	@InjectMocks
	private SummariesService summariesService;
	
	@Mock
	private ExpenseRepository expenseRepository;
	
	@Mock
	private RevenueRepository revenueRepository;
	
	@Test
	@DisplayName("Resumo mensal")
	void loadSummaryMonth_WhenIsValidExpectedOk() {
		
		when(this.expenseRepository.sumMoth(anyInt(), anyInt())).thenReturn(Optional.of(BigDecimal.ZERO));
		
		when(this.revenueRepository.sumMoth(anyInt(), anyInt())).thenReturn(Optional.of(BigDecimal.ZERO));
		
		when(this.expenseRepository.expensesByCategoryAtTheEndOfTheMonth(anyInt(), anyInt())).thenReturn(SummaryScenarioFactory.LIST_SUMMARY);
		
		
		SummaryResponse summaryResponse = this.summariesService.summaryMonth(2022, 02);
		
		
		assertNotNull(summaryResponse);
		
		verify(expenseRepository).sumMoth(any(), any());
		verify(revenueRepository).sumMoth(any(), any());
		verify(expenseRepository).expensesByCategoryAtTheEndOfTheMonth(any(), any());
	}
}
