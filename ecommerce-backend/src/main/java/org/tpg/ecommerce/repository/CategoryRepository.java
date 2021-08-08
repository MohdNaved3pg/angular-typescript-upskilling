package org.tpg.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tpg.ecommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
