package com.pucrs.modulovendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.pucrs"})
public class ModuloVendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModuloVendasApplication.class, args);
	}

}
