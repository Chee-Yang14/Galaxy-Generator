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
import ics499.GalaxyGenerator.model.StarSystem;
import ics499.GalaxyGenerator.model.Universe;
import ics499.GalaxyGenerator.repository.StarSystemRepository;

@RestController
public class StarSystemController {

  @Autowired
  private StarSystemRepository repo;

  @GetMapping("/starsystems")
  public List<StarSystem> getAllStarSystems() {
    return repo.findAll();
  }

  @GetMapping("/starsystem/{id}")
  public ResponseEntity<StarSystem> getStarSystemById(@PathVariable(value = "id") Integer starSystemId) {
    try {
      StarSystem newStarSystem = repo.findById(starSystemId).get();
      return new ResponseEntity<StarSystem>(newStarSystem, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<StarSystem>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/addstarsystem")
  public StarSystem create(@RequestBody StarSystem starSystemToAdd) {
    return repo.saveAndFlush(starSystemToAdd);
  }

  @PutMapping("/starsystem/{id}")
  public ResponseEntity<?> update(@RequestBody StarSystem starSystemToUpdate, @PathVariable Integer id) {
    try {
      StarSystem existedStarSystem = repo.findById(id).get();
      existedStarSystem.setEconomyLevel(starSystemToUpdate.getEconomyLevel());
      existedStarSystem.setGoverment(starSystemToUpdate.getGoverment());
      existedStarSystem.setLocation(starSystemToUpdate.getLocation());
      existedStarSystem.setName(starSystemToUpdate.getName());
      existedStarSystem.setPopulation(starSystemToUpdate.getPopulation());
      existedStarSystem.setSpaceResources(starSystemToUpdate.getSpaceResources());
      existedStarSystem.setType(starSystemToUpdate.getType());

      repo.save(existedStarSystem);
      new ResponseEntity<>(HttpStatus.OK);
      return ResponseEntity.ok("Update successfully");
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/starsystem/{id}")
  public void delete(@PathVariable(value = "id") Integer starSystemId) {
    repo.deleteById(starSystemId);
  }
}
