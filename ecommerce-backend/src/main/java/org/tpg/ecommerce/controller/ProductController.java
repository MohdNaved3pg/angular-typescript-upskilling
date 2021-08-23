package org.tpg.ecommerce.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.tpg.ecommerce.model.Product;
import org.tpg.ecommerce.repository.ProductRepository;

@RestController
@CrossOrigin
public class ProductController {
	@Autowired
	ProductRepository productRepository;

	@GetMapping(value = "/product/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImage(@PathVariable int id) throws IOException {
		InputStream in = new ClassPathResource("product-images/" + id + ".jpg").getInputStream();
		return IOUtils.toByteArray(in);
	}

	@GetMapping(value = "/products/category/{categoryId}")
	public List<Product> getProductByCategory(@PathVariable int categoryId) throws IOException {
		return productRepository.findAllByCategoryId(categoryId);
	}

	@GetMapping(value = "/product/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable int productId) throws IOException {
		Optional<Product> product = productRepository.findById(productId);
		if (product.isPresent()) {
			return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
