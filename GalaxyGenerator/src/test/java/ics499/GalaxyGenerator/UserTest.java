package ics499.GalaxyGenerator;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import ics499.GalaxyGenerator.controller.AppController;
import ics499.GalaxyGenerator.model.User;
import ics499.GalaxyGenerator.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserTest {
  @Autowired
  private AppController userController;

  @Autowired
  private UserRepository userRepository;

  @Test
  void testRegisterUser() {
    User user = new User();
    user.setEmail("test");
    user.setPassword("test");
    userController.addUser(user);
    Assertions.assertThat(user.getId()).isGreaterThan(0);
  }

  @Test
  void getUser() {
    User user = userRepository.findById(1).get();
    Assertions.assertThat(user.getId()).isEqualTo(1);
  }

  @Test
  void loginUser() {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    User testUser = new User();
    testUser.setEmail("test");
    testUser.setPassword("test");
    User user = userRepository.findById(1).get();
    assertTrue(passwordEncoder.matches(testUser.getPassword(), user.getPassword()));
    assertEquals(testUser.getEmail(), user.getEmail());
  }
}
