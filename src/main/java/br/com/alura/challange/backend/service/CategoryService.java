package br.com.alura.challange.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.alura.challange.backend.domain.Category;
import br.com.alura.challange.backend.repository.CategoryRepository;
import br.com.alura.challange.backend.validations.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("CategoryService")
@AllArgsConstructor
@Validated
@Slf4j
public class CategoryService {
	
	private CategoryRepository categoryRepository;
	
	public Category findById(Long id) {
		Category category = this.categoryRepository.findById(id)
				.orElseThrow(() -> Message.CATEGORY_ID_NOT_FOUND.asBusinessException());

		log.info("method=findById id={}", id);

		return category;
	}


}
