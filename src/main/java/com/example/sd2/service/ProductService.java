package com.example.sd2.service;

import com.example.sd2.dtos.ProductDTO;
import com.example.sd2.entity.Product;
import com.example.sd2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private RestaurantService restaurantService;

	public void save(Product product){
		repository.save(product);
	}

	public List<ProductDTO> findAll(){
		return toDTOs(repository.findAll());
	}

	public Product findById(Long id){
		if(repository.findById(id).isPresent())
			return repository.findById(id).get();
		else {
			System.out.println("Invalid ID!");
			return null;
		}
	}

	public ProductDTO toDTO (Product product) {
		return new ProductDTO(product.getProductId(), product.getName(), product.getDescription(), product.getCategory(), product.getPrice(), product.getRestaurant().getRestaurantId() );
	}

	public List<ProductDTO> toDTOs (List<Product> products) {
		List<ProductDTO> dtos = new ArrayList<>();
		for (Product product : products) {
			dtos.add(toDTO(product));
		}
		return dtos;
	}

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
