package ics499.GalaxyGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ics499.GalaxyGenerator.model.User;
import ics499.GalaxyGenerator.repository.UserRepository;

@Controller
public class AppController {
  @Autowired
  UserRepository repo;

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
    repo.save(user);
    return "register_success"; // returns register_sucess.html
  }
}
