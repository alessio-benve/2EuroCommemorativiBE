package com.dueeuro.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

@SuppressWarnings("deprecation")
public class InfoAddizionaleToken implements TokenEnhancer{
	/*da implementare nel caso vuole più info sul token*/
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String,Object> info = new HashMap<>();
		info.put("moreInfo","alessio sei nCoglione");
		return null;
	}

}
