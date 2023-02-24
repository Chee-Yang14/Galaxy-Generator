package ics499.GalaxyGenerator.controller;

import java.util.List;
import java.util.NoSuchElementException;

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

import ics499.GalaxyGenerator.model.Universe;
import ics499.GalaxyGenerator.repository.UniverseRepository;

@RestController
public class UniverseController {
	
	@Autowired
	private UniverseRepository repo;
	
	@GetMapping("/universes")
	public List<Universe> getAllStarSystems() {
	    return repo.findAll();
	}
	
	@GetMapping("/universe/{id}")
	public ResponseEntity<Universe> getStarSystemById(@PathVariable(value = "id") Integer universeId) {
	    try {
	      Universe newUniverse = repo.findById(universeId).get();
	      return new ResponseEntity<Universe>(newUniverse, HttpStatus.OK);
	    }
	    catch (NoSuchElementException e)  {
	      return new ResponseEntity<Universe>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@PostMapping("/adduniverse")
	public Universe create(@RequestBody final Universe universeToAdd) {
	    return repo.saveAndFlush(universeToAdd);
	}
	
	@PutMapping("/universe/{id}")
	public ResponseEntity<?> update(@RequestBody Universe UniverseUpdate, @PathVariable Integer id){
	    try {
	    	Universe existedUniverse = repo.findById(id).get();
	    	


		    repo.save(existedUniverse);
		    new ResponseEntity<>(HttpStatus.OK);
		    return ResponseEntity.ok("Update successfully");
	    }
	    catch (NoSuchElementException e) {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("/universe/{id}") 
	public void delete(@PathVariable(value = "id") Integer UniverseId) {
	    repo.deleteById(UniverseId);
	}
}
