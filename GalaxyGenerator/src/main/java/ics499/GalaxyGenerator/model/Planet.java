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
  
  public String createDescription() {
    String descList[][] = {
        { "<line1 <line2 for <line3", "<line1 <line2 for <line3 and <line3", "<line4 by <line5",
            "<line1 <line2 for <line3 but <line4 by <line5", "a <line6 <line7" }, // fix a-n
        /* <line1 */{ "very", "mildly", "most", "reasonably", "" },
        /* <line2 */{ "fabled", "notable", "well known", "famous", "noted" },
        /* <line3 */{ "its <line8 <line9", "the <random name <line10 <line11",
            "its inhabitants' <line12 <line13", "<line14", "its <line15 <line16" },
        /* <line4 */{ "is threatened", "is plagued", "is ravaged", "is cursed", "scourged" },
        /* <line5 */{ "<line17 nuclear war", "<line18 <line10 <line11", "a <line18 disease",
            "<line17 natural disasters", "<line37" },
        /* <line6 */{ " unremarkable", " boring", " dull", " tedious", " revolting" },
        /* <line7 */{ "planet", "world", "place", "little planet", "dump" },
        /* <line8 */{ "ancient", "<line19", "great", "vast", "pink" },
        /* <line9 */{ "<line22 <line21 plantations", "mountains", "<line20", "<line23 forests", "oceans" },
        /* <line10 */{ "3-headed", "golden", "giant", "fire", "river" },
        /* <line11 */{ "<line24", "<line32", "<line26", "<line31", "<line30" },
        /* <line12 */{ "ancient", "exceptional", "eccentric", "fascinating", "<line19" },
        /* <line13 */{ "strength", "religion & beliefs", "intelligence", "ammount of <line25",
            "love for <line25" },
        /* <line14 */{ "<line28 <line27", "<random name> <line24 <line34", "its <line15 <line32 <line34",
            "<line35 <line36", "<line28 <line27" },
        /* <line15 */{ "fabulous", "exotic", "breathtaking", "unusual", "exciting" },
        /* <line16 */{ "cuisine", "wildlife", "scenery", "culture", " <line33 " },
        /* <line17 */{ "frequent", "occasional", "unpredictable", "dreadful", "deadly" },
        /* <line18 */{ "killer", "deadly", "evil", "lethal", "vicious" },
        /* <line19 */{ "funny", "weird", "unusual", "strange", "peculiar" },
        /* <line20 */{ "biofluorescent flora", "dust clouds", "ice bergs", "rock formations", "volcanoes" },
        /* <line21 */{ "plant", "tulip", "banana", "corn", "apple" },
        /* <line22 */{ "B2", "<random name> ", "<random name> <line18", "inhabitant", "<random name> B2" },
        /* <line23 */{ "tropical", "dense", "rain", "impenetrable", "exuberant" },
        /* <line24 */{ "dragon", "lion", "bison", "snake", "wolf" },
        /* <line25 */{ "natural resources", "technology and inventions", "poetry", "music", "<line16" },
        /* <line26 */{ "talking tree", "crab", "bat", "dragon", "B2" },
        /* <line27 */{ "juice", "brandy", "water", "brew", "gargle blasters" },
        /* <line28 */{ "B2", "<random name> <line11", "<random name> ", "<random name> <line18", "<line18 B2" },
        /* <line29 */{ "B0", "The planet B0", "The world B0", "This planet", "This world" },
        /* <line30 */{ "wasp", "aligator", "spider", "ant", "B2" },
        /* <line31 */{ "bees", "plants", "scorpions", "anaconda", "slug" },
        /* <line32 */{ "leopard", "cat", "shark", "goat", "eagles" },
        /* <line33 */{ "<line28 <line27", "<random name> <line24 <line34", "its <line15 <line32 <line34",
            "<line35 <line36", "<line28 <line27" },
        /* <line34 */{ "meat", "cutlet", "steak", "burgers", "soup" },
        /* <line35 */{ "ice", "mud", "underwater", "swamp", "<random name> ultimate" },
        /* <line36 */{ "hockey", "soccer", "karate", "baseball", "tennis" },
        /* <line36 */{ "famine", "alien invasion", "cannibal tribes", "black holes", "asteroid" },
        { "<name", "The planet <name", "The world <name", "This planet", "This world" },
    };

    Random rand = new Random();

    String[] descrArray = (descList[38][rand.nextInt(5)] + " " + descList[0][rand.nextInt(5)]).split(" ");
    ArrayList<String> stringList = new ArrayList<String>();

    for (int i = 0; i < descrArray.length; i++) {
      stringList.add(descrArray[i]);
    }

    for (int i = 0; i < stringList.size(); i++) {
      if (stringList.get(i).toString().contains("<line")) {
        int index = Integer.parseInt(stringList.get(i).toString().substring(5));
        String[] nexStrings = descList[index][rand.nextInt(5)].split(" ");
        ArrayList<String> subList = new ArrayList<String>();

        for (int j = 0; j < nexStrings.length; j++) {
          subList.add(nexStrings[j]);
        }

        stringList.addAll(i + 1, subList);
        stringList.remove(i);
        i--;
      }
    }
    return String.join(" ", stringList);
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
