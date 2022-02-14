package br.com.alura.challange.backend.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.challange.backend.domain.Revenue;
import br.com.alura.challange.backend.domain.dto.response.RevenueResponse;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {

	@Query("select new br.com.alura.challange.backend.domain.dto.response.RevenueResponse(r.id,r.description,r.value, r.date) "
			+ "From Revenue r " + "where r.description=:description" + " AND r.value=:value" + " AND r.date=:date")
	Optional<RevenueResponse> findByValueAndDate(@Param(value = "description") String description,
			@Param(value = "value") BigDecimal value, @Param("date") LocalDate date);

	@Query("select new br.com.alura.challange.backend.domain.dto.response.RevenueResponse(r.id,r.description,r.value, r.date) From Revenue r WHERE (: description is null or r.description like %:description% )")
	Page<RevenueResponse> listAllRevenue(@Param(value = "description") String description, Pageable pageable);


	@Query("select new br.com.alura.challange.backend.domain.dto.response.RevenueResponse(r.id,r.description,r.value, r.date) "
			+ "From Revenue r WHERE (: year is null or YEAR(date)=:year)  AND (: month is null or MONTH(date)=:month)")
	Page<RevenueResponse> listByRevenueYearAndMonth(Pageable pageable, @Param("year") Integer year, @Param("month") Integer month);
	
	@Query("select sum(d.value) from Revenue d where YEAR(d.date) = :ano and MONTH(d.date) = :mes")
    Optional<BigDecimal> sumMoth(Integer ano, Integer mes);
	

}
