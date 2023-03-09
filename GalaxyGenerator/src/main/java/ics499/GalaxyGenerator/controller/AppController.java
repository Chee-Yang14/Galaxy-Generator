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
import ics499.GalaxyGenerator.model.User;
import ics499.GalaxyGenerator.repository.PlanetRepository;
import ics499.GalaxyGenerator.repository.StarSystemRepository;
import ics499.GalaxyGenerator.repository.UserRepository;

@Controller
public class AppController {
  @Autowired
  UserRepository userRepo;
  @Autowired
  PlanetRepository planetRepo;
  @Autowired
  StarSystemRepository starSystemRepo;

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
    userRepo.save(user);
    return "register_success"; // returns register_sucess.html
  }

  @GetMapping("/planets")
  public String listPlanets(Model model) {
    List<Planet> listPlanets = planetRepo.findAll();
    model.addAttribute("listPlanets", listPlanets);
    return "planets";
  }

  @GetMapping("/canvas")
  public String listStarSystems(Model model) {
    List<StarSystem> listStarSystems = starSystemRepo.findAll();
    model.addAttribute("listStarSystems", listStarSystems);
    return "canvas";
  }

  // @GetMapping("/planetfromstarsystem/{id}")
  // public String getPlanetsFromStarSystemById(@PathVariable(value = "id")
  // Integer starSystemId, Model model) {
  // List<Object> planets = starSystemRepo.findPlanetsByStarSystem(starSystemId);
  // model.addAttribute("planets", planets);
  // return "planets from starsystem";
  // }
}
