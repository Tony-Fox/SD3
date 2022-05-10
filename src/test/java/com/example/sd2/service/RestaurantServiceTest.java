package com.example.sd2.service;

import com.example.sd2.dtos.RestaurantDTO;
import com.example.sd2.entity.Product;
import com.example.sd2.entity.Restaurant;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RestaurantServiceTest {


	@Test
	void toDTO() {
		RestaurantService restaurantService = new RestaurantService();
		Restaurant testRestaurant = new Restaurant(
				"KFC",
				"Cluj",
				"1"
		);
		assertEquals("KFC", restaurantService.toDTO(testRestaurant).getName());
	}

	@Test
	void toDTOs() {
		List<Restaurant> restaurants = new ArrayList<>();
		RestaurantService restaurantService = new RestaurantService();
		Restaurant testRestaurant = new Restaurant(
				"KFC",
				"Cluj",
				"1"
		);
		assertEquals("KFC", restaurantService.toDTO(testRestaurant).getName());

		restaurants.add(testRestaurant);
		restaurants.add(testRestaurant);

		assertEquals(restaurantService.toDTO(testRestaurant).getName(), restaurantService.toDTOs(restaurants).get(0).getName());
		assertEquals(restaurantService.toDTOs(restaurants).size(), 2);

	}

	@Test
	void toRestaurant() {
		RestaurantService restaurantService = new RestaurantService();
		RestaurantDTO testRestaurantDTO = new RestaurantDTO(
				1L,
				"KFC",
				"Cluj",
				"1"
		);
		assertEquals("KFC", restaurantService.toRestaurant(testRestaurantDTO).getName());

	}
}