package ics499.GalaxyGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import ics499.GalaxyGenerator.controller.UniverseController;
import ics499.GalaxyGenerator.model.StarSystem;
import ics499.GalaxyGenerator.model.Universe;
import ics499.GalaxyGenerator.repository.UniverseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UniverseControllerTest {

	@Autowired
	private UniverseRepository repo;
	
	@Autowired
	private UniverseController controller;

	@Test
	void testCreate() {
		long repoCount = repo.count();
		Universe universe = controller.create();
		
		assertNotNull(universe);
		assert repoCount < repo.count();
	}
	
	@Test
	void testgetAllStarSystems() {
		//made sure it return all universe even as more is added on
		// universe repo start at 1
		List<Universe> listofUni=controller.getAllUniverse();
		System.out.print(listofUni);
		assert listofUni.size() == repo.findAll().size();
		
		Universe universe = controller.create();
		
		listofUni=controller.getAllUniverse();
		
		assert listofUni.size() == repo.findAll().size();
		
		Universe universe2 = controller.create();
		
		listofUni=controller.getAllUniverse();
		
		assert listofUni.size() == repo.findAll().size();
		
		
		assertNotNull(listofUni);
		
	}
	
	@Test
	void testgetUniverseById() {
		
		
		Integer uniId = repo.findAll().get(0).getUniverseId();
		Universe universe = repo.findAll().get(0);
		ResponseEntity<Universe> uni = controller.getUniverseById(uniId);
		ResponseEntity<Universe> failed = controller.getUniverseById(550);
		
		assertThat(uni).isNotNull();
		assertThat(uni.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(uni.getBody()).isNotNull();
		assertThat(uni.equals(universe));
		
		assertThat(failed).isNotNull();
		assertThat(failed.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(failed.getBody()).isNull();

	}
	
	@Test
	void testUpdate() {
		
	}
	
	@Test
	void testDelete() {
		
		
		Universe uni = controller.create();
		long uniBefore = repo.count();
		
		controller.delete(uni.getUniverseId());
		
		long uniAfter= repo.count();
		
		assert uniBefore>uniAfter;
		assertThat(repo.findById(uni.getUniverseId()).isEmpty());
	}
	
}
