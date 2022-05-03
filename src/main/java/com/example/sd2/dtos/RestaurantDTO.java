package com.example.sd2.dtos;


public class RestaurantDTO {


	private Long restaurantId;

	private String name;

	private String location;

	private String deliveryZones;

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

	public RestaurantDTO(Long restaurantId, String name, String location, String deliveryZones) {
		this.restaurantId = restaurantId;
		this.name = name;
		this.location = location;
		this.deliveryZones = deliveryZones;
	}

	public RestaurantDTO() {
	}

}
