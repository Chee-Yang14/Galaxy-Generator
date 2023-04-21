package ics499.GalaxyGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import ics499.GalaxyGenerator.controller.AppController;
import ics499.GalaxyGenerator.controller.PlanetController;
import ics499.GalaxyGenerator.controller.StarSystemController;
import ics499.GalaxyGenerator.controller.UniverseController;
import ics499.GalaxyGenerator.model.Planet;
import ics499.GalaxyGenerator.model.StarSystem;
import ics499.GalaxyGenerator.model.Universe;
import ics499.GalaxyGenerator.model.User;
import ics499.GalaxyGenerator.repository.PlanetRepository;
import ics499.GalaxyGenerator.repository.StarSystemRepository;
import ics499.GalaxyGenerator.repository.UniverseRepository;
import ics499.GalaxyGenerator.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class UniverseControllerTest {

	@Autowired
	private UniverseRepository universeRepo;

	@Autowired
	private UniverseController universeController;

	@Autowired
	private StarSystemController starSystemController;
	@Autowired
	private StarSystemRepository starSystemRepo;

	@Test
	@Order(1)
	void testCreate() {
		long repoCount = universeRepo.count();
		Universe universe = universeController.create();

		assertNotNull(universe);
		assert repoCount < universeRepo.count();
	}

	@Test
	@Order(2)
	void testgetAllUniverses() {
		// made sure it return all universe even as more is added on
		// universe repo start at 1
		List<Universe> listofUni = universeController.getAllUniverse();
		System.out.print(listofUni);
		assert listofUni.size() == universeRepo.findAll().size();

		Universe universe = universeController.create();

		listofUni = universeController.getAllUniverse();
		assert listofUni.size() == universeRepo.findAll().size();

		Universe universe2 = universeController.create();

		listofUni = universeController.getAllUniverse();
		assert listofUni.size() == universeRepo.findAll().size();
		assertNotNull(listofUni);

	}

	@Test
	@Order(3)
	void testgetUniverseById() {
		Universe universe = universeController.create();
		Integer uniId = universe.getUniverseId();
		ResponseEntity<Universe> uni = universeController.getUniverseById(uniId);
		ResponseEntity<Universe> failed = universeController.getUniverseById(550);

		assertThat(uni).isNotNull();
		assertThat(uni.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(uni.getBody()).isNotNull();
		assertThat(uni.equals(universe));

		assertThat(failed).isNotNull();
		assertThat(failed.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(failed.getBody()).isNull();
	}

	@Test
	@Order(4)
	void testUpdate() {
		Universe uni = universeController.create();
		Universe uni2 = universeController.create();

		universeController.update(uni, uni2.getUniverseId());

		assertThat(uni.getShape()).isEqualByComparingTo(uni2.getShape());
		assertThat(uni.getStarSystem().equals(uni2.getStarSystem()));
	}

	@Test
	@Order(13)
	void testDelete() {
		Universe uni = universeController.create();
		long uniBefore = universeRepo.count();

		universeController.delete(uni.getUniverseId());

		long uniAfter = universeRepo.count();

		assert uniBefore > uniAfter;
		assertThat(universeRepo.findById(uni.getUniverseId()).isEmpty());
	}

	@Test
	@Order(9)
	void testCreateStarSystem() throws Exception {
		StarSystem newStarSystem = new StarSystem();
		StarSystem starSystemAdded = starSystemController.create(1, newStarSystem);
		starSystemController.update(starSystemAdded, 1);
		assertNotNull(starSystemRepo.findById(starSystemAdded.getStarsystemId()));
		assertSame(starSystemAdded, newStarSystem);

		StarSystem starSystem2Added = starSystemController.create(1, new StarSystem());
		assertNotNull(starSystemRepo.findById(starSystem2Added.getStarsystemId()));
		assertTrue(starSystemRepo.existsById(starSystem2Added.getStarsystemId()));
	}

	@Test
	@Order(10)
	void testGetStarSystem() {
		ResponseEntity<StarSystem> response = starSystemController.getStarSystemById(1);
		assertNotNull(starSystemController.getStarSystemById(1));
		assertSame(HttpStatus.OK, response.getStatusCode());

		response = starSystemController.getStarSystemById(200);
		assertSame(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	@Order(11)
	void testPutStarSystem() {
		StarSystem newStarSystem = new StarSystem();
		ResponseEntity<StarSystem> responseStarSystem = starSystemController.getStarSystemById(1);
		ResponseEntity<?> response = starSystemController.update(newStarSystem, 1);
		assertSame(HttpStatus.OK, response.getStatusCode());
		assertSame("Update successfully", response.getBody());

		ResponseEntity<StarSystem> responseStarSystemChanged = starSystemController.getStarSystemById(1);
		assertNotSame(responseStarSystem, responseStarSystemChanged);
	}

	@Test
	@Order(12)
	void testDeleteStarSystem() {
		StarSystem planet = new StarSystem();
		starSystemController.delete(1);
		ResponseEntity<StarSystem> responseStarSystem = starSystemController.getStarSystemById(1);
		assertSame(HttpStatus.NOT_FOUND, responseStarSystem.getStatusCode());
		starSystemController.update(planet, 1);
	}

	@Autowired
	private PlanetController planetController;

	@Autowired
	private PlanetRepository planetRepo;

	@Test
	@Order(5)
	void testCreatePlanet() throws Exception {
		Planet newPlanet = new Planet();
		Planet planetAdded = planetController.create(newPlanet, 1);
		planetController.update(planetAdded, 1);
		assertNotNull(planetAdded.getPlanetId());
		assertSame(newPlanet, planetAdded);

		Planet planet2Added = planetController.create(new Planet(), 1);
		assertNotNull(planetRepo.findById(planet2Added.getPlanetId()));
		assertTrue(planetRepo.existsById(planet2Added.getPlanetId()));
	}

	@Test
	@Order(6)
	void testGetPlanet() {
		ResponseEntity<Planet> response = planetController.getPlanetById(1);
		assertNotNull(planetController.getPlanetById(1));
		assertSame(HttpStatus.OK, response.getStatusCode());

		response = planetController.getPlanetById(200);
		assertSame(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	@Order(7)
	void testPutPlanet() {
		Planet newPlanet = new Planet();
		ResponseEntity<Planet> responsePlanet = planetController.getPlanetById(1);
		ResponseEntity<?> response = planetController.update(newPlanet, 1);
		assertSame(HttpStatus.OK, response.getStatusCode());
		assertSame("Update successfully", response.getBody());

		ResponseEntity<Planet> responsePlanetChanged = planetController.getPlanetById(1);
		assertNotSame(responsePlanet, responsePlanetChanged);
	}

	@Test
	@Order(8)
	void testDeletePlanet() {
		Planet planet = new Planet();
		planetController.delete(1);
		ResponseEntity<Planet> responsePlanet = planetController.getPlanetById(1);
		assertSame(HttpStatus.NOT_FOUND, responsePlanet.getStatusCode());
		planetController.update(planet, 1);
	}

	@Autowired
	private AppController userController;

	@Autowired
	private UserRepository userRepository;

	@Test
	@Order(14)
	void testRegisterUser() {
		User user = new User();
		user.setEmail("test");
		user.setPassword("test");
		userController.addUserTest(user);
		Assertions.assertThat(user.getId()).isGreaterThan(0);
	}

	@Test
	@Order(15)
	void getUser() {
		User user = userRepository.findById(1).get();
		Assertions.assertThat(user.getId()).isEqualTo(1);
	}

	@Test
	@Order(16)
	void loginUser() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User testUser = new User();
		testUser.setEmail("test");
		testUser.setPassword("test");
		User user = userRepository.findById(1).get();
		assertTrue(passwordEncoder.matches(testUser.getPassword(), user.getPassword()));
		assertEquals(testUser.getEmail(), user.getEmail());
	}
}
