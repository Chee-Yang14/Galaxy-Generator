package ics499.GalaxyGenerator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Planet is a class that simulate a planet.
 * It contains various things a typical planet would have such as a name,
 * population, size, natural resource amount, etc.
 * 
 * @author Chee Yang
 * @author Lam Truong
 * @author Joseph jarosch
 * @author andy phan
 */
@Entity
@Table(name = "planet")
public class Planet {
	private static Random random = new Random();
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "planet", allocationSize = 1)
	private Integer planetId;
	private String name;
	private int size;
	private long population;
	private int naturalResources;
	private int economyLevel;
	private int[] location;
	private String economyType;
	private String description;
	private String type;

	/**
	 * This the constructor of the planet class
	 * where it create the planet that has all these param.
	 * 
	 * @param id               is the planet id
	 * @param name             is name of the planet
	 * @param type             describe what type of planet this is
	 * @param description      gives a little discription of the planet
	 * @param population       is how many people are living on the planet
	 * @param naturalResources is how many natural resource do this planet have
	 * @param economyLevel     is the economy level
	 * @param economyType      describe what type of economy the planet has.
	 * @param location         coordinate of where the planet is.
	 */
	public Planet(Integer id, String name, String type, String description, long population, int naturalResources,
			int economyLevel, String economyType, int[] location) {
		this.planetId = id;
		this.name = name;
		this.population = population;
		this.naturalResources = naturalResources;
		this.economyLevel = economyLevel;
		this.type = type;
		this.setDescription(description);
		this.setEconomyType(economyType);
		// this.location = location;
	}

	/**
	 * like the planet constructor but instead of taking in input and using those to
	 * create a planet
	 * this method create a planet with every input randomized.
	 * in short it create a planet with randomized features
	 */
	public Planet() {
		this.setSize(createSize());
		this.setPopulation(createPopulation());
		// this.setPlanetId(random.nextInt(900000) + 100000);
		this.type = createType();
		this.setNaturalResources(createResource());
		this.setEconomyType(createEconType());
		this.setEconomyLevel(createEconLevel());
		this.setName(generateNames());
		this.setDescription(createDescription());
	}

	/**
	 * generate a single planet with randomized features
	 * 
	 * @param r an random class
	 * @return A single planet with randomized features
	 */
	public static Planet generate(Random r) {
		random = r;
		return new Planet();
	}

	/**
	 * determine the planet type
	 * chances of this planet being each of these planet type are:
	 * Barren 40%
	 * Ymirian 24%
	 * Arean 13%
	 * Cytherian 12%
	 * Europan 9%
	 * Euarean 1.00%
	 * Eucytherian 1.00%
	 * terran 0.10%
	 * 
	 * @return the planet type it lands on
	 */
	private String createType() {
		String type = "";
		int roll = random.nextInt(10000 - 1) + 1;
		if (roll <= 4000) {
			type = "Barren";
		} else if (roll <= 6400) {
			type = "Ymirian";
		} else if (roll <= 7700) {
			type = "Arean";
		} else if (roll <= 8900) {
			type = "Cytherian";
		} else if (roll <= 9800) {
			type = "Europan";
		} else if (roll <= 9895) {
			type = "Euarean";
		} else if (roll <= 9990) {
			type = "Eucytherian";
		} else if (roll <= 10000) {
			type = "terran";
		}
		return type;
	}

	/**
	 * using the planet type this method return the amount of resource the planet
	 * has.
	 * formula is = random number multipy a constant multiplier based on the planet
	 * type.
	 * 
	 * @return the resource the planet have
	 */
	private int createResource() {
		int resource = 0;
		switch (this.type) {
			case "Barren":
				resource = (int) ((random.nextInt(1000 - 100) + 100) * 0.5);
				break;
			case "Ymirian":
				resource = (int) ((random.nextInt(1000 - 100) + 100) * 0.7);
				break;
			case "Arean":
				resource = (int) ((random.nextInt(1000 - 100) + 100) * 0.9);
				break;
			case "Cytherian":
				resource = (random.nextInt(1000 - 100) + 100) * 1;
				break;
			case "Europan":
				resource = (random.nextInt(1000 - 100) + 100) * 1;
				break;
			case "Euarean":
				resource = (int) ((random.nextInt(1000 - 500) + 500) * 1.1);
				break;
			case "Eucytherian":
				resource = (int) ((random.nextInt(1000 - 500) + 500) * 1.1);
				break;
			case "terran":
				resource = (int) ((random.nextInt(2000 - 700) + 700) * 1.5);
				break;
		}
		return resource;
	}

	/**
	 * determine the planet population based on the size
	 * the formula being
	 * Size multipying a random long plus a number based on the size
	 * 
	 * @return return the planet population
	 */
	private long createPopulation() {
		long population = 0;
		if (this.size < 120000000) {
			population = this.size * (random.nextLong(20 - 10) + 10);
		} else if (this.size < 140000000) {
			population = this.size * (random.nextLong(25 - 15) + 15);
		} else if (this.size < 160000000) {
			population = this.size * (random.nextLong(30 - 20) + 20);
		} else if (this.size < 180000000) {
			population = this.size * (random.nextLong(35 - 25) + 25);
		} else if (this.size < 200000000) {
			population = this.size * (random.nextLong(40 - 30) + 30);
		} else if (this.size < 220000000) {
			population = this.size * (random.nextLong(45 - 35) + 35);
		} else if (this.size < 250000000) {
			population = this.size * (random.nextLong(55 - 45) + 45);
		}
		int roll = random.nextInt(100 - 1) + 1;
		if (roll <= 20) {
			population *= random.nextDouble(.6 - .1) + .1;
		} else if (roll == 100) {
			population *= random.nextInt(8 - 3) + 3;
		}
		return population;
	}

	/**
	 * create a random number for size between 100 million and 250 million
	 * 
	 * @return the size
	 */
	private int createSize() {
		return random.nextInt(250000000 - 100000000) + 100000000;
	}

	/**
	 * determine the economy type based on the roll
	 * 
	 * @return the economy type
	 */
	private String createEconType() {
		int roll = random.nextInt(3);
		switch (roll) {
			case 0:
				return "Traditional economic system";
			case 1:
				return "Command economic system";
			case 2:
				return "Market economic system";
			case 3:
				return "Mixed system";
		}
		return "Market Economy";
	}

	/**
	 * method use to calculate the economy level of the planet.
	 * first find the mid point of the the resource amount by the formula = (min +
	 * max) / 2
	 * if the resource falls below the mid point, the level will be in range 1 - 5,
	 * if above mid point level 5 - 10
	 * example: barren have .5 multiplier with random resource 100-1000. min = 100 *
	 * .5 = 50, max = 1000 * .5 = 500. (500 + 50) / 2 = 275
	 * planet resource = 250, 250 < 275 = true, level = 3
	 * 
	 * @return random value between 1-10
	 */
	private int createEconLevel() {
		switch (this.type) {
			case "Barren":
				if (this.naturalResources < ((500 + 50) / 2)) {
					return random.nextInt(5 - 1) + 1;
				} else {
					return random.nextInt(10 - 5) + 5;
				}
			case "Ymirian":
				if (this.naturalResources < ((700 + 70) / 2)) {
					return random.nextInt(5 - 1) + 1;
				} else {
					return random.nextInt(10 - 5) + 5;
				}
			case "Arean":
				if (this.naturalResources < ((900 + 90) / 2)) {
					return random.nextInt(5 - 1) + 1;
				} else {
					return random.nextInt(10 - 5) + 5;
				}
			case "Cytherian":
				if (this.naturalResources < ((1000 + 100) / 2)) {
					return random.nextInt(5 - 1) + 1;
				} else {
					return random.nextInt(10 - 5) + 5;
				}
			case "Europan":
				if (this.naturalResources < ((1000 + 100) / 2)) {
					return random.nextInt(5 - 1) + 1;
				} else {
					return random.nextInt(10 - 5) + 5;
				}
			case "Euarean":
				if (this.naturalResources < ((1100 + 550) / 2)) {
					return random.nextInt(5 - 1) + 1;
				} else {
					return random.nextInt(10 - 5) + 5;
				}
			case "Eucytherian":
				if (this.naturalResources < ((1100 + 550) / 2)) {
					return random.nextInt(5 - 1) + 1;
				} else {
					return random.nextInt(10 - 5) + 5;
				}
			case "terran":
				if (this.naturalResources < ((3000 + 1050) / 2)) {
					return random.nextInt(5 - 1) + 1;
				} else {
					return random.nextInt(10 - 5) + 5;
				}
		}
		return economyLevel;

	}

	/**
	 * this method generate a random name for planets
	 * It has three array that hold a bunchs of words represting a sylable
	 * a random number determine how many sylable the name will have
	 * The sylable are added if there more than one
	 * the end result is the name
	 * 
	 * @return the randomly created name
	 */
	private String generateNames() {
		String name = "";
		String[] firstSylable = { "Ame", "Shi", "Kiin", "Kael", "Sal", "Sale", "Her", "Hur", "Hue", "New",
				"Old", "Bri", "Twi", "Kel", "Lit", "Le", "Lye", "Deep", "Dark", "Alt", "Ber", "Bres", "Sat", "Fal",
				"Ka", "Ca", "Sej", "Con" };
		String[] secondSylable = { "rash", "nar", "'kan", "kan", "sho", "del", "le", "la", "ta", "coru", "mel",
				"'coru", "sin", "shin", "ba", "sed", "dro" };
		String[] thirdSylable = { "shin", "shen", "dale", "dreg", "je", "se", "ae", "te", "to", "toe", "ri",
				"dro", "dra", "jra", "del", "rel", "ni" };

		int num = random.nextInt(3);
		if (num == 0) {
			name = firstSylable[random.nextInt(firstSylable.length)];
		} else if (num == 1) {
			name = firstSylable[random.nextInt(firstSylable.length)] + secondSylable[random.nextInt(secondSylable.length)];
		} else if (num == 2) {
			name = firstSylable[random.nextInt(firstSylable.length)] + secondSylable[random.nextInt(secondSylable.length)]
					+ thirdSylable[random.nextInt(thirdSylable.length)];
		} else {
			System.out.println("Random number somehow out of range");
		}
		return name;
	}

	/**
	 * This method create a random description for each planet
	 * the method contains a 2d array that holds varys descriptions and phrase.
	 * using for loops and if statement, this method creates a random description
	 * for a planet.
	 * 
	 * @return the string that has the description
	 */
	private String createDescription() {
		String descList[][] = {
				{ "<line1 <line2 for <line3", "<line1 <line2 for <line3 and <line3", "<line4 by <line5",
						"<line1 <line2 for <line3 but <line4 by <line5", "a <line6 <line7" }, // fix a-n
				/* <line1 */{ "very", "mildly", "mostly", "reasonably", "" },
				/* <line2 */{ "fabled", "notable", "well known", "famous", "noted" },
				/* <line3 */{ "its <line8 <line9", "the <randomName> <line10 <line11",
						"its inhabitants' <line12 <line13", "<line14", "its <line15 <line16" },
				/* <line4 */{ "threatened", "plagued", "ravaged", "cursed", "scourged" },
				/* <line5 */{ "<line17 nuclear war", "<line18 <line10 <line11", "a <line18 disease",
						"<line17 natural disasters", "<line37" },
				/* <line6 */{ "unremarkable", "boring", "dull", "tedious", "revolting" },
				/* <line7 */{ "planet", "world", "place", "little planet", "dump" },
				/* <line8 */{ "ancient", "<line19", "great", "vast", "pink" },
				/* <line9 */{ "<line22 <line21 plantations", "mountains", "<line20", "<line23 forests", "oceans" },
				/* <line10 */{ "3-headed", "golden", "giant", "fire", "river" },
				/* <line11 */{ "<line24", "<line32", "<line26", "<line31", "<line30" },
				/* <line12 */{ "ancient", "exceptional", "eccentric", "fascinating", "<line19" },
				/* <line13 */{ "strength", "religion & beliefs", "intelligence", "amount of <line25",
						"love for <line25" },
				/* <line14 */{ "<line28 <line27", "<randomName> <line24 <line34", "its <line15 <line32 <line34",
						"<line35 <line36", "<line28 <line27" },
				/* <line15 */{ "fabulous", "exotic", "breathtaking", "unusual", "exciting" },
				/* <line16 */{ "cuisine", "wildlife", "scenery", "culture", " <line33 " },
				/* <line17 */{ "frequent", "occasional", "unpredictable", "dreadful", "deadly" },
				/* <line18 */{ "killer", "deadly", "evil", "lethal", "vicious" },
				/* <line19 */{ "funny", "weird", "unusual", "strange", "peculiar" },
				/* <line20 */{ "biofluorescent flora", "dust clouds", "ice bergs", "rock formations", "volcanoes" },
				/* <line21 */{ "plant", "tulip", "banana", "corn", "apple" },
				/* <line22 */{ "<randomName>", "<randomName> ", "<randomName> <line18", "inhabitant",
						"<randomName> <randomName>" },
				/* <line23 */{ "tropical", "dense", "rain", "impenetrable", "exuberant" },
				/* <line24 */{ "dragon", "lion", "bison", "snake", "wolf" },
				/* <line25 */{ "natural resources", "technology and inventions", "poetry", "music", "<line16" },
				/* <line26 */{ "talking tree", "crab", "bat", "dragon", "<randomName>" },
				/* <line27 */{ "juice", "brandy", "water", "brew", "gargle blasters" },
				/* <line28 */{ "<randomName>", "<randomName> <line11", "<randomName> ", "<randomName> <line18",
						"<line18 <randomName>" },
				/* <line29 */{ "B0", "The planet B0", "The world B0", "This planet", "This world" },
				/* <line30 */{ "wasp", "aligator", "spider", "ant", "<randomName>" },
				/* <line31 */{ "bees", "<randomName>", "scorpions", "anaconda", "slug" },
				/* <line32 */{ "leopard", "cat", "shark", "goat", "eagles" },
				/* <line33 */{ "<line28 <line27", "<randomName> <line24 <line34", "its <line15 <line32 <line34",
						"<line35 <line36", "<line28 <line27" },
				/* <line34 */{ "meat", "cutlet", "steak", "burgers", "soup" },
				/* <line35 */{ "ice", "mud", "underwater", "swamp", "<randomName> ultimate" },
				/* <line36 */{ "hockey", "soccer", "karate", "baseball", "tennis" },
				/* <line36 */{ "famine", "alien invasion", "cannibal tribes", "black holes", "asteroid" },
				{ "<name is", "The planet <name is", "The world <name is", "This planet is", "This world is" },
		};

		String[] descrArray = (descList[38][random.nextInt(5)] + " " + descList[0][random.nextInt(5)]).split(" ");
		ArrayList<String> stringList = new ArrayList<String>();

		for (int i = 0; i < descrArray.length; i++) {
			stringList.add(descrArray[i]);
		}

		for (int i = 0; i < stringList.size(); i++) {
			if (stringList.get(i).toString().contains("<name")) {
				stringList.set(i, this.name);
			}
			if (stringList.get(i).toString().contains("<line")) {
				int index = Integer.parseInt(stringList.get(i).toString().substring(5));
				String[] nexStrings = descList[index][random.nextInt(5)].split(" ");
				ArrayList<String> subList = new ArrayList<String>();

				for (int j = 0; j < nexStrings.length; j++) {
					subList.add(nexStrings[j]);
				}

				stringList.addAll(i + 1, subList);
				stringList.remove(i);
				i--;
			}
			if (stringList.get(i).toString().contains("<randomName>")) {
				stringList.set(i, this.generateNames());
			}
		}
		return String.join(" ", stringList);
	}

	public void onClick() {
		/**
		 * TODO
		 */
	}

	public Integer getPlanetId() {
		return this.planetId;
	}

	public void setPlanetId(Integer id) {
		this.planetId = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// @Column(name = "planet_type", nullable = false)
	// public String getType() {
	// return this.type;
	// }

	// public void setType(String type) {
	// this.type = type;
	// }

	// @Column(name = "planet_desc", nullable = false)
	// public String getDescription() {
	// return this.description;
	// }

	// public void setDescription(String description) {
	// this.description = description;
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
	// return this.economyType;
	// }

	// public void setEconomyType(String economyType) {
	// this.economyType = economyType;
	// }

	public int[] getLocation() {
		return this.location;
	}

	public void setLocation(int[] location) {
		this.location = location;
	}

	public String getEconomyType() {
		return economyType;
	}

	public void setEconomyType(String economyType) {
		this.economyType = economyType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Planet [planetId=" + planetId + ", name=" + name + ", size=" + size + ", population=" + population
				+ ", naturalResources=" + naturalResources + ", economyLevel=" + economyLevel + ", location="
				+ Arrays.toString(location) + ", economyType=" + economyType + ", description=" + description
				+ ", type=" + type + "]";
	}
}
