package ics499.GalaxyGenerator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "starsystem")
public class StarSystem {
  @Id
  @GeneratedValue
  @SequenceGenerator(name = "starsystem", allocationSize = 1)
  private Integer starsystemId;
  @Transient
  private Universe u;
  private Random rand;
  private String name;
  private StarType type;
  private String goverment;
  private long population;
  private int economyLevel;
  private int spaceResources;
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<Planet> planets = new ArrayList<Planet>();
  private int[] location;// X, Y

  public StarSystem(String name, StarType type, String goverment, long population, int economyLevel, int spaceResources,
      List<Planet> planets, int[] location) {
    this.name = name;
    this.type = type;
    this.goverment = goverment;
    this.population = population;
    this.economyLevel = economyLevel;
    this.spaceResources = spaceResources;
    this.planets = planets;
    this.location = location;
  }

  public StarSystem(Universe u) {
    GalaxyShape shape = u.getShape();
    Random r = u.getRandom();
    if (shape.equals(GalaxyShape.SCATTER)) {
      location = new int[] { r.nextInt(100), r.nextInt(100) };
    } else if (shape.equals(GalaxyShape.CLUSTER)) {
      location = new int[] { r.nextInt(50) + r.nextInt(50), r.nextInt(50) + r.nextInt(50) };

    }

    int starTableRoll = r.nextInt(1000); // The real probabilities of the star types can be found here
                                         // https://en.wikipedia.org/wiki/Stellar_classification
    if (starTableRoll < 450) { // I have fudged the numbers a little bit for a bir more even a spread.
      this.type = StarType.M;
    } else if (starTableRoll < 750) {
      this.type = StarType.K;
    } else if (starTableRoll < 900) {
      this.type = StarType.G;
    } else if (starTableRoll < 950) {
      this.type = StarType.F;
    } else if (starTableRoll < 990) {
      this.type = StarType.A;
    } else if (starTableRoll < 996) {
      this.type = StarType.B;
    } else {
      this.type = StarType.O;
    }

    this.population = 0;
    this.economyLevel = 0;
    int sizeA = r.nextInt(10);
    int sizeB = r.nextInt(10);
    int iPlanets = Math.min(sizeA, sizeB); // roll two 10-sided dice, take the lower. There could be up to 12 planets,
                                           // with the average being aprox 4.5
    for (int i = 0; i < iPlanets; i++) {
      Planet newPlanet = Planet.generate(r);
      this.population += newPlanet.getPopulation();
      this.economyLevel += newPlanet.getEconomyLevel();
      planets.add(newPlanet);
    }
    // In the future, we may want the system totals to be higher than the sum of
    // it's planets. We will need to work out the formula.

  }

  public StarSystem() {
  }

  public static StarSystem generate(Universe u) {
    return new StarSystem(u);
    /**
     * TODO
     */
  }

  public void onClick() {
    /**
     * TODO
     */
  }

  public Integer getStarsystemId() {
    return this.starsystemId;
  }

  public void setStarsystemId(Integer id) {
    this.starsystemId = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public StarType getType() {
    return this.type;
  }

  public void setType(StarType type) {
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
