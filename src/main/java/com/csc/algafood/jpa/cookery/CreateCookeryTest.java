package com.csc.algafood.jpa.cookery;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.csc.algafood.AlgafoodApiApplication;
import com.csc.algafood.domain.model.Cookery;
import com.csc.algafood.domain.repository.CookeryRepository;

public class CreateCookeryTest {

	public static void main(String[] args) {
		ApplicationContext cookeryContext = 
				new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CookeryRepository cc = cookeryContext.getBean(CookeryRepository.class);
		
		Cookery cookery = Cookery.builder().name("Chinesa").build();
		
		cookery = cc.save(cookery);
		
		Cookery cookery_2 = Cookery.builder().name("Tailândesa").build();
		
		cookery_2 = cc.save(cookery_2);
	
	}
	
}
