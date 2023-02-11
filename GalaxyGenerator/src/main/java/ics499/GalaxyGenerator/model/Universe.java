package ics499.GalaxyGenerator.model;

import java.util.List;

public class Universe {
  private String shape;
  private List<StarSystem> starSystem;

  public Universe(String shape, List<StarSystem> starSystem){
    this.shape = shape;
    this.starSystem = starSystem;
  }

  public Universe(){}

  public void generate(){
    /**
     * TODO 
     */
  }

  public String getShape() {
    return this.shape;
  }

  public void setShape(String shape) {
    this.shape = shape;
  }

  public List<StarSystem> getStarSystem() {
    return this.starSystem;
  }

  public void setStarSystem(List<StarSystem> starSystem) {
    this.starSystem = starSystem;
  }
}