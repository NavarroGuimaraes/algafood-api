package com.csc.algafood.jpa.restaurant;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.csc.algafood.AlgafoodApiApplication;
import com.csc.algafood.domain.model.Restaurant;
import com.csc.algafood.domain.repository.RestaurantRepository;

public class UpdateRestaurantTest {

	public static void main(String[] args) {
		ApplicationContext restaurantContext = 
				new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestaurantRepository cc = restaurantContext.getBean(RestaurantRepository.class);
		
		Restaurant restaurant = Restaurant.builder().id(1l).name("Pizzaria Atlântico").deliveryFee(new BigDecimal(0.5)).build();
		
		restaurant = cc.save(restaurant);
		
		System.out.printf("%s - %s - %f\n"
				, restaurant.getId()
				, restaurant.getName()
				, restaurant.getDeliveryFee());
		
	
	}
	
}
