package com.eventoesportivo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "com.eventoesportivo.model")
@ComponentScan(basePackages = { "com.*" })
@EnableJpaRepositories(basePackages = { "com.eventoesportivo.repository" })
@EnableTransactionManagement
public class EventoesportivoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventoesportivoApplication.class, args);
		/*BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		  String result = encoder.encode("123");
		  System.out.println(result);
		 */
	}
}
