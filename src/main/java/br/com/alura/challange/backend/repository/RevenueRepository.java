package br.com.alura.challange.backend.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.challange.backend.domain.Revenue;
import br.com.alura.challange.backend.domain.response.RevenueResponse;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {

	@Query("select new br.com.alura.challange.backend.domain.response.RevenueResponse(r.id,r.description,r.value, r.date) "
			+ "From Revenue r " + "where r.description=:description" + " AND r.value=:value" + " AND r.date=:date")
	Optional<RevenueResponse> findByDescriptionAndValueAndDate(@Param(value = "description") String description,
			@Param(value = "value") BigDecimal value, @Param("date") LocalDate date);

	@Query("select new br.com.alura.challange.backend.domain.response.RevenueResponse(r.id,r.description,r.value, r.date) From Revenue r WHERE (: description is null or r.description like %:description% )")
	Page<RevenueResponse> listAllRevenue(@Param(value = "description") String description, Pageable pageable);

//    String jpql = "select mov from Automovel mov where day(data)=day(current_date()) and month(data)=month(current_date())";

	@Query("select new br.com.alura.challange.backend.domain.response.RevenueResponse(r.id,r.description,r.value, r.date) "
			+ "From Revenue r WHERE YEAR(date)=:year AND MONTH(date)=:month")
	List<RevenueResponse> findByDateAndYear(@Param("year") Integer year, @Param("month") Integer month);
	
//	 @Query("select new br.com.alura.challengebackend.dto.GastoPorCategoriaDto(d.categoria, sum(d.valor)) from Despesa d " +
//	            " where YEAR(d.data) = :ano and MONTH(d.data) = :mes " +
//	            " group by d.categoria")
//	    List<GastoPorCategoriaDto> gastosPorCategoriaNoMes(Integer ano, Integer mes);

}
