package ics499.GalaxyGenerator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * This class simulate the universe. It holds everything in it whether it the star system or planets. 
 * It also have name and shape among others. 
 * 
 * @author Chee Yang
 * @author Lam Truong
 * @author Joseph jarosch
 * @author andy phan
 */
@Entity
@Table(name = "Universe")
public class Universe {
  @Id
  @GeneratedValue
  @SequenceGenerator(name = "Universe", allocationSize = 1)
  private Integer universeId;
  @Transient
  private int seed;
  private GalaxyShape shape;
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  private List<StarSystem> starSystem = new ArrayList<StarSystem>();
  private Stack<String> names;
  @Transient
  private Random rand = new Random();

  /**
   * this is the default constructor
   * it generate a universe with the given parameters.
   * 
   * @param shape determine what shape the galaxy will be.
   * @param random a randomizer that planets and starsystem
   * @param size determine how big the universe is
   * @param seed a seed, so the universe can return the same universe with the seed. 
   */
  public Universe(GalaxyShape shape, Random random, int size, int seed) {
    this.shape = shape;
    this.seed = seed;
    this.rand = random;
    this.names = new Stack<String>();
    generateNames(1000);
    this.universeId = rand.nextInt(900000) + 100000;

    for (int i = 0; i < size; i++) {
      starSystem.add(StarSystem.generate(this));
    }
  }

  public Universe() {
  }

  /**
   * this method is used to generate a name for the universe. 
   * if the names stack has less than two name it generate a bunch of new name
   * otherwise the method pop a name and return it.
   *
   * @return return the name generated
   */
  public String newName() {
    if (names.size() <= 2) {
      generateNames(1000);
    }
    return names.pop();
  }

  /**
   * this method generate a universe and return it
   * 
   * @param randSeed the seed number
   * @param gSize the size of the universe
   * @param gShape the shape of the galaxy
   * @return the universe
   */
  public static Universe generate(int randSeed, int gSize, GalaxyShape gShape) {
    // seed will be given value by input, eventually
    Random nRandom = new Random(randSeed);
    Universe u = new Universe(gShape, nRandom, gSize, randSeed);
    return u;

    /**
     * TODO
     */
  }

  /**
	 * this method generate a random name for the star system
	 * It has three array that hold a bunchs of words represting a sylable
	 * 
	 * the paramenter number determine how many names there will be
	 * there a random generated number ever for loops
	 * the random generated number determine how many sylable there will be
	 * The sylable are added if there more than one
	 * the end result is the stack names having tons of randomly generated names
	 * 
	 */
  private void generateNames(int number) {
    Random temp = new Random();
    this.rand = temp;
    String[] firstSylable = { "Ame", "Shi", "Kiin", "Kael", "Sal", "Sale", "Her", "Hur", "Hue", "New",
        "Old", "Bri", "Twi", "Kel", "Lit", "Le", "Lye", "Deep", "Dark", "Alt", "Ber", "Bres", "Sat", "Fal",
        "Ka", "Ca", "Sej", "Con" };
    String[] secondSylable = { "rash", "nar", "'kan", "kan", "sho", "del", "le", "la", "ta", "coru", "mel",
        "'coru", "sin", "shin", "ba", "sed", "dro" };
    String[] thirdSylable = { "shin", "shen", "dale", "dreg", "je", "se", "ae", "te", "to", "toe", "ri",
        "dro", "dra", "jra", "del", "rel", "ni" };

    for (int i = 0; i < number; i++) {
      int num = rand.nextInt(3);
      if (num == 0) {
        String name = firstSylable[rand.nextInt(firstSylable.length)];
        names.push(name);
      } else if (num == 1) {
        String name = firstSylable[rand.nextInt(firstSylable.length)]
            + secondSylable[rand.nextInt(secondSylable.length)];
        names.push(name);
      } else if (num == 2) {
        String name = firstSylable[rand.nextInt(firstSylable.length)]
            + secondSylable[rand.nextInt(secondSylable.length)] + thirdSylable[rand.nextInt(thirdSylable.length)];
        names.push(name);
      } else {
        System.out.println("Ranom number somehow out of range");
      }
    }
  }

  public Integer getUniverseId() {
    return this.universeId;
  }

  public void setUniverseId(Integer id) {
    this.universeId = id;
  }

  public GalaxyShape getShape() {
    return this.shape;
  }

  public void setShape(GalaxyShape shape) {
    this.shape = shape;
  }

  public List<StarSystem> getStarSystem() {
    return this.starSystem;
  }

  public void setStarSystem(List<StarSystem> starSystem) {
    this.starSystem = starSystem;
  }

  @Override
  public String toString() {
    return "Universe [universeId=" + universeId + ", seed=" + seed + ", shape=" + shape + ", starSystem=" + starSystem
        + ", names=" + names + "]";
  }

}