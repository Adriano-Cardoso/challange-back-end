package br.com.alura.challange.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alura.challange.backend.domain.dto.response.RevenueResponse;
import br.com.alura.challange.backend.exception.BusinessException;
import br.com.alura.challange.backend.feature.RevenueScenarioFactory;
import br.com.alura.challange.backend.repository.RevenueRepository;

@ExtendWith(MockitoExtension.class)
public class RevenueServiceTest {

	@InjectMocks
	private RevenueService revenueService;

	@Mock
	private RevenueRepository revenueRepository;

	@Test
	@DisplayName("Listar todas receitas")
	void listAllRevenue_WhenListValid_ExpectedOk() {

		when(this.revenueRepository.listAllRevenue(anyString(), any(Pageable.class)))
				.thenReturn(RevenueScenarioFactory.PAGE_REVENUE);

		Page<RevenueResponse> listAllRevenue = this.revenueService.listAllRevenue(0, 10, "te");

		assertNotNull(listAllRevenue);

		assertEquals(RevenueScenarioFactory.PAGE_REVENUE, listAllRevenue);

		verify(revenueRepository).listAllRevenue(any(), any());
	}

	@Test
	@DisplayName("Listar receita por mes e ano")
	void listByRevenueYearAndMonth_WhenIsValid_ExpectedOk() {
		
		when(this.revenueRepository.listByRevenueYearAndMonth(any(), any(), any())).thenReturn(RevenueScenarioFactory.PAGE_REVENUE);
		
		Page<RevenueResponse> listByYearAndMonth = this.revenueService.listByRevenueYearAndMonth(0, 10, 2022, 02);
		
		assertNotNull(listByYearAndMonth);
		
		verify(revenueRepository).listByRevenueYearAndMonth(any(), any(), any());
	}
	
	@Test
	@DisplayName("Listar id que existe na base")
	void listByIdRevenue_WhenIsvalid_ExpectedOk() {

		when(this.revenueRepository.findById(anyLong())).thenReturn(Optional.of(RevenueScenarioFactory.REVENUE));

		this.revenueService.findById(1L);

		verify(revenueRepository).findById(any());

	}

	@Test
	@DisplayName("Listar id que não existe na base")
	void listByIdRevenue_WhenIdIsInValid_ExpectedException() {

		when(this.revenueRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(BusinessException.class, () -> this.revenueService.findById(7L));
	}

	@Test
	@DisplayName("Deletar id que existe na base")
	void deleteByIdRevenue_WhenIdIsInValid_ExpecetedOk() {

		when(this.revenueRepository.findById(anyLong())).thenReturn(Optional.of(RevenueScenarioFactory.REVENUE));

		this.revenueService.delete(1L);

		verify(revenueRepository).delete(any());
	}

	@Test
	@DisplayName("Deletar id que não existe na base")
	void deleteByIdRevenue_WhenIdIsInValid_ExpectedException() {

		when(this.revenueRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(BusinessException.class, () -> this.revenueService.delete(7L));

	}

	@Test
	@DisplayName("Atualizar receita, com validação de id e descrição corretos!")
	void updateRevenue_WhenIdIsValid_ExpectedOk() {

		when(this.revenueRepository.findById(any())).thenReturn(Optional.of(RevenueScenarioFactory.REVENUE));

		when(this.revenueRepository.findByValueAndDate(any(), any(), any())).thenReturn(Optional.empty());

		RevenueResponse revenueResponse = this.revenueService.updateRevenue(1L, RevenueScenarioFactory.REVENUE_UPDATE_REQUEST);

		assertNotNull(revenueResponse);

	}

	@Test
	@DisplayName("Atualizar receita, com validação de id e inválido")
	void updateRevenue_WheIdIsInvalid_ExpectedOk() {

		when(this.revenueRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(BusinessException.class,
				() -> revenueService.updateRevenue(10L, RevenueScenarioFactory.REVENUE_UPDATE_REQUEST));
	}

	@Test
	@DisplayName("Atualizar receita, com validação descrição e data e inválido")
	void updateRevenue_WhenValidationDescripionDateInValid_ExpectdException() {

		when(this.revenueRepository.findById(any())).thenReturn(Optional.of(RevenueScenarioFactory.REVENUE));

		when(this.revenueRepository.findByValueAndDate(any(), any(), any()))
				.thenReturn(Optional.of(RevenueScenarioFactory.REVENUE_RESPONSE));

		assertThrows(BusinessException.class,
				() -> revenueService.updateRevenue(1L, RevenueScenarioFactory.REVENUE_UPDATE_REQUEST));
	}

	@Test
	@DisplayName("Criar receita, com validação descrição e data e válida")
	void createRevenue_WhenValidationDescripitionAndDateIsValid_ExpectedOk() {

		when(this.revenueRepository.findByValueAndDate(any(), any(), any())).thenReturn(Optional.empty());

		RevenueResponse revenueResponse = this.revenueService.createRevenue(RevenueScenarioFactory.CREATE);

		assertNotNull(revenueResponse);

	}

	@Test
	@DisplayName("Criar receita, com validação descrição e data e inválido")
	void createRevenue_WhenValidationDescripitionAndDateIsInValid_ExpectedException() {
		when(this.revenueRepository.findByValueAndDate(any(), any(), any()))
				.thenReturn(Optional.of(RevenueScenarioFactory.REVENUE_RESPONSE));

		assertThrows(BusinessException.class,
				() -> this.revenueService.createRevenue(RevenueScenarioFactory.CREATE));

	}

}
