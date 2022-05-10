package com.example.sd2.service;

import com.example.sd2.dtos.ProductDTO;
import com.example.sd2.entity.Product;
import com.example.sd2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;

@Service
public class ProductService {

	private java.util.logging.Logger logger;

	public ProductService() {
		logger =  java.util.logging.Logger.getLogger(this.getClass().getName());
		FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler(this.getClass().getName() + ".log");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		logger.addHandler(fileHandler);
	}

	public ProductService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
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
	private ProductRepository repository;

	@Autowired
	private RestaurantService restaurantService;

	/**
	 * Saves a product in the database
	 * @param product The product to save
	 */
	public void save(Product product){
		repository.save(product);
		logger.info("Saved new product: " + product.getName());
	}

	/**
	 * Returns all products as dtos
	 * @return all products as dtos
	 */
	public List<ProductDTO> findAll(){
		return toDTOs(repository.findAll());
	}

	/**
	 * Finds the product by its ID
	 * @param id the id of the product to find
	 * @return the product if it finds it
	 */
	public Product findById(Long id){
		if(repository.findById(id).isPresent())
			return repository.findById(id).get();
		else {
			System.out.println("Invalid ID!");
			logger.warning("Invalid ID: " + id);
			return null;
		}
	}

	/**
	 * Converts a Product to a ProductDTO
	 * @param product the product to convert
	 * @return the productDTO
	 */
	public ProductDTO toDTO (Product product) {
		return new ProductDTO(product.getProductId(), product.getName(), product.getDescription(), product.getCategory(), product.getPrice(), product.getRestaurant().getRestaurantId() );
	}

	/**
	 * Converts Products to ProductDTOs
	 * @param products the products to convert
	 * @return the productDTOs
	 */
	public List<ProductDTO> toDTOs (List<Product> products) {
		List<ProductDTO> dtos = new ArrayList<>();
		for (Product product : products) {
			dtos.add(toDTO(product));
		}
		return dtos;
	}

	/**
	 * Converts a productDTO to a product
	 * @param productDTO the productDTO to convert
	 * @return the converted product
	 */
	public Product toProduct (ProductDTO productDTO) {
		return new Product(
				productDTO.getName(),
				productDTO.getDescription(),
				productDTO.getCategory(),
				productDTO.getPrice(),
				restaurantService.findById(productDTO.getRestaurantId())
				
		);
	}

}
