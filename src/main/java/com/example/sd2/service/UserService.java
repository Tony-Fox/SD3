package com.example.sd2.service;

import com.example.sd2.dtos.UserDTO;
import com.example.sd2.entity.Restaurant;
import com.example.sd2.entity.User;
import com.example.sd2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.FileHandler;

@Service
public class UserService {

	private java.util.logging.Logger logger;
	public static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();




	@Autowired
	UserRepository userRepository;

	public UserService() {
		logger =  java.util.logging.Logger.getLogger(this.getClass().getName());
		FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler(this.getClass().getName() + ".log");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		logger.addHandler(fileHandler);
	}

	/**
	 * Saves a user in the database
	 * @param user the user to save
	 */
	public void save(User user){
		userRepository.save(new User(
				user.getUsername(),
				passwordEncoder.encode(user.getPassword()),
				user.isAdmin()
		));
		logger.info("Saved new user: " + user.getUsername());
	}


	/**
	 * Finds the user by its username
	 * @param username the username of the user to find
	 * @return the user if it finds it
	 */
	public User findByUsername(String username){
		List<User> userList = userRepository.findAll();
		for(User user : userList){
			if(user.getUsername().equals(username))
				return user;
		}
		return null;
	}


	/**
	 * Converts a UserDTO to a User
	 * @param userDTO the UserDTO to convert
	 * @return the converted User
	 */
	public User toUser(UserDTO userDTO) {
		return new User(
				userDTO.getUsername(),
				userDTO.getPassword(),
				false
		);
	}

	/**
	 * Checks the username and password with the database
	 * @param username the username to check
	 * @param password the password to check
	 * @return whether the username and password match any records in the database
	 */
	public boolean checkCredential(String username, String password) {
		return (passwordEncoder.matches(password, findByUsername(username).getPassword()));
	}

}
