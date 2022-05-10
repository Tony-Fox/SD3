package com.example.sd2.service;

import com.example.sd2.dtos.ProductDTO;
import com.example.sd2.entity.Product;
import com.example.sd2.entity.Restaurant;
import com.example.sd2.repository.RestaurantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

	@BeforeEach
	void setUp() {

	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void save() {

	}

	@Test
	void findAll() {

	}

	@Test
	void findById() {

	}

	@Test
	void toDTO() {
		ProductService productService = new ProductService();
		Restaurant restaurant = Mockito.mock(Restaurant.class);
		when(restaurant.getRestaurantId()).thenReturn(1L);
		Product testProduct = new Product(
				"burger",
				"good",
				"Lunch",
				2L,
				restaurant);
		assertEquals("burger", productService.toDTO(testProduct).getName());
	}

	@Test
	void toDTOs() {
		ProductService productService = new ProductService();
		List<Product> products = new ArrayList<>();
		Restaurant restaurant = Mockito.mock(Restaurant.class);
		when(restaurant.getRestaurantId()).thenReturn(1L);
		Product testProduct = new Product(
				"burger",
				"good",
				"Lunch",
				2L,
				restaurant);
		products.add(testProduct);
		products.add(testProduct);

		assertEquals(productService.toDTO(testProduct).getName(), productService.toDTOs(products).get(0).getName());
		assertEquals(productService.toDTOs(products).size(), 2);
}

	@Test
	void toProduct() {
		RestaurantService service = Mockito.mock(RestaurantService.class);
		Restaurant restaurant = Mockito.mock(Restaurant.class);
		when(service.findById(1L)).thenReturn(restaurant);


		ProductService productService = new ProductService(service);


		when(restaurant.getRestaurantId()).thenReturn(1L);
		ProductDTO testProductDTO = new ProductDTO(
				1L,
				"burger",
				"good",
				"Lunch",
				2L,
				1L);
		assertEquals("burger", productService.toProduct(testProductDTO).getName());

	}
}