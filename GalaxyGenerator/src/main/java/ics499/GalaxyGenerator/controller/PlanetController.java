package ics499.GalaxyGenerator.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ics499.GalaxyGenerator.model.Planet;
import ics499.GalaxyGenerator.repository.PlanetRepository;

@RestController
public class PlanetController {

  @Autowired
  private PlanetRepository repo;

  @GetMapping("/planets")
  public List<Planet> getALlPlanets() {
    return repo.findAll();
  }

  @GetMapping("/planet/{id}") 
  public ResponseEntity<Planet> getPlanetById(@PathVariable(value = "id") Integer planetId) {
    try {
      Planet planet = repo.findById(planetId).get();
      return new ResponseEntity<Planet>(planet, HttpStatus.OK);
    }
    catch (NoSuchElementException e) {
      return new ResponseEntity<Planet>(HttpStatus.NOT_FOUND);
    }
  }
}
