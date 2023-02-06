package ics499.GalaxyGenerator.model;

import java.awt.Point;
import java.util.List;

public class StarSystem {
  private String name;
  private String type;
  private String goverment;
  private int population;
  private int econemyLevel;
  private int spaceResources;
  private List<Planet> planets;
  private Point location;


  public StarSystem(String name, String type, String goverment, int population, int econemyLevel, int spaceResources, List<Planet> planets, Point location) {
    this.name = name;
    this.type = type;
    this.goverment = goverment;
    this.population = population;
    this.econemyLevel = econemyLevel;
    this.spaceResources = spaceResources;
    this.planets = planets;
    this.location = location;
  }

  public StarSystem(){}

  public void generate(){
    /**
     * TODO 
     */
  }

  public void onClick(){
    /**
     * TODO 
     */
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getGoverment() {
    return this.goverment;
  }

  public void setGoverment(String goverment) {
    this.goverment = goverment;
  }

  public int getPopulation() {
    return this.population;
  }

  public void setPopulation(int population) {
    this.population = population;
  }

  public int getEconemyLevel() {
    return this.econemyLevel;
  }

  public void setEconemyLevel(int econemyLevel) {
    this.econemyLevel = econemyLevel;
  }

  public int getSpaceResources() {
    return this.spaceResources;
  }

  public void setSpaceResources(int spaceResources) {
    this.spaceResources = spaceResources;
  }

  public List<Planet> getPlanets() {
    return this.planets;
  }

  public void setPlanets(List<Planet> planets) {
    this.planets = planets;
  }

  public Point getLocation() {
    return this.location;
  }

  public void setLocation(Point location) {
    this.location = location;
  }
}
