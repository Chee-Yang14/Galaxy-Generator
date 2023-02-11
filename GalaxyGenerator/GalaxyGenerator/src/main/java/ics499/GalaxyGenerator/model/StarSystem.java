package ics499.GalaxyGenerator.model;

import java.awt.Point;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="starsystem")
public class StarSystem {
  private Integer id;
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

  @Id
  @GeneratedValue
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "name", nullable = false)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "star_type", nullable = false)
  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Column(name = "government_type", nullable = false)
  public String getGoverment() {
    return this.goverment;
  }

  public void setGoverment(String goverment) {
    this.goverment = goverment;
  }

  @Column(name = "population", nullable = false)
  public int getPopulation() {
    return this.population;
  }

  public void setPopulation(int population) {
    this.population = population;
  }

  @Column(name = "economy_level", nullable = false)
  public int getEconemyLevel() {
    return this.econemyLevel;
  }

  public void setEconemyLevel(int econemyLevel) {
    this.econemyLevel = econemyLevel;
  }

  @Column(name = "space_resource", nullable = false)
  public int getSpaceResources() {
    return this.spaceResources;
  }

  public void setSpaceResources(int spaceResources) {
    this.spaceResources = spaceResources;
  }

  @Transient
  public List<Planet> getPlanets() {
    return this.planets;
  }

  public void setPlanets(List<Planet> planets) {
    this.planets = planets;
  }

  @Transient
  public Point getLocation() {
    return this.location;
  }

  public void setLocation(Point location) {
    this.location = location;
  }
}
