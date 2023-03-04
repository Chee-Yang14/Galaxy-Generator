package ics499.GalaxyGenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ics499.GalaxyGenerator.model.GalaxyShape;
import ics499.GalaxyGenerator.model.Universe;

@SpringBootApplication
public class GalaxyGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalaxyGeneratorApplication.class, args);

		GalaxyShape s = GalaxyShape.SCATTER;
		Universe universe = Universe.generate(0, 10, s.SCATTER);

		for (int i = 0; i < 10; i++) {
			// System.out.println(universe.getStarSystem().get(i));
		}

		// Planet planet = new Planet();
		// for (int i = 0; i < 25; i++)
		// System.out.println(planet.createDescription());
	}
}
