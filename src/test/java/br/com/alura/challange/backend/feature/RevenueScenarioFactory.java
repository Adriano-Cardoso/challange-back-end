package br.com.alura.challange.backend.feature;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.alura.challange.backend.domain.Revenue;
import br.com.alura.challange.backend.domain.dto.request.RevenueRequest;
import br.com.alura.challange.backend.domain.dto.response.RevenueResponse;

public class RevenueScenarioFactory {

	public static final Page<RevenueResponse> LIST_ALL = loadListAll();
	public static final Revenue REVENUE = loadRevenue();
	public static final RevenueResponse REVENUE_RESPONSE = loadRevenueResponse();
	public static final RevenueRequest REVENUE_REQUEST = loadRevenuUpdate();
	public static final RevenueRequest CREATE = loadCreate();
	public static final RevenueRequest CREATE_REVENUE = loadCreateRevenue();
	public static final Page<RevenueResponse> PAGE_REVENUE = loadPageRevenue();

	private static Page<RevenueResponse> loadListAll() {

		PageRequest page = PageRequest.of(0, 10);

		RevenueResponse revenueResponse = new RevenueResponse(1L, "teste", BigDecimal.TEN, LocalDate.now());

		List<RevenueResponse> list = new ArrayList<>();

		list.add(revenueResponse);

		return new PageImpl<>(list, page, 10);
	}

	private static Page<RevenueResponse> loadPageRevenue() {
		PageRequest page = PageRequest.of(0, 10);

		RevenueResponse revenueResponse = new RevenueResponse(1L, "teste", BigDecimal.TEN, LocalDate.now());

		List<RevenueResponse> list = new ArrayList<>();

		list.add(revenueResponse);

		return new PageImpl<>(list, page, 10);
	}

	private static RevenueRequest loadCreateRevenue() {
		return RevenueRequest.builder().description("receita 01").value(BigDecimal.TEN).date(LocalDate.now()).build();
	}

	private static RevenueRequest loadCreate() {
		RevenueRequest revenueRequest = new RevenueRequest("teste", BigDecimal.ONE, LocalDate.now());
		return revenueRequest;
	}

	private static RevenueResponse loadRevenueResponse() {
		RevenueResponse revenueResponse = new RevenueResponse(1L, "teste 01", BigDecimal.TEN, LocalDate.now());
		return revenueResponse;
	}

	private static RevenueRequest loadRevenuUpdate() {
		return RevenueRequest.builder().description("testes").value(BigDecimal.ZERO).date(LocalDate.now()).build();
	}

	private static Revenue loadRevenue() {
		return Revenue.builder().id(1L).description("Salario").value(BigDecimal.TEN).date(LocalDate.now()).build();
	}


}
