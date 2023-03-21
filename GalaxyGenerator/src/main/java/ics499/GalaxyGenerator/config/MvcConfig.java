package ics499.GalaxyGenerator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

  public void addViewControllers(ViewControllerRegistry registry) { // create connection between html files and web
                                                                    // browser
    registry.addViewController("/home").setViewName("home");
    registry.addViewController("/").setViewName("home");
    registry.addViewController("/register").setViewName("register");
    registry.addViewController("/register_success").setViewName("register_success");
    registry.addViewController("/add_user").setViewName("add_user");
    registry.addViewController("/index").setViewName("index");
    registry.addViewController("/planets").setViewName("planets");
    registry.addViewController("/canvas").setViewName("canvas");
    registry.addViewController("/universes").setViewName("universes");
    registry.addViewController("/generator").setViewName("generator");
  }

}