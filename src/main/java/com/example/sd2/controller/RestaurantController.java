package com.example.sd2.controller;

import com.example.sd2.dtos.RestaurantDTO;
import com.example.sd2.entity.Restaurant;
import com.example.sd2.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.sd2.repository.RestaurantRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RestaurantController {

	private java.util.logging.Logger logger;

	public RestaurantController() {
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
	private RestaurantService service;


	@GetMapping("/restaurants")
	public ResponseEntity<List<RestaurantDTO>> findAll(){



		List<RestaurantDTO> restaurants ;

		restaurants = service.findAll();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Responded", "RestaurantController");
		return ResponseEntity.accepted().headers(httpHeaders).body(restaurants);
	}


	@PostMapping("/restaurant/add")
	public ResponseEntity addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
		service.save(service.toRestaurant(restaurantDTO));
		logger.info("Created restaurant: " + restaurantDTO.getName());
		return ResponseEntity.noContent().build();
	}

//	@PostMapping("/restaurants/add")
//	public ResponseEntity addRestaurant(@RequestBody RestaurantDTO restaurantDTO){
//		System.out.println(restaurantDTO.getName()+restaurantDTO.getId()+restaurantDTO.getAddress()+restaurantDTO.getDescription());
//		service.save(restaurantDTO);
//		return ResponseEntity.noContent().build();
//	}

}
