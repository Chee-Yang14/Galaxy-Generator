package ics499.GalaxyGenerator;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpStatus;
import ics499.GalaxyGenerator.controller.PlanetController;
import ics499.GalaxyGenerator.model.Planet;
import ics499.GalaxyGenerator.repository.PlanetRepository;
import ics499.GalaxyGenerator.repository.StarSystemRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PlanetTest {
	
	@Autowired
	private PlanetController controller;
	
	@Autowired
	private StarSystemRepository starSystemRepo;
	
	@Autowired
	private PlanetRepository repo;
	
	@Test
	void testCreatePlanet() throws Exception{
		Planet newPlanet = new Planet();
		Planet planetAdded = controller.create(newPlanet, 1);
		assertNotNull(planetAdded.getPlanetId());
		assertSame(newPlanet, planetAdded);
		
		Planet planet2Added = controller.create(new Planet(), 1);
		assertNotNull(repo.findById(planet2Added.getPlanetId()));
		assertTrue(repo.existsById(planet2Added.getPlanetId()));
	}
	
	@Test
	void testGetPlanet() {
		ResponseEntity<Planet> response = controller.getPlanetById(1);
		assertNotNull(controller.getPlanetById(1));
		assertSame(HttpStatus.OK, response.getStatusCode());
		
		response = controller.getPlanetById(200);
		assertSame(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	void testPutPlanet() {
		Planet newPlanet = new Planet();
		ResponseEntity<Planet> responsePlanet = controller.getPlanetById(1);
		ResponseEntity<?> response = controller.update(newPlanet, 1);
		assertSame(HttpStatus.OK, response.getStatusCode());
		assertSame("Update successfully", response.getBody());
		
		ResponseEntity<Planet> responsePlanetChanged = controller.getPlanetById(1);
		assertNotSame(responsePlanet, responsePlanetChanged);
	}

}
