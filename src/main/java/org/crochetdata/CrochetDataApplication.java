package org.crochetdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("org.crochetdata.model")
public class CrochetDataApplication {
	public static void main(String[] args) {
		SpringApplication.run(CrochetDataApplication.class, args);
	}
}


