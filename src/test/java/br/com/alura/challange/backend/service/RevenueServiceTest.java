package br.com.alura.challange.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

import br.com.alura.challange.backend.domain.response.RevenueResponse;
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

		when(this.revenueRepository.listAllRevenue(any(Pageable.class))).thenReturn(RevenueScenarioFactory.LIST_ALL);

		Page<RevenueResponse> listAllRevenue = this.revenueService.listAllRevenue();

		assertNotNull(listAllRevenue);

		assertEquals(RevenueScenarioFactory.LIST_ALL, listAllRevenue);

		verify(revenueRepository).listAllRevenue(any());
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

		when(this.revenueRepository.findByDescriptionAndValue(any(), any(), any())).thenReturn(Optional.empty());

		RevenueResponse revenueResponse = this.revenueService.updateRevenue(1L, RevenueScenarioFactory.REVENUE_Request);

		assertNotNull(revenueResponse);

	}

	@Test
	@DisplayName("Atualizar receita, com validação de id e inválido")
	void updateRevenue_WheIdIsInvalid_ExpectedOk() {
		
		when(this.revenueRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(BusinessException.class,
				() -> revenueService.updateRevenue(10L, RevenueScenarioFactory.REVENUE_Request));
	}

	@Test
	@DisplayName("Atualizar receita, com validação descrição e data e inválido")
	void updateRevenue_WhenValidationDescripionDateInValid_ExpectdException() {
		
		when(this.revenueRepository.findById(any())).thenReturn(Optional.of(RevenueScenarioFactory.REVENUE));

		when(this.revenueRepository.findByDescriptionAndValue(any(), any(), any())).thenReturn(Optional.of(RevenueScenarioFactory.REVENUE_RESPONSE));
		
		assertThrows(BusinessException.class, () -> revenueService.updateRevenue(1L, RevenueScenarioFactory.REVENUE_Request));
	}
	
	@Test
	@DisplayName("Criar receita, com validação descrição e data e válida")
	void createRevenue_WhenValidationDescripitionAndDateIsValid_ExpectedOk() {
		
		when(this.revenueRepository.findByDescriptionAndValue(any(), any(), any())).thenReturn(Optional.empty());
		
		RevenueResponse revenueResponse = this.revenueService.createRevenue(RevenueScenarioFactory.CREATE);
		
		assertNotNull(revenueResponse);
		
	}
	
	@Test
	@DisplayName("Criar receita, com validação descrição e data e inválido")
	void createRevenue_WhenValidationDescripitionAndDateIsInValid_ExpectedException() {
		when(this.revenueRepository.findByDescriptionAndValue(any(), any(), any())).thenReturn(Optional.of(RevenueScenarioFactory.REVENUE_RESPONSE));
		
		assertThrows(BusinessException.class, ()-> this.revenueService.createRevenue(RevenueScenarioFactory.REVENUE_Request));
		
	}

}
