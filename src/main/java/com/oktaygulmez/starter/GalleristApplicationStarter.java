package com.oktaygulmez.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages = {"com.oktaygulmez"})
@EntityScan(basePackages = {"com.oktaygulmez"})
@EnableJpaRepositories(basePackages = {"com.oktaygulmez"})
@EnableScheduling
@SpringBootApplication
public class oktayProjectApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(oktayProjectApplicationStarter.class, args);
	}

}
