package com.kuroshan.workshop.ms.hr.security.server.config.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private Environment env;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private InfoAdditionalToken infoAdditionalToken;

	// SPRING-SECURITY (6)
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		 security
	        .tokenKeyAccess("permitAll()") //POST: /oauth/token
	        .checkTokenAccess("isAuthenticated()");
	}

	// SPRING-SECURITY (5)
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
	    	.inMemory()
	        .withClient(env.getProperty("security.oauth.client.id"))
	        .secret(passwordEncoder.encode(env.getProperty("security.oauth.client.secrect")))
	        .scopes("read", "write")
	        .authorizedGrantTypes("password", "refresh_token")
	        .accessTokenValiditySeconds(3600)
	        .refreshTokenValiditySeconds(3600);
	}

	// SPRING-SECURITY (4)
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
	    tokenEnhancerChain
	        .setTokenEnhancers(Arrays.asList(this.infoAdditionalToken,
	                                         this.accessTokenConverter()));
		
		endpoints
			.authenticationManager(this.authenticationManager)
			.tokenStore(this.tokenStore())
			.accessTokenConverter(this.accessTokenConverter())
			.tokenEnhancer(tokenEnhancerChain); //info adicional
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(env.getProperty("security.oauth.jwt.key"));
		return tokenConverter;
	}

}
