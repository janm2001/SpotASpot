package com.codeninjas.spotaspot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpotASpotApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpotASpotApplication.class, args);
	}

}
