package com.csc.algafood.jpa.cookery;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.csc.algafood.AlgafoodApiApplication;
import com.csc.algafood.domain.model.Cookery;
import com.csc.algafood.domain.repository.CookeryRepository;

public class FindAllCookeriesTest {

	public static void main(String[] args) {
		ApplicationContext cookeryContext = 
				new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CookeryRepository cc = cookeryContext.getBean(CookeryRepository.class);
		
		List<Cookery> cookeries = cc.findAll();
		
		for (Cookery c : cookeries) {
			System.out.println(c.getName());
		}
	}
	
}
