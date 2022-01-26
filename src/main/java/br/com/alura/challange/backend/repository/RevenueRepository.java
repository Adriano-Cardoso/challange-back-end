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
import br.com.alura.challange.backend.domain.response.RevenueResponse;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {
	
	
	
	@Query("select new br.com.alura.challange.backend.domain.response.RevenueResponse(r.id,r.description,r.value, r.date) "
			+ "From Revenue r "
			+ "where r.description=:description"
			+ " AND r.value=:value"
			+ " AND r.date=:date")
	Optional<RevenueResponse> findByDescriptionAndValueAndDate(@Param(value = "description")String description, @Param(value = "value") BigDecimal value, @Param("date") LocalDate date);
	
	
	@Query("select new br.com.alura.challange.backend.domain.response.RevenueResponse(r.id,r.description,r.value, r.date)"
			+ " From Expense r"
			+ " where r.description=:description")
	Page<RevenueResponse> listAllRevenue(Pageable pageable,  @Param(value = "description")String description);

}
