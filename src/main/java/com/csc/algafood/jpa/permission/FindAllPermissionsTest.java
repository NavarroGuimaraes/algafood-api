package com.csc.algafood.jpa.permission;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.csc.algafood.AlgafoodApiApplication;
import com.csc.algafood.domain.model.Permission;
import com.csc.algafood.domain.repository.PermissionRepository;

public class FindAllPermissionsTest {
	
	public static void main(String[] args) {
		ApplicationContext context = 
				new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PermissionRepository repository = context.getBean(PermissionRepository.class);
		List<Permission> allPermissions = repository.findAll();
		for (Permission p : allPermissions) {
			System.out.printf("%s - %s - %s"+System.lineSeparator()
					, p.getId()
					, p.getName()
					, p.getDescription());
		}
	}

}
