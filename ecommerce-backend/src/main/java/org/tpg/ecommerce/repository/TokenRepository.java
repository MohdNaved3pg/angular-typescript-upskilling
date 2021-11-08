package org.tpg.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tpg.ecommerce.model.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {

	Optional<Token> findByTokenStr(String tokenStr);
}
