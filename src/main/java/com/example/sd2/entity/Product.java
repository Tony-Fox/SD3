package com.example.sd2.entity;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;

	@Column
	private String name;

	@Column
	private String description;

	@Column
	private String category;

	@Column
	private Long price;

	@ManyToOne
	@JoinColumn
	private Restaurant restaurant;

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

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public Product(String name, String description, String category, Long price, Restaurant restaurant) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
		this.restaurant = restaurant;
	}

	public Product() {
	}
}
