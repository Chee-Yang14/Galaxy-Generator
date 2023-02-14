package ics499.GalaxyGenerator.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="starsystem")
public class StarSystem {
  @Id
  @GeneratedValue
  private Integer id;

  private String name;
  private String type;
  private String goverment;
  private long population;
  private int economyLevel;
  private int spaceResources;
  @Transient
  private List<Planet> planets;
  private int[] location;


  public StarSystem(String name, String type, String goverment, long population, int economyLevel, int spaceResources, List<Planet> planets, int[] location) {
    this.name = name;
    this.type = type;
    this.goverment = goverment;
    this.population = population;
    this.economyLevel = economyLevel;
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

  public long getPopulation() {
    return this.population;
  }

  public void setPopulation(long population) {
    this.population = population;
  }

  public int getEconomyLevel() {
    return this.economyLevel;
  }

  public void setEconomyLevel(int economyLevel) {
    this.economyLevel = economyLevel;
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

  public int[] getLocation() {
    return this.location;
  }

  public void setLocation(int[] location) {
    this.location = location;
  }
}
