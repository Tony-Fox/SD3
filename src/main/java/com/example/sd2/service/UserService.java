package com.example.sd2.service;

import com.example.sd2.dtos.UserDTO;
import com.example.sd2.entity.Restaurant;
import com.example.sd2.entity.User;
import com.example.sd2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



	@Autowired
	UserRepository userRepository;

	public void save(User user){
		userRepository.save(new User(
				user.getUsername(),
				passwordEncoder.encode(user.getPassword()),
				user.isAdmin()
		));
	}


	public User findByUsername(String username){
		List<User> userList = userRepository.findAll();
		for(User user : userList){
			if(user.getUsername().equals(username))
				return user;
		}
		return null;
	}




	public User toUser(UserDTO userDTO) {
		return new User(
				userDTO.getUsername(),
				userDTO.getPassword(),
				false
		);
	}

	public boolean checkCredential(String username, String password) {
		return (passwordEncoder.matches(password, findByUsername(username).getPassword()));
	}

}
