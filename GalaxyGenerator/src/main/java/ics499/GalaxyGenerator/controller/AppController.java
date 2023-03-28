package ics499.GalaxyGenerator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ics499.GalaxyGenerator.model.Planet;
import ics499.GalaxyGenerator.model.StarSystem;
import ics499.GalaxyGenerator.model.Universe;
import ics499.GalaxyGenerator.model.User;
import ics499.GalaxyGenerator.repository.PlanetRepository;
import ics499.GalaxyGenerator.repository.StarSystemRepository;
import ics499.GalaxyGenerator.repository.UniverseRepository;
import ics499.GalaxyGenerator.repository.UserRepository;

@Controller
public class AppController {
  @Autowired
  UserRepository userRepo;
  @Autowired
  PlanetRepository planetRepo;
  @Autowired
  StarSystemRepository starSystemRepo;
  @Autowired
  UniverseRepository universeRepo;

  @GetMapping("")
  public String homePage() { // this returns home.html
    return "home";
  }

  @GetMapping("/register") // this returns register.html
  public String register(Model model) {
    model.addAttribute("user", new User()); // @ModelAttribute is an annotation that binds a method parameter or method
                                            // return value to a named model attribute, and then exposes it to a web
                                            // view.
    return "register";
  }

  @PostMapping("/adduser")
  public String addUser(User user) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Password encoder
    String encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
    try {
    	userRepo.save(user);
    }
    catch (Exception e) {
    	return "register_fail";
    }
    
    return "register_success"; // returns register_sucess.html
  }

  @GetMapping("/planets")
  public String listPlanets(Model model) {
    List<Planet> listPlanets = planetRepo.findAll();
    model.addAttribute("listPlanets", listPlanets);
    return "planets";
  }

  @GetMapping("/canvas/{id}")
  public String listStarSystems(@PathVariable(value = "id") Integer universeId, Model model) {
    Universe universeWithId = universeRepo.findById(universeId).get();
    List<StarSystem> listStarSystems = universeWithId.getStarSystem();
    model.addAttribute("listStarSystems", listStarSystems);
    return "canvas";
  }

  /**
   * the method accept a http request to return all planets of a star system
   * the http request come with an id of the star system you want the planet of.
   * the method search for the star system in the repository using the id
   * if a match is found it return that star system's planets
   * 
   * @param starSystemId id of the starsystem you want
   * @return the planets of the matching id star system
   */
  @GetMapping("/planetfromstarsystem/{id}")
  public String getPlanetsFromStarSystemById(@PathVariable(value = "id") Integer starSystemId, Model model) {
    List<Object> planets = starSystemRepo.findPlanetsByStarSystem(starSystemId);
    model.addAttribute("planets", planets);
    return "planets_from_starsystem";
  }

  /**
   * this method add a universe to the repository
   * When the HTTP request is send
   * universe is created
   * and then added, saved and flushed
   *
   * @return repo with newly added universe
   */
  @GetMapping("/generator")
  public String generateUniverse(Model model) {
    model.addAttribute("universe", new Universe());
    return "generator";
  }

  @PostMapping("/generateuniverse")
  public String addUniverse(Universe universe) {
    Universe newUniverse = universe.generate(6, universe.getSize(), universe.getShape());
    universeRepo.save(newUniverse);
    return "generate_success";
  }
}
