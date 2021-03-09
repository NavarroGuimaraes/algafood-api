package com.csc.algafood.jpa.restaurant;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.csc.algafood.AlgafoodApiApplication;
import com.csc.algafood.domain.model.Restaurant;
import com.csc.algafood.domain.repository.RestaurantRepository;

public class RemoveRestaurantTest {

	public static void main(String[] args) {
		
		ApplicationContext restaurantContext = 
				new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestaurantRepository repository = restaurantContext.getBean(RestaurantRepository.class);		
		Restaurant restaurant = Restaurant.builder().id(2L).build();
		repository.delete(restaurant);
	
	}
	
}
