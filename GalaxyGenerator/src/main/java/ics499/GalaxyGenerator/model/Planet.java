package ics499.GalaxyGenerator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="planet")
public class Planet {
  
  @Id
  @GeneratedValue
  @SequenceGenerator(name = "planet", allocationSize = 1)
  private Integer id;

  private String name;
  private int size;
  private long population;
  private int naturalResources;
  private int economyLevel;
  private int[] location;
  // private String economyType;
  // private String description;
  // private String type;

  public Planet(Integer id, String name, String type, String description, long population, int naturalResources, int economyLevel, String economyType, int[] location) {
    this.id = id;
    this.name = name;
    this.population = population;
    this.naturalResources = naturalResources;
    this.economyLevel = economyLevel;
    // this.type = type;
    // this.description = description;
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

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

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


  public int getSize() {
    return this.size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public long getPopulation() {
    return this.population;
  }

  public void setPopulation(long population) {
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

	// @Column(name = "government_type", nullable = false)
  // public String getEconomyType() {
  //   return this.economyType;
  // }

  // public void setEconomyType(String economyType) {
  //   this.economyType = economyType;
  // }

  public int[] getLocation() {
    return this.location;
  }

  public void setLocation(int[]location) {
    this.location = location;
  }
}
