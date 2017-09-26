package cn.roy.service;

public interface OAuthService {
	public void addAuthCode(String authCode, String username);// 添加 auth code  
	public void addAccessToken(String accessToken, String username); // 添加 access token  
	boolean checkAuthCode(String authCode); // 验证auth code是否有效  
	boolean checkAccessToken(String accessToken); // 验证access token是否有效  
	String getUsernameByAuthCode(String authCode);// 根据auth code获取用户名  
	String getUsernameByAccessToken(String accessToken);// 根据access token获取用户名  
	long getExpireIn();//auth code / access token 过期时间  
}
