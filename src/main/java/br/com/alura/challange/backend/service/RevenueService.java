package br.com.alura.challange.backend.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.alura.challange.backend.domain.Revenue;
import br.com.alura.challange.backend.domain.request.RevenueRequest;
import br.com.alura.challange.backend.domain.response.RevenueResponse;
import br.com.alura.challange.backend.repository.RevenueRepository;
import br.com.alura.challange.backend.validations.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("RevenueService")
@AllArgsConstructor
@Validated
@Slf4j
public class RevenueService {

	private RevenueRepository revenueRepository;

	@Validated
	public RevenueResponse createRevenue(@Valid RevenueRequest revenueRequest) {

		this.revenueRepository.findByDescriptionAndValueAndDate(revenueRequest.getDescription(),
				revenueRequest.getValue(), revenueRequest.getDate()).ifPresent(d -> {
					throw Message.DESCRIPTION_EXISTS.asBusinessException();

				});

		Revenue revenue = Revenue.of(revenueRequest);

		this.revenueRepository.save(revenue);

		log.info("method=createRevenue Id={} description={} value={} date={}", revenue.getId(),
				revenue.getDescription(), revenue.getValue(), revenue.getDate());

		return revenue.toResponse();
	}

	public Page<RevenueResponse> listAllRevenue(int page, int limit, String description) {

		log.info("method=listAllRevenue");

		Pageable pageable = PageRequest.of(page, limit);

		log.info("method=findAllVideoFree limit{}", limit);
		return this.revenueRepository.listAllRevenue(description, pageable);

	}

	@Validated
	@Transactional
	public RevenueResponse updateRevenue(Long id, @Valid RevenueRequest revenueRequest) {
		Revenue revenue = this.revenueRepository.findById(id)
				.orElseThrow(() -> Message.NOT_FOUND_ID.asBusinessException());

		this.revenueRepository.findByDescriptionAndValueAndDate(revenueRequest.getDescription(),
				revenueRequest.getValue(), revenueRequest.getDate()).ifPresent(d -> {
					throw Message.DESCRIPTION_EXISTS.asBusinessException();

				});

		revenue.update(revenueRequest);

		log.info("method=updateRevenue id={} description={} value={} date={}", revenue.getId(),
				revenue.getDescription(), revenue.getValue(), revenue.getDate());

		return revenue.toResponse();

	}

	public List<RevenueResponse> listByRevenueMonth(Integer year, Integer month) {

		List<RevenueResponse> revenue = this.revenueRepository.findByDateAndYear(year, month);

		return revenue;
	}

	public RevenueResponse findById(Long id) {
		Revenue revenue = this.revenueRepository.findById(id)
				.orElseThrow(() -> Message.NOT_FOUND_ID.asBusinessException());

		log.info("method=findById videoId={}", id);

		return revenue.toResponse();
	}

	public void delete(Long id) {
		Revenue revenue = this.revenueRepository.findById(id)
				.orElseThrow(() -> Message.NOT_FOUND_ID.asBusinessException());

		log.info("method=delete id={}", id);

		this.revenueRepository.delete(revenue);

	}

}
