package ics499.GalaxyGenerator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * This class simulate the universe. It holds everything in it whether it the
 * star system or planets.
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
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "universe", allocationSize = 1)
  private Integer universeId;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<StarSystem> starSystem = new ArrayList<StarSystem>();
  //@Transient
 // private List<StarSystem> capitals = new ArrayList<StarSystem>();
  @Transient
  private int size;
  private GalaxyShape shape;
  private Stack<String> names;
  @Transient
  private Random rand = new Random();
  @Transient
  private int seed;
  private String universeName;
  /**
   * this is the default constructor
   * it generate a universe with the given parameters.
   * 
   * @param shape  determine what shape the galaxy will be.
   * @param random a randomizer that planets and starsystem
   * @param size   determine how big the universe is
   * @param seed   a seed, so the universe can return the same universe with the
   *               seed.
   */
  public Universe(GalaxyShape shape, Random random, int size, int seed) {
    this.shape = shape;
    this.seed = seed;
    this.rand = random;
    this.size = size;
    this.names = new Stack<String>();
    generateNames(1000);
    this.universeName = names.pop();
    this.universeId = rand.nextInt(9000) + 1000;
    List<StarSystem> capitals = new ArrayList<StarSystem>();
    for (int i = 0; i < this.size; i++) {
    	StarSystem newSystem = (StarSystem.generate(this));
    	if(i < Math.sqrt(this.size)) {
    		System.out.println(newSystem.isCapital());
    		newSystem.setCapital(true);
    		System.out.println(newSystem.isCapital());
    		capitals.add(newSystem);
    	}
      starSystem.add(newSystem);
    }
    for(int i = 0; i<starSystem.size(); i++) {
    	if(starSystem.get(i).isCapital()) {
    		starSystem.get(i).setLiegeSystemName(capitals.get(i).getName());
    	}else {
    		StarSystem frontrunner = capitals.get(0);
    		for(int j = 1; j < capitals.size(); j++) {
    			if(starSystem.get(i).getInfluence(capitals.get(j)) > starSystem.get(i).getInfluence(frontrunner)) {
    				frontrunner = capitals.get(j);
    			}
    		}
    		starSystem.get(i).setLiegeSystemName(frontrunner.getName());
    	}
    }
    
    
    
  }

  public Universe() {
    this.seed = 6;
    for (int i = 0; i < this.size; i++) {
      starSystem.add(StarSystem.generate(this));
    }
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
   * @param gSize    the size of the universe
   * @param gShape   the shape of the galaxy
   * @return the universe
   */
  public static Universe generate(int randSeed, int gSize, GalaxyShape gShape) {
    // seed will be given value by input, eventually
    Random nRandom = new Random(randSeed);
    Universe u = new Universe(gShape, nRandom, gSize, randSeed);
    return u;
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
    return "shape= " + shape + ", size= " + size + ", universeName= " + universeName;
  }

  public void addStarSystem(StarSystem starSystemToAdd) {
    this.starSystem.add(starSystemToAdd);
  }

  public int getSize() {
    return this.size;
  }

  public void setSize(int size) {
    this.size = size;
  }

public String getUniverseName() {
	return universeName;
}

public void setUniverseName(String universeName) {
	this.universeName = universeName;
}
}