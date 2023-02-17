package ics499.GalaxyGenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GalaxyGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalaxyGeneratorApplication.class, args);
		// Planet planet = new Planet();
		// for (int i = 0; i < 25; i++)
		// System.out.println(planet.createDescription());
	}
}
