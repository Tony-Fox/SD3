package com.example.sd2.service;

import com.example.sd2.dtos.ProductDTO;
import com.example.sd2.dtos.RestaurantDTO;
import com.example.sd2.entity.Product;
import com.example.sd2.entity.Restaurant;
import com.example.sd2.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;

@Service
public class RestaurantService {


	private java.util.logging.Logger logger;


	public RestaurantService() {
		logger =  java.util.logging.Logger.getLogger(this.getClass().getName());
		FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler(this.getClass().getName() + ".log");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		logger.addHandler(fileHandler);
	}

	public RestaurantService(RestaurantRepository repository) {
		this.repository = repository;
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
	private RestaurantRepository repository;

	/**
	 * Saves a restaurant in the database
	 * @param restaurant the restaurant to save
	 */
	public void save(Restaurant restaurant){
		repository.save(restaurant);
		logger.info("Saved new restaurant: " + restaurant.getName());
	}

	/**
	 * Finds the restaurant by its id
	 * @param id the id of the restaurant to find
	 * @return the restaurant if it finds it
	 */
	public Restaurant findById(Long id){
		if(repository.findById(id).isPresent())
			return repository.findById(id).get();
		else {
			System.out.println("Invalid ID!");
			logger.warning("Invalid ID: " + id);
			return null;
		}
	}

	/**
	 * Returns all products sold in a given restaurant
	 * @param id the id of the restaurant
	 * @return the products of the restaurant
	 */
	public List<ProductDTO> getProducts(Long id) {
		ProductService productService = new ProductService();
		Restaurant restaurant = findById(id);
		return productService.toDTOs(restaurant.getProducts());
	}

	/**
	 * Converts a Restaurant to a RestaurantDTO
	 * @param restaurant the Restaurant to convert
	 * @return the converted RestaurantDTO
	 */
	public RestaurantDTO toDTO (Restaurant restaurant) {
		return new RestaurantDTO(restaurant.getRestaurantId(), restaurant.getName(), restaurant.getLocation(), restaurant.getDeliveryZones());
	}

	/**
	 * Converts Restaurants to RestaurantDTOs
	 * @param restaurants the Restaurants to convert
	 * @return the converted RestaurantDTOs
	 */
	public List<RestaurantDTO> toDTOs (List<Restaurant> restaurants) {
		List<RestaurantDTO> dtos = new ArrayList<>();
		for (Restaurant restaurant : restaurants) {
			dtos.add(toDTO(restaurant));
		}
		return dtos;
	}

	/**
	 * Finds all restaurants in the database
	 * @return the list of all restaurants
	 */
	public List<RestaurantDTO> findAll(){
		return toDTOs(repository.findAll());
	}

	/**
	 * Converts a RestaurantDTO to a Restaurant
	 * @param restaurantDTO the RestaurantDTO to convert
	 * @return the converted Restaurant
	 */
	public Restaurant toRestaurant(RestaurantDTO restaurantDTO) {
		return new Restaurant(
				restaurantDTO.getName(),
				restaurantDTO.getLocation(),
				restaurantDTO.getDeliveryZones()
		);
	}

}
