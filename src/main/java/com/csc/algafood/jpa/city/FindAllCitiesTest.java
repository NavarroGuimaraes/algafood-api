package com.csc.algafood.jpa.city;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.csc.algafood.AlgafoodApiApplication;
import com.csc.algafood.domain.model.City;
import com.csc.algafood.domain.repository.CityRepository;

public class FindAllCitiesTest {
	
	public static void main(String[] args) {
		ApplicationContext context = 
				new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CityRepository repository = context.getBean(CityRepository.class);
		List<City> allCities = repository.findAll();
		for (City c : allCities) {
			System.out.printf("%s - %s/%s"+System.lineSeparator()
					, c.getId()
					, c.getState().getName()
					, c.getName());
		}
	}

}
