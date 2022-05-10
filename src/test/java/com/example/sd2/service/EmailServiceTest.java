package com.example.sd2.service;

import com.example.sd2.dtos.ProductDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EmailServiceTest {



	@Test
	void sendMail() {
		EmailService emailService = new EmailService();
		ProductDTO productDTO = Mockito.mock(ProductDTO.class);
		when(productDTO.toString()).thenReturn("burger");
		emailService.sendMail(productDTO, "tonivulpe@yahoo.com");
	}
}