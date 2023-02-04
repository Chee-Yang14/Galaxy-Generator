package ics499.GalaxyGenerator;

import java.awt.Point;

public class Planet {
  private String name;
  private String type;
  private String description;
  private int population;
  private int naturalResources;
  private int economyLevel;
  private String economyType;
  private Point location;

  public Planet(String name, String type, String description, int population, int naturalResources, int economyLevel, String economyType, Point location) {
    this.name = name;
    this.type = type;
    this.description = description;
    this.population = population;
    this.naturalResources = naturalResources;
    this.economyLevel = economyLevel;
    this.economyType = economyType;
    this.location = location;
  }

  public Planet() {
  }

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

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getPopulation() {
    return this.population;
  }

  public void setPopulation(int population) {
    this.population = population;
  }

  public int getNaturalResources() {
    return this.naturalResources;
  }

  public void setNaturalResources(int naturalResources) {
    this.naturalResources = naturalResources;
  }

  public int getEconomyLevel() {
    return this.economyLevel;
  }

  public void setEconomyLevel(int economyLevel) {
    this.economyLevel = economyLevel;
  }

  public String getEconomyType() {
    return this.economyType;
  }

  public void setEconomyType(String economyType) {
    this.economyType = economyType;
  }

  public Point getLocation() {
    return this.location;
  }

  public void setLocation(Point location) {
    this.location = location;
  }
  
}
