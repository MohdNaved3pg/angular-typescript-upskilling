package org.tpg.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tpg.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query( "select o from Product o where o.category.id = :categoryId" )
	public List<Product> findAllByCategoryId(@Param("categoryId") int categoryId);
}
