package ics499.GalaxyGenerator.model;

import java.awt.Point;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="planet")
public class Planet {
  private Integer id;
  private String name;
  // private String type;
  private int size;
  // private String description;
  private int population;
  private int naturalResources;
  private int economyLevel;
  // private String economyType;
  private int[] location;

  public Planet(Integer id, String name, String type, String description, int population, int naturalResources, int economyLevel, String economyType, Point location) {
    this.id = id;
    this.name = name;
    // this.type = type;
    // this.description = description;
    this.population = population;
    this.naturalResources = naturalResources;
    this.economyLevel = economyLevel;
    // this.economyType = economyType;
    // this.location = location;
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

	// @Column(name = "planet_type", nullable = false)
  // public String getType() {
  //   return this.type;
  // }

  // public void setType(String type) {
  //   this.type = type;
  // }

	// @Column(name = "planet_desc", nullable = false)
  // public String getDescription() {
  //   return this.description;
  // }

  // public void setDescription(String description) {
  //   this.description = description;
  // }


	@Column(name = "size", nullable = false)
  public int getSize() {
    return this.size;
  }

  public void setSize(int size) {
    this.size = size;
  }

	@Column(name = "population", nullable = false)
  public int getPopulation() {
    return this.population;
  }

  public void setPopulation(int population) {
    this.population = population;
  }

	@Column(name = "natural_resource", nullable = false)
  public int getNaturalResources() {
    return this.naturalResources;
  }

  public void setNaturalResources(int naturalResources) {
    this.naturalResources = naturalResources;
  }

	@Column(name = "economy", nullable = false)
  public int getEconomyLevel() {
    return this.economyLevel;
  }

  public void setEconomyLevel(int economyLevel) {
    this.economyLevel = economyLevel;
  }

	// @Column(name = "government_type", nullable = false)
  // public String getEconomyType() {
  //   return this.economyType;
  // }

  // public void setEconomyType(String economyType) {
  //   this.economyType = economyType;
  // }

  @Column(name = "location")
  public int[] getLocation() {
    return this.location;
  }

  public void setLocation(int[]location) {
    this.location = location;
  }
}
