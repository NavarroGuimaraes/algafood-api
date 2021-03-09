package com.csc.algafood.jpa.restaurant;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.csc.algafood.AlgafoodApiApplication;
import com.csc.algafood.domain.model.Restaurant;
import com.csc.algafood.domain.repository.RestaurantRepository;

public class CreateRestaurantTest {

	public static void main(String[] args) {
		ApplicationContext restaurantContext = 
				new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestaurantRepository cc = restaurantContext.getBean(RestaurantRepository.class);
		
		Restaurant restaurant = Restaurant.builder().name("Pizzaria Atlântico").deliveryFee(new BigDecimal(0.5)).build();
		
		restaurant = cc.save(restaurant);
		
		Restaurant restaurant_2 = Restaurant.builder().name("Tay San").deliveryFee(new BigDecimal(4.5)).build();
		
		restaurant_2 = cc.save(restaurant_2);
		
		System.out.printf("%s - %s - %f\n"
				, restaurant.getId()
				, restaurant.getName()
				, restaurant.getDeliveryFee());
		
		System.out.printf("%s - %s - %f\n"
				, restaurant_2.getId()
				, restaurant_2.getName()
				, restaurant_2.getDeliveryFee());
	
	}
	
}
