package br.com.alura.challange.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.challange.backend.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	

}
