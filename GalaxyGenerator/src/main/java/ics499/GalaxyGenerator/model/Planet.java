package ics499.GalaxyGenerator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Random;

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
	  Random random = new Random();
	  this.size = random.nextInt(250000000 - 100000000) + 100000000;
	  if (this.size < 120000000) {
		  this.population = this.size * (random.nextLong(20 - 10) + 10);
	  }
	  else if (this.size < 140000000) {
		  this.population = this.size * (random.nextLong(25 - 15) + 15);
	  }
	  else if (this.size < 160000000) {
		  this.population = this.size * (random.nextLong(30 - 20) + 20);
	  }
	  else if (this.size < 180000000) {
		  this.population = this.size * (random.nextLong(35 - 25) + 25);
	  }
	  else if (this.size < 200000000) {
		  this.population = this.size * (random.nextLong(40 - 30) + 30);
	  }
	  else if (this.size < 220000000) {
		  this.population = this.size * (random.nextLong(45 - 35) + 35);
	  }
	  else if (this.size < 250000000) {
		  this.population = this.size * (random.nextLong(55 - 45) + 45);
	  }
	  
	  int roll = random.nextInt(100 - 1) + 1;
	  if (roll <= 20) {
		  this.population *= random.nextDouble(.6 - .1) + .1;
	  }
	  else if (roll == 100) {
		  this.population *= random.nextInt(8 - 3) + 3;
	  }
	  
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
