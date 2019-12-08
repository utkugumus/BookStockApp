package com.kafein.bookstockapp.service;

import com.kafein.bookstockapp.model.User;

public interface UserService {
	
	void save(User user);

	User findByUsername(String username);
	
}