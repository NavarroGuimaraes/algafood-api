package com.csc.algafood.jpa.state;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.csc.algafood.AlgafoodApiApplication;
import com.csc.algafood.domain.model.State;
import com.csc.algafood.domain.repository.StateRepository;

public class FindAllStatesTest {
	
	public static void main(String[] args) {
		ApplicationContext context = 
				new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		StateRepository repository = context.getBean(StateRepository.class);
		List<State> allStates = repository.findAll();
		for (State s : allStates) {
			System.out.printf("%s - %s"+System.lineSeparator()
					, s.getId()
					, s.getName());
		}
	}

}
