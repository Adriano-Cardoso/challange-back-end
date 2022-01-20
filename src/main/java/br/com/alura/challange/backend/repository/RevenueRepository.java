package br.com.alura.challange.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.challange.backend.domain.Revenue;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {

}
