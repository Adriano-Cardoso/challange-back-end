package br.com.alura.challange.backend.feature;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.alura.challange.backend.domain.Revenue;
import br.com.alura.challange.backend.domain.request.RevenueRequest;
import br.com.alura.challange.backend.domain.response.RevenueResponse;

public class RevenueScenarioFactory {

	public static final Page<RevenueResponse> LIST_ALL = loadListAll();
	public static final Revenue REVENUE = loadRevenue();
	public static final RevenueResponse REVENUE_RESPONSE = loadRevenueResponse();
	public static final RevenueRequest REVENUE_Request = loadRevenuUpdate();
	public static final RevenueRequest CREATE = loadCreate();

	private static Page<RevenueResponse> loadListAll() {

		PageRequest page = PageRequest.of(0, 10);

		RevenueResponse revenueResponse = new RevenueResponse(1l, "teste", BigDecimal.TEN, LocalDate.now());

		List<RevenueResponse> list = new ArrayList<>();

		list.add(revenueResponse);

		return new PageImpl<>(list, page, 5);
	}

	private static RevenueRequest loadCreate() {
		return RevenueRequest.builder().description("receita 01").value(BigDecimal.TEN).date(LocalDate.now()).build();
	}

	private static RevenueRequest loadRevenuUpdate() {
		return RevenueRequest.builder().description("Despesas teste").value(BigDecimal.TEN).date(LocalDate.now()).build();
	}

	private static RevenueResponse loadRevenueResponse() {
		return RevenueResponse.builder().id(1L).description("Salario").value(BigDecimal.TEN).date(LocalDate.of(2021, 10, 17))
				.build();
	}

	private static Revenue loadRevenue() {
		return Revenue.builder().id(1L).description("Salario").value(BigDecimal.TEN).date(LocalDate.of(2021, 10, 17))
				.build();
	}

}
