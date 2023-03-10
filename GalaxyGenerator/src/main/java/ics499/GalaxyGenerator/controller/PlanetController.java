package ics499.GalaxyGenerator.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import ics499.GalaxyGenerator.model.Planet;
import ics499.GalaxyGenerator.model.StarSystem;
import ics499.GalaxyGenerator.repository.PlanetRepository;
import ics499.GalaxyGenerator.repository.StarSystemRepository;
/**
 * This planetController class is used to handle HTTP rest. 
 * This mean it uses http request like GET, PUT ,POST and delete to manipulate data. 
 * the data being planets from models package. 
 * 
 * @author Chee Yang
 * @author Lam Truong
 * @author Joseph jarosch
 * @author andy phan
 */
@RestController
public class PlanetController {

  @Autowired
  private PlanetRepository repo;
  @Autowired
  private StarSystemRepository starSystemRepo;

  // @GetMapping("/planets")
  // public List<Planet> getALlPlanets() {
  // return repo.findAll();
  // }
  /**
   * this method uses @pathVariable to find an planet with an id that match the given id and then return it.
   * @param planetId the given id
   * @return the planet with the given id and httpStatus that say OK meaning sucessful. 
   * or in the case there is no id match it return just httpStatus that say not found. 
   */
  @GetMapping("/planet/{id}")
  public ResponseEntity<Planet> getPlanetById(@PathVariable(value = "id") Integer planetId) {
    try {
      Planet planet = repo.findById(planetId).get();
      return new ResponseEntity<Planet>(planet, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<Planet>(HttpStatus.NOT_FOUND);
    }
  }
  
  /**
   * This method add a planet to the repository. 
   * It does this by taking a given star system id
   * then searching the star system repository for a match. 
   * then it add a planet to that star system when a match is found. 
   * 
   * @param planetToAdd is the planet to be added
   * @param starSystemId to determine which starsystem the adding planet is on
   * @return the repo after saving and flushing. 
   */
  @PostMapping("/addplanet/{starSystemId}")
  public Planet create(@RequestBody Planet planetToAdd, @PathVariable Integer starSystemId) {
    StarSystem parentStarSystem = starSystemRepo.findById(starSystemId).get();
    parentStarSystem.addPlanet(planetToAdd);
    return repo.saveAndFlush(planetToAdd);
  }

  /**
   * this method update a planet with a given id
   * a planet and id is given, the method take the id and look for a match in it repostories. 
   * then it replace the planet with matching id in it repositories with all the features in the given planet. 
   * updating the planet in the repositories. 
   * 
   * @param planetToUpdate the planet you want to update
   * @param id the planet id
   * @return return a HTTP status that say OK or NOT_FOUND depending on sucess and failure.
   */
  @PutMapping("/planet/{id}")
  public ResponseEntity<?> update(@RequestBody Planet planetToUpdate, @PathVariable Integer id) {
    try {
      Planet existedPlanet = repo.findById(id).get();

      existedPlanet.setSize(planetToUpdate.getSize());
      existedPlanet.setEconomyLevel(planetToUpdate.getEconomyLevel());
      existedPlanet.setLocation(planetToUpdate.getLocation());
      existedPlanet.setNaturalResources(planetToUpdate.getNaturalResources());
      existedPlanet.setPopulation(planetToUpdate.getPopulation());
      existedPlanet.setName(planetToUpdate.getName());

      repo.save(existedPlanet);
      new ResponseEntity<>(HttpStatus.OK);
      return ResponseEntity.ok("Update successfully");
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * this method delete a planet from the repository
   * it take an id and use it to find and delete a planet in the repositroy
   * 
   * @param planetId id of the planet you want to remove 
   */
  @DeleteMapping("/planet/{id}")
  public void delete(@PathVariable(value = "id") Integer planetId) {
    repo.deleteById(planetId);
  }

}
