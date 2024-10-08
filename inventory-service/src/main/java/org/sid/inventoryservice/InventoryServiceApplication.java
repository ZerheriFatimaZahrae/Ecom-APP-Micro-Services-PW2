package org.sid.inventoryservice;

import org.sid.inventoryservice.entities.Product;
import org.sid.inventoryservice.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class InventoryServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(ProductRepository productRepository,
							RepositoryRestConfiguration repositoryRestConfiguration){
		return args -> {
			Random random=new Random();
			for (int i = 1; i <10 ; i++) {
				repositoryRestConfiguration.exposeIdsFor(Product.class);
				productRepository.saveAll(List.of(
						Product.builder()
								.name("Compuer "+i)
								.price(1200+Math.random()*10000)
								.quantity(1+random.nextInt(200)).build()
				));
			}

		};
	}
}
