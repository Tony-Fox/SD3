package com.example.sd2.service;

import com.example.sd2.dtos.ProductDTO;
import com.example.sd2.dtos.RestaurantDTO;
import com.example.sd2.entity.Product;
import com.example.sd2.entity.Restaurant;
import com.example.sd2.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository repository;

	public void save(Restaurant restaurant){
		repository.save(restaurant);
	}

	public Restaurant findById(Long id){
		if(repository.findById(id).isPresent())
			return repository.findById(id).get();
		else {
			System.out.println("Invalid ID!");
			return null;
		}
	}

	public List<ProductDTO> getProducts(Long id) {
		ProductService productService = new ProductService();
		Restaurant restaurant = findById(id);
		return productService.toDTOs(restaurant.getProducts());
	}

	public RestaurantDTO toDTO (Restaurant restaurant) {
		return new RestaurantDTO(restaurant.getRestaurantId(), restaurant.getName(), restaurant.getLocation(), restaurant.getDeliveryZones());
	}

	public List<RestaurantDTO> toDTOs (List<Restaurant> restaurants) {
		List<RestaurantDTO> dtos = new ArrayList<>();
		for (Restaurant restaurant : restaurants) {
			dtos.add(toDTO(restaurant));
		}
		return dtos;
	}

	public List<RestaurantDTO> findAll(){
		return toDTOs(repository.findAll());
	}

	public Restaurant toRestaurant(RestaurantDTO restaurantDTO) {
		return new Restaurant(
				restaurantDTO.getName(),
				restaurantDTO.getLocation(),
				restaurantDTO.getDeliveryZones()
		);
	}

}
