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

  @GetMapping("/planet/{id}")
  public ResponseEntity<Planet> getPlanetById(@PathVariable(value = "id") Integer planetId) {
    try {
      Planet planet = repo.findById(planetId).get();
      return new ResponseEntity<Planet>(planet, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<Planet>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/addplanet/{starSystemId}")
  public Planet create(@RequestBody Planet planetToAdd, @PathVariable Integer starSystemId) {
    StarSystem parentStarSystem = starSystemRepo.findById(starSystemId).get();
    parentStarSystem.addPlanet(planetToAdd);
    return repo.saveAndFlush(planetToAdd);
  }

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

  @DeleteMapping("/planet/{id}")
  public void delete(@PathVariable(value = "id") Integer planetId) {
    repo.deleteById(planetId);
  }

}
