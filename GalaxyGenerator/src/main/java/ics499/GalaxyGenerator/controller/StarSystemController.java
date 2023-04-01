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
import ics499.GalaxyGenerator.model.Planet;
import ics499.GalaxyGenerator.model.StarSystem;
import ics499.GalaxyGenerator.model.Universe;
import ics499.GalaxyGenerator.repository.PlanetRepository;
import ics499.GalaxyGenerator.repository.StarSystemRepository;
import ics499.GalaxyGenerator.repository.UniverseRepository;

/**
 * This class StarSystemController handle all the http request for star system
 * Like planetController this class uses http request like GET, PUT ,POST and
 * delete to manipulate
 * star system data.
 * 
 * @author Chee Yang
 * @author Lam Truong
 * @author Joseph jarosch
 * @author andy phan
 */
@RestController
public class StarSystemController {

  @Autowired
  private StarSystemRepository repo;
  @Autowired
  private UniverseRepository universeRepo;
  @Autowired
  private UniverseController universeController;
  
  
  /**
   * this method accept an http request for all star system
   * the method then return all the star system in it repository back
   * 
   * @return all star system in it repository
   */
  @GetMapping("/starsystems")
  public List<StarSystem> getAllStarSystems() {
    return repo.findAll();
  }

  /**
   * this method accepts a http request for a star system with the given id
   * the method take the id and search the repository for a star system with an id
   * that match the given id.
   * the method then return the star system if found along with a OK message
   * else if no match it found it return the message Not found
   * 
   * @param starSystemId is the id of the star system you want to find
   * @return if found a the star system is return along with OK message else on
   *         failure a not found is return instead.
   */
  @GetMapping("/starsystem/{id}")
  public ResponseEntity<StarSystem> getStarSystemById(@PathVariable(value = "id") Integer starSystemId) {
    try {
      StarSystem newStarSystem = repo.findById(starSystemId).get();
      return new ResponseEntity<StarSystem>(newStarSystem, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<StarSystem>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * this method accepts a http request to add a star system to the repo
   * the http request has a universe id so that the method knows where to add the
   * star system to
   * the method find the universe with the given universe id
   * then it add the star system to the universe
   * 
   * @param universeId      is the id of the universe you want your new star
   *                        system to add to
   * @param starSystemToAdd is the star system to be added
   * @return the repository with it saved and flushed
   */
  @PostMapping("/addstarsystem/{universeId}")
  public StarSystem create(@PathVariable Integer universeId, @RequestBody StarSystem starSystemToAdd) {
    Universe parentUniverse = universeRepo.findById(universeId).get();
    parentUniverse.addStarSystem(starSystemToAdd);
    return repo.saveAndFlush(starSystemToAdd);
  }

  /**
   * this method update a star system with a given id
   * a starsystem and id is given, the method take the id and look for a match in
   * it repostories.
   * then it replace the starsystem with matching id in it repositories with all
   * the features in the given starsytem.
   * 
   * @param starSystemToUpdate the star system you want to update the repository
   *                           with
   * @param id                 is the id of the star system to be changed
   * @return OK if everything is sucess or NOT FOUND if faillure occur.
   */
  @PutMapping("/starsystem/{id}")
  public ResponseEntity<?> update(@RequestBody StarSystem starSystemToUpdate, @PathVariable Integer id) {
    try {
      StarSystem existedStarSystem = repo.findById(id).get();
      existedStarSystem.setEconomyLevel(starSystemToUpdate.getEconomyLevel());
      existedStarSystem.setGoverment(starSystemToUpdate.getGoverment());
      if (starSystemToUpdate.getLocation().length != 0) {
    	  existedStarSystem.setLocation(starSystemToUpdate.getLocation());
      }
      existedStarSystem.setName(starSystemToUpdate.getName());

      repo.save(existedStarSystem);
      new ResponseEntity<>(HttpStatus.OK);
      return ResponseEntity.ok("Update successfully");
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * this method accept a http request to delete a star system
   * the http request come with an id of the star system it want removed
   * the method takes the id and find a star system with matching id in it
   * repository
   * if it found it then delete the starsystem.
   * 
   * @param starSystemId
   */
  @DeleteMapping("/starsystem/{id}")
  public void delete(@PathVariable(value = "id") Integer starSystemId) {
	List<Universe> universes = universeRepo.findAll();
	for (int i = 0; i < universes.size(); i++) {
		boolean indicator = false;
		List<StarSystem> starSystems = universes.get(i).getStarSystem();
		for (int x = 0; x < starSystems.size(); x++) {
			if (starSystems.get(x).getStarsystemId() == starSystemId) {
				starSystems.remove(x);
				indicator = true;
				break;
			}
		}
		if (indicator == true) {
			universes.get(i).setStarSystem(starSystems);
			universeController.update(universes.get(i), universes.get(i).getUniverseId());
			break;
		}
	}
    repo.deleteById(starSystemId);
  }

//   @GetMapping("/planetfromstarsystem/{id}")
//   public List<Object> getPlanetsFromStarSystemById(@PathVariable(value = "id")
//   Integer starSystemId) {
//   List<Object> planets = repo.findPlanetsByStarSystem(starSystemId);
//   return planets;
//   }

}
