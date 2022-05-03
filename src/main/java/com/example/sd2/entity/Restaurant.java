package com.example.sd2.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long restaurantId;

	@Column
	private String name;

	@Column
	private String location;

	@Column
	private String deliveryZones;

	@OneToMany(mappedBy = "restaurant")
	private List<Product> products;



	public Long getRestaurantId() {
		return restaurantId;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public String getDeliveryZones() {
		return deliveryZones;
	}

	public List<Product> getProducts() {
		return products;
	}

	public Restaurant(String name, String location, String deliveryZones) {
		this.name = name;
		this.location = location;
		this.deliveryZones = deliveryZones;
	}


	public Restaurant() {
	}



}
