package org.tpg.ecommerce.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tpg.ecommerce.model.Token;
import org.tpg.ecommerce.model.User;
import org.tpg.ecommerce.repository.TokenRepository;
import org.tpg.ecommerce.repository.UserRepository;
import org.tpg.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TokenRepository tokenRepo;

	@Override
	public Token authenticateUser(String username, String password) throws Exception {
		return userRepo.findByUsername(username)
				.filter(user -> user.getPassword().equals(password))
				.map(this::tokenProvider)
				.orElseThrow(() -> new Exception("Invalid login details"));
	}

	private Token tokenProvider(User user) {
		String tokenStr = UUID.randomUUID().toString();
		Token token = new Token(tokenStr, user.getUser_id());
		return tokenRepo.save(token);
	}

	@Override
	public Optional<Integer> getUserIdFromToken(String tokenStr) {
		return tokenRepo.findByTokenStr(tokenStr)
				.map(Token::getUserId);
	}

}
