package br.com.alura.challange.backend.feature;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.challange.backend.domain.dto.response.SummaryByCategoryResponse;
import br.com.alura.challange.backend.domain.dto.response.SummaryResponse;
import br.com.alura.challange.backend.domain.enums.CategoryEnum;

public class SummaryScenarioFactory {

	public static final List<SummaryByCategoryResponse> LIST_SUMMARY = loadListSummary();
	public static final SummaryResponse SUMMARY_RESPONSE = loadSummaryResponse();

	private static List<SummaryByCategoryResponse> loadListSummary() {

		List<SummaryByCategoryResponse> list = new ArrayList<>();

		SummaryByCategoryResponse summaryByCategoryResponse = new SummaryByCategoryResponse(CategoryEnum.OUTRAS,
				BigDecimal.ZERO);

		list.add(summaryByCategoryResponse);

		return list;
	}

	private static SummaryResponse loadSummaryResponse() {
		return SummaryResponse.builder().totalRevenueExpense(BigDecimal.TEN).totalRevenueAmount(BigDecimal.TEN)
				.finalBalance(BigDecimal.TEN).spendingByCategories(new ArrayList<SummaryByCategoryResponse>()).build();
	}

}
