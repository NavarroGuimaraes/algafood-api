package com.csc.algafood.jpa.cookery;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.csc.algafood.AlgafoodApiApplication;
import com.csc.algafood.domain.model.Cookery;
import com.csc.algafood.domain.repository.CookeryRepository;

public class UpdateCookeryTest {
	
	public static void main(String[] args) {
		
		ApplicationContext context = 
				new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CookeryRepository cm = context.getBean(CookeryRepository.class);
		
		Cookery c = Cookery.builder().id(1L).name("Brasileirinha").build();
		c = cm.save(c);
		System.out.printf("Id: %s - Name: %s", c.getId(), c.getName());
	}
}
