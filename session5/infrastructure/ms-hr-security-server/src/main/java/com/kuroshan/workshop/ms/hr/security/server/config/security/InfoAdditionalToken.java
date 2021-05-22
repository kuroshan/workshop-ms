package com.kuroshan.workshop.ms.hr.security.server.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import com.kuroshan.workshop.ms.hr.security.server.controllers.response.UserResponse;
import com.kuroshan.workshop.ms.hr.security.server.services.UserService;

//SPRING-SECURITY (7)
@Component
public class InfoAdditionalToken implements TokenEnhancer {

  @Autowired
  private UserService userService;

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    UserResponse user = userService.findByUsername(authentication.getName());
    Map<String, Object> info = new HashMap<String, Object>();
    info.put("email", user.getEmail());
    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
    return accessToken;
  }

}
