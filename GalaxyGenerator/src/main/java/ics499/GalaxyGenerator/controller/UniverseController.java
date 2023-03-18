package ics499.GalaxyGenerator.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ics499.GalaxyGenerator.model.GalaxyShape;
import ics499.GalaxyGenerator.model.Universe;
import ics499.GalaxyGenerator.repository.UniverseRepository;

/**
 * this UniverseController class is used to handle HTTP request.
 * like planets and starsystem controller this class uses HTTP request like
 * like GET, PUT ,POST and delete to manipulate universe data.
 * 
 * @author Chee Yang
 * @author Lam Truong
 * @author Joseph jarosch
 * @author andy phan
 */
@RestController
public class UniverseController {

	@Autowired
	private UniverseRepository repo;

	/**
	 * This method find all the universe in the repository
	 * Upon GET request
	 * the method search the repository for all the star system
	 * 
	 * @return all the universe in repo
	 */
	@GetMapping("/universes")
	public List<Universe> getAllUniverse() {
		return repo.findAll();
	}

	/**
	 * this method find a specific universe using its id
	 * The HTTP request require an id
	 * when given it search through the universe repositories
	 * matching each universes id with the given id to find a match
	 * once found it return it
	 * 
	 * @param universeId the id of the universe you want to find
	 * @return the universe with the given id
	 */
	@GetMapping("/universe/{id}")
	public ResponseEntity<Universe> getUniverseById(@PathVariable(value = "id") Integer universeId) {
		try {
			Universe newUniverse = repo.findById(universeId).get();
			return new ResponseEntity<Universe>(newUniverse, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Universe>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * this method update the universe
	 * When the HTTP request is send
	 * the method take the argument id and use it to find the universe to be changed
	 * then with the argument universeupdate it change it with the universe that
	 * have the id
	 * updating the repository
	 * 
	 * @param UniverseUpdate universe you want
	 * @param id             is the id of the universe you want to change
	 * @return responseEntity with HHttpStatus.OK on sucess or HttpStatus.Not_Found
	 *         on failure
	 */
	@PutMapping("/universe/{id}")
	public ResponseEntity<?> update(@RequestBody Universe UniverseUpdate, @PathVariable Integer id) {
		try {
			Universe existedUniverse = repo.findById(id).get();
			existedUniverse.setShape(UniverseUpdate.getShape());
			existedUniverse.setStarSystem(UniverseUpdate.getStarSystem()); 
			repo.save(existedUniverse);
			new ResponseEntity<>(HttpStatus.OK);
			return ResponseEntity.ok("Update successfully");
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * this method delete a universe
	 * Upon the HTTP request
	 * the method use the id to find the universe with the given id and then delete
	 * it
	 * 
	 * @param UniverseId the id of the universe you want to delete
	 */
	@DeleteMapping("/universe/{id}")
	public void delete(@PathVariable(value = "id") Integer UniverseId) {
		repo.deleteById(UniverseId);
	}
}
