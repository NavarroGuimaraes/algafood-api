package com.csc.algafood.jpa.cookery;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.csc.algafood.AlgafoodApiApplication;
import com.csc.algafood.domain.model.Cookery;
import com.csc.algafood.domain.repository.CookeryRepository;

public class FindCookeryByIdTest {

	public static void main(String[] args) throws Exception {
		ApplicationContext cookeryContext = 
				new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CookeryRepository cc = cookeryContext.getBean(CookeryRepository.class);
		
		Cookery cookery = cc.findById(2L).orElseThrow(() -> new Exception());
		
		System.out.println(cookery.getName());
		
	}
	
}
