package com.kuroshan.workshop.ms.hr.zuul.server.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.lang.reflect.Array;
import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Value("${security.oauth.jwt.key}")
  private String jwtKey;

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.tokenStore(tokenStore());
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests() 
        .antMatchers("/api/hr/authentication/oauth/**").permitAll()
        .antMatchers(HttpMethod.GET, "/api/hr/areas/departments").permitAll()
        .antMatchers(HttpMethod.GET, "/api/hr/authorization/users").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and()
        .cors().configurationSource(corsConfigurationSource());
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfig = new CorsConfiguration();
    corsConfig.addAllowedOrigin("*");
    corsConfig.setAllowedMethods(Arrays.asList(
        HttpMethod.GET.name(),
        HttpMethod.POST.name(),
        HttpMethod.PUT.name(),
        HttpMethod.DELETE.name(),
        HttpMethod.OPTIONS.name()
        ));
    corsConfig.setAllowCredentials(true);
    corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfig);

    return source;
  }

  @Bean
  public FilterRegistrationBean<CorsFilter> corsFilter() {
    FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
    filter.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return filter;
  }

  @Bean
  public JwtTokenStore tokenStore() {
    return new JwtTokenStore(accessTokenConverter());
  }

  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
    tokenConverter.setSigningKey(this.jwtKey);
    return tokenConverter;
  }

}