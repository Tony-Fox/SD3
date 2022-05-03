package com.example.sd2.controller;

import com.example.sd2.dtos.ProductDTO;
import com.example.sd2.dtos.RestaurantDTO;
import com.example.sd2.entity.Product;
import com.example.sd2.entity.Restaurant;
import com.example.sd2.service.ProductService;
import com.example.sd2.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.sd2.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProductController {

	@Autowired
	private ProductService service;

	@Autowired
	private RestaurantService restaurantService;



	@PostMapping("/product/add")
	public ResponseEntity addProduct(@RequestBody ProductDTO productDTO) {
		service.save(service.toProduct(productDTO));
		return ResponseEntity.noContent().build();
	}

//	@PostMapping("/restaurants/addfood")
//	public ResponseEntity addFood(@RequestParam String id, @RequestBody FoodDTO dto){
//		System.out.println(dto.getName()+dto.getCategory()+dto.getDescription()+dto.getPrice()+dto.getId()+id);
//		service.save(service.FromDto(dto,id));
//		return ResponseEntity.noContent().build();
//	}



	@GetMapping("/products")
	public ResponseEntity<List<ProductDTO>> findAll() {




		List<ProductDTO> products;


//		service.save(new Product("Apple", "Tasty", "Breakfast", 1L, restaurantService.findById(1L)));

		products = service.findAll();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Responded", "ProductController");
		return ResponseEntity.accepted().headers(httpHeaders).body(products);
	}


	@GetMapping("/product")
	public ResponseEntity<List<ProductDTO>> findById(@RequestParam String id) {

		List<ProductDTO> products;
		products = restaurantService.getProducts(Long.parseLong(id));
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Responded", "ProductController");
		return ResponseEntity.accepted().headers(httpHeaders).body(products);
	}

}
