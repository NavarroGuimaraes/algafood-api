package com.csc.algafood.jpa.restaurant;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.csc.algafood.AlgafoodApiApplication;
import com.csc.algafood.domain.model.Restaurant;
import com.csc.algafood.domain.repository.RestaurantRepository;

public class FindAllRestaurantsTest {

	public static void main(String[] args) {
		ApplicationContext restaurantContext = 
				new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestaurantRepository repository = restaurantContext.getBean(RestaurantRepository.class);
		
		List<Restaurant> allRestaurants = repository.findAll();
		for (Restaurant r: allRestaurants) {
			System.out.printf("%s - %s - %f - %s\n"
					,r.getId()
					,r.getName()
					,r.getDeliveryFee()
					,r.getCookery().getName());
		}
	}
	
}
