package ics499.GalaxyGenerator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import ics499.GalaxyGenerator.controller.StarSystemController;
import ics499.GalaxyGenerator.controller.UniverseController;
import ics499.GalaxyGenerator.model.StarSystem;
import ics499.GalaxyGenerator.model.StarSystem;
import ics499.GalaxyGenerator.repository.StarSystemRepository;
import ics499.GalaxyGenerator.repository.UniverseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StarSystemControllerTest {
	@Autowired
	  private StarSystemRepository repo;
	  @Autowired
	  private UniverseRepository universeRepo;
	  @Autowired
	  private UniverseController universeController;
	  @Autowired
	  private StarSystemController controller;
	  
	  @Test
	  void testCreateStarSystem() throws Exception{
		  StarSystem newStarSystem = new StarSystem();
			StarSystem starSystemAdded = controller.create(1, newStarSystem);
			controller.update(starSystemAdded, 1);
			assertNotNull(repo.findById(starSystemAdded.getStarSystemId()));
			assertSame(starSystemAdded, newStarSystem);
			
			StarSystem starSystem2Added = controller.create(1, new StarSystem());
			assertNotNull(repo.findById(starSystem2Added.getStarSystemId()));
			assertTrue(repo.existsById(starSystem2Added.getStarSystemId()));
	  }
	  
	  @Test
		void testGetStarSystem() {
			ResponseEntity<StarSystem> response = controller.getStarSystemById(1);
			assertNotNull(controller.getStarSystemById(1));
			assertSame(HttpStatus.OK, response.getStatusCode());
			
			response = controller.getStarSystemById(200);
			assertSame(HttpStatus.NOT_FOUND, response.getStatusCode());
		}
		
		@Test
		void testPutStarSystem() { 
			StarSystem newStarSystem = new StarSystem();
			ResponseEntity<StarSystem> responseStarSystem = controller.getStarSystemById(1);
			ResponseEntity<?> response = controller.update(newStarSystem, 1);
			assertSame(HttpStatus.OK, response.getStatusCode());
			assertSame("Update successfully", response.getBody());
			
			ResponseEntity<StarSystem> responseStarSystemChanged = controller.getStarSystemById(1);
			assertNotSame(responseStarSystem, responseStarSystemChanged);
		}
		
		@Test
		void testDeleteStarSystem() {
			StarSystem planet = new StarSystem();
			controller.delete(1);
			ResponseEntity<StarSystem> responseStarSystem = controller.getStarSystemById(1);
			assertSame(HttpStatus.NOT_FOUND, responseStarSystem.getStatusCode());
			controller.update(planet, 1);
		}


}
