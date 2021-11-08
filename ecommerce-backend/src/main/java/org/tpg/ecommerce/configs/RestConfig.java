package org.tpg.ecommerce.configs;

import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class RestConfig /*extends WebSecurityConfigurerAdapter  */{
    @Value("${jwt.public.key}")
	RSAPublicKey key;

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		
//		http.csrf().disable()
//			.formLogin().disable()
//			.logout().disable()
//			.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
//			;
//		
//		// @formatter:off
//		http.authorizeRequests((authz) -> authz.anyRequest().authenticated())
////			.httpBasic(Customizer.withDefaults())
//			.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
//			.exceptionHandling((exceptions) -> exceptions
//				.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
//				.accessDeniedHandler(new BearerTokenAccessDeniedHandler())
//			);
//		// @formatter:on
//	}
//
//	@Bean
//	JwtDecoder jwtDecoder() {
//		return NimbusJwtDecoder.withPublicKey(this.key).build();
//	}
}
