package cn.roy.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.roy.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static Map<String,String> users = new HashMap<String,String>();
	static{
		users.put("admin", "password");
		users.put("roy", "roy");
	}
	
	@Override
	public boolean checkPassword(String username, String password) {
		return password.equals(users.get(username));
	}

}
