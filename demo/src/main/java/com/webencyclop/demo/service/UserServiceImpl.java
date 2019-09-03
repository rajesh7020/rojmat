package com.webencyclop.demo.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webencyclop.demo.model.User;
import com.webencyclop.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		Optional<User> u = userRepository.findById(user.getId());
		if(u.isPresent()) {
			User newUser = u.get();
			newUser.setEmail(user.getEmail());
			newUser.setLastName(user.getLastName());
			newUser.setMobile(user.getMobile());
			newUser.setPassword(user.getPassword());
			newUser.setStatus("Unverified");
			newUser.setRoles(user.getRoles());
			newUser = userRepository.save(newUser);
			return newUser;
		}else {
			user = userRepository.save(user);
			return user;
		}
	}
	
}
