package com.example.sd2.controller;


import com.example.sd2.dtos.UserDTO;
import com.example.sd2.entity.Token;
import com.example.sd2.entity.User;
import com.example.sd2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.logging.FileHandler;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

	private java.util.logging.Logger logger;
	public UserController(){

		logger =  java.util.logging.Logger.getLogger(this.getClass().getName());
		FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler(this.getClass().getName() + ".log");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		logger.addHandler(fileHandler);
	}

	@Autowired
	private UserService service;

	@PostMapping("/register")
	public ResponseEntity registerUser(@RequestBody UserDTO userDTO) {

		service.save(service.toUser(userDTO));
		logger.info("saved user: " + userDTO.getUsername());
		return ResponseEntity.noContent().build();
	}


	@GetMapping("/createadmin")
	public void createAdmin() {
		logger.warning("Created new admin account!");
		service.save(new User("admin", "admin", true));
	}




	@PostMapping("/login2")
	public ResponseEntity login(@RequestBody UserDTO dto){
		System.out.println(dto.getUsername()+dto.getPassword());

		logger.info("Logging in " + dto.getUsername()+dto.getPassword());

		if(service.checkCredential(dto.getUsername(), dto.getPassword())) {
			if (service.findByUsername(dto.getUsername()).isAdmin())
				return ResponseEntity.ok(new Token("administrator"));
			return ResponseEntity.ok(new Token("normal"));

		}
		else
			return ResponseEntity.badRequest().build();
	}


}
