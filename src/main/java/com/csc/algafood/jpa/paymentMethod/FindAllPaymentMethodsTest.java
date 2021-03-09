package com.csc.algafood.jpa.paymentMethod;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.csc.algafood.AlgafoodApiApplication;
import com.csc.algafood.domain.model.PaymentMethod;
import com.csc.algafood.domain.repository.PaymentMethodRepository;

public class FindAllPaymentMethodsTest {
	
	public static void main(String[] args) {
		ApplicationContext context = 
				new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PaymentMethodRepository repository = context.getBean(PaymentMethodRepository.class);
		List<PaymentMethod> allPaymentMethod = repository.findAll();
		for (PaymentMethod pm : allPaymentMethod) {
			System.out.printf("%s - %s"+System.lineSeparator()
					, pm.getId()
					, pm.getDescription());
		}
	}

}
