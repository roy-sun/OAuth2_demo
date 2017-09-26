package cn.roy.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.roy.service.OAuthService;

@Service
public class OAuthServiceImpl implements OAuthService {

	 private Map<String,String> cache = new HashMap<String,String>();

	    @Override
	    public void addAuthCode(String authCode, String username) {
	        cache.put(authCode, username);
	    }

	    @Override
	    public void addAccessToken(String accessToken, String username) {
	        cache.put(accessToken, username);
	    }

	    @Override
	    public String getUsernameByAuthCode(String authCode) {
	        return cache.get(authCode);
	    }

	    @Override
	    public String getUsernameByAccessToken(String accessToken) {
	        return cache.get(accessToken);
	    }

	    @Override
	    public boolean checkAuthCode(String authCode) {
	        return cache.get(authCode) != null;
	    }

	    @Override
	    public boolean checkAccessToken(String accessToken) {
	        return cache.get(accessToken) != null;
	    }

	    @Override
	    public long getExpireIn() {
	        return 3600L;
	    }
}
