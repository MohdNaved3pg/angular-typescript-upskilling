package org.tpg.ecommerce.service;

import java.util.Optional;

import org.tpg.ecommerce.model.Token;

public interface UserService {

	Token authenticateUser(String username, String password) throws Exception;

	Optional<Integer> getUserIdFromToken(String tokenStr);

}
