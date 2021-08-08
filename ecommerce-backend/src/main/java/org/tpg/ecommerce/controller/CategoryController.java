package org.tpg.ecommerce.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.tpg.ecommerce.model.Category;
import org.tpg.ecommerce.repository.CategoryRepository;

@RestController
public class CategoryController {
	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping(value = "/categories")
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	
	@GetMapping(value = "/category/{categoryId}")
	public ResponseEntity<?> getProductById(@PathVariable int categoryId) throws IOException {
		Optional<Category> category = categoryRepository.findById(categoryId);
		if (category.isPresent()) {
			return new ResponseEntity<Category>(category.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}
