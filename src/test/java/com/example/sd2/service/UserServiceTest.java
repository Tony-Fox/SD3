package com.example.sd2.service;

import com.example.sd2.dtos.UserDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

	@Test
	void toUser() {
		UserService userService = new UserService();

		UserDTO testUserDTO = new UserDTO(
				"user",
				"user"
		);

		assertEquals("user", userService.toUser(testUserDTO).getUsername());

	}
}