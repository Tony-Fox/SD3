package com.example.sd2.dtos;


public class ProductDTO {

	private Long productId;

	private String name;

	private String description;

	private String category;

	private Long price;

	private Long restaurantId;

	public ProductDTO(Long productId, String name, String description, String category, Long price, Long restaurantId) {
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
		this.restaurantId = restaurantId;
	}

	public ProductDTO() {
	}

	public Long getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getCategory() {
		return category;
	}

	public Long getPrice() {
		return price;
	}

	public Long getRestaurantId() { return restaurantId; }

	@Override
	public String toString() {
		return name;
	}

}
