package org.acme.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({
	"classpath:my.properties"
})
public class EshopApplication {

	public static void main(final String[] args) {
		SpringApplication.run(EshopApplication.class, args);
	}
}
