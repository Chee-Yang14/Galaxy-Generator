package ics499.GalaxyGenerator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
 * StarSystem is a class that simulate a Star system.
 * It has a star and then planets that orbit around it.
 * The planets are represent in an array.
 * The star system would typically be occupy and thus have government and
 * population values.
 * 
 * @author Chee Yang
 * @author Lam Truong
 * @author Joseph jarosch
 * @author andy phan
 */
@Entity
@Table(name = "starsystem")
public class StarSystem {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "starsystem", allocationSize = 1)
	private Integer starsystemId;

	@Transient
	private Universe u;
	
	@Transient
	private boolean capital;
	
	@Transient
	private StarSystem vassalTo;
	private Random random = new Random();
	private String name;
	private String type;
	private String goverment;
	private long population;
	private int economyLevel;
	private int spaceResources;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Planet> planets = new ArrayList<Planet>();
	private int[] location;// X, Y

	/**
	 * This is the default constructor.
	 * 
	 * @param name            used to determine the name
	 * @param type            is used to determine the star type 
	 * @param goverment       determine what the government will be 
	 * @param population      determine what population is - population is how many people live on the starsystem
	 * @param economyLevel    determine the economylevel - which is how good the economy is
	 * @param spaceResources  determine spaceResource - how many resource are there are from all their planet combine
	 * @param planets         determine the planets that orbit this starsystem.
	 * @param location		  determine this star system location in the universe. 
	 */
	public StarSystem(Integer id, String name, String type, String goverment, long population, int economyLevel,
			int spaceResources,
			List<Planet> planets, int[] location) {
		this.starsystemId = id;
		this.name = name;
		this.type = type;
		this.goverment = goverment;
		this.population = population;
		this.economyLevel = economyLevel;
		this.spaceResources = spaceResources;
		this.planets = planets;
		this.location = location;
	}

	/**
	 * a another constructor that generate a star system with randomized features
	 * the only parameter it take in is a universe where the star system will be in
	 * @param u the universe where this star system will be put in
	 */
	public StarSystem(Universe u) {
		this.setPlanets(createPlanets());
		this.setUniverse(u);
		this.setName(generateNames());
		this.setType(createStarType());
		this.setLocation(createLocation());
		this.setPopulation(createPopulation());
		this.setEconomyLevel(createEconLevel());
		this.setSpaceResources(createSpaceResource());
		this.setGoverment(createGovernment());
		// In the future, we may want the system totals to be higher than the sum of
		// it's planets. We will need to work out the formula.

	}
	/**
	 * another construtor that generate a starsytem with randomize features but this time it has no universe.
	 */
	public StarSystem() {
		this.setPlanets(createPlanets());
		this.setName(generateNames());
		this.setType(createStarType());
		this.setLocation(createLocation());
		this.setPopulation(createPopulation());
		this.setEconomyLevel(createEconLevel());
		this.setSpaceResources(createSpaceResource());
		this.setGoverment(createGovernment());
	}
	
	/**
	 * this method generate a single starsytem with randomized feature and then return it
	 * 
	 * @param u take in the universe to give to the starsystem so it is generated with the universe as it universe. 
	 * @return a single starsystem with randomized features
	 */
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

	/**
	 * this method create planets for the starsytem. 
	 * it take two random number between 1 and 10. 
	 * and used it to determine how many planets the starsytem will have.
	 * then it generated the planet, which will all have randomized features. 
	 * @return the list of planets
	 */
	public ArrayList<Planet> createPlanets() {
		ArrayList<Planet> planetList = new ArrayList<>();
		int sizeA = random.nextInt(10 - 1) + 1;
		int sizeB = random.nextInt(10 - 1) + 1;
		int iPlanets = Math.min(sizeA, sizeB);

		for (int i = 0; i < iPlanets; i++) {
			Planet newPlanet = Planet.generate(random);
			planetList.add(newPlanet);
		}
		return planetList;
	}

	/**
	 * this method randomly determine what the starsystem government is gonna be
	 * there a randomized number that range from 1 to 10. 
	 * depending on the random number the government is determine
	 * through the switch, landing where the number is. 
	 * @return the government
	 */
	public String createGovernment() {
		int num = random.nextInt(10);
		String government = "";
		switch (num) {
			case 0:
				government = "Democracy";
				break;
			case 1:
				government = "Communism";
				break;
			case 2:
				government = "Socialism";
				break;
			case 3:
				government = "Oligarchy";
				break;
			case 4:
				government = "Aristocracy";
				break;
			case 5:
				government = "Monarchy";
				break;
			case 6:
				government = "Theocracy";
				break;
			case 7:
				government = "Colonialism";
				break;
			case 8:
				government = "Totalitarianism";
				break;
			case 9:
				government = "Military Dictatorship";
				break;
		}
		return government;
	}

	/**
	 * this method determine the starsytem location
	 * there is a star shape which is either scatter or cluster. 
	 * using that we use it to determine the starsystem location so that along with other starsytem 
	 * it make a cluster or scatter on the map. 
	 * @return the location 
	 */
	public int[] createLocation() {
		int[] location = {};
		try {
			GalaxyShape shape = u.getShape();
			if (shape.equals(GalaxyShape.SCATTER)) {
				location = new int[] { random.nextInt(100), random.nextInt(100) };
			} else if (shape.equals(GalaxyShape.CLUSTER)) {
				location = new int[] { random.nextInt(50) + random.nextInt(50), random.nextInt(50) + random.nextInt(50) };
			}
		} catch (Exception e) {
		}
		return location;
	}

	/**
	 * This method determine what the starsystem star stype will be. 
	 * there a randomized number that is generated 
	 * and depending on that randomized number a star type is determine
	 * the star type are taken from stellar_classification wikipedia
	 * this the star type along with the percent chance of the star sytem being that star type
	 * O 55%
	 * B 25%
	 * A 10%
	 * F  5%
	 * G  1&
	 * K .4%
	 * M  what ever is left
	 * @return the star type
	 */
	public String createStarType() {
		int starTableRoll = random.nextInt(1000); // The real probabilities of the star types can be found here
		String type = "";
		// https://en.wikipedia.org/wiki/Stellar_classification
		if (starTableRoll < 450) { // I have fudged the numbers a little bit for a bir more even a spread.
			// type = StarType.M;
			type = "M";
		} else if (starTableRoll < 750) {
			// type = StarType.K;
			type = "K";
		} else if (starTableRoll < 900) {
			// type = StarType.G;
			type = "G";
		} else if (starTableRoll < 950) {
			// type = StarType.F;
			type = "F";
		} else if (starTableRoll < 990) {
			// type = StarType.A;
			type = "A";
		} else if (starTableRoll < 996) {
			// type = StarType.B;
			type = "B";
		} else {
			// type = StarType.O;
			type = "O";
		}
		return type;
	}

	/**
	 * the method is used to determine the population of the star syste. 
	 * the method goes through all the planets and by adding the population up
	 * it is able to find the total population
	 * @return the population
	 */
	public long createPopulation() {
		long population = 0;
		for (int i = 0; i < planets.size(); i++) {
			population += planets.get(i).getPopulation();
		}
		return population;
	}

	/**
	 * this method determine what the econLevel on the starsytem will be.
	 * 
	 * It is able to determine the econlevel by adding all the econlevel from each planet up. 
	 * @return the econlevel
	 */
	public int createEconLevel() {
		int level = 0;
		for (int i = 0; i < planets.size(); i++) {
			level += planets.get(i).getEconomyLevel();
		}
		return level;
	}

	/**
	 * this method determine how much natural resource the star system have
	 * 
	 * this is determine by adding all the planets natural resource up.
	 * @return the natural resource
	 */
	public int createSpaceResource() {
		int resource = 0;
		for (int i = 0; i < planets.size(); i++) {
			resource += planets.get(i).getNaturalResources();
		}
		return resource;
	}
	/**
	 * this method generate a random name for the star system
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
	
	public double getDistance(StarSystem s) {
		int[] c = s.getLocation();
		double dist = Math.sqrt((c[0] - location[0])*(c[0] - location[0]) + (c[1] - location[1])*(c[1] - location[1]));
		return dist;
		
	}
	
	public double getInfluence(StarSystem s) {
		double influence = s.getEconomyLevel() - getDistance(s) * getDistance(s);
		return influence;
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

	public String getType() {
		return this.type;
	}

	public void setType(String string) {
		this.type = string;
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

	public void addPlanet(Planet planet) {
		this.planets.add(planet);
	}

	public int[] getLocation() {
		return this.location;
	}

	public void setLocation(int[] location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "StarSystem [starsystemId=" + starsystemId + ", name=" + name + ", type=" + type
				+ ", goverment=" + goverment + ", population=" + population + ", economyLevel=" + economyLevel
				+ ", spaceResources=" + spaceResources + ", planets=" + planets + ", location="
				+ Arrays.toString(location) + "]";
	}

	public Universe getUniverse() {
		return this.u;
	}

	public void setUniverse(Universe u) {
		this.u = u;
	}

	public boolean isCapital() {
		return capital;
	}

	public void setCapital(boolean capital) {
		this.capital = capital;
	}

	public StarSystem getVassalTo() {
		return vassalTo;
	}

	public void setVassalTo(StarSystem vassalTo) {
		this.vassalTo = vassalTo;
	}

}
