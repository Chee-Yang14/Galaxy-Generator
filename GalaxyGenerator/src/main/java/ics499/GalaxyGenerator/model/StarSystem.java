package ics499.GalaxyGenerator.model;

import java.util.ArrayList;
import java.util.Arrays;
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
	private Random random = new Random();
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
		this.setStarsystemId(random.nextInt(900000) + 100000);
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
		this.setPlanets(createPlanets());
		this.setStarsystemId(random.nextInt(900000) + 100000);
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
	
	public int[] createLocation() {
		int[] location = {};
		try {
			GalaxyShape shape = u.getShape();
			if (shape.equals(GalaxyShape.SCATTER)) {
				location = new int[] { random.nextInt(100), random.nextInt(100) };
			} 
			else if (shape.equals(GalaxyShape.CLUSTER)) {
				location = new int[] { random.nextInt(50) + random.nextInt(50), random.nextInt(50) + random.nextInt(50) };
			}
		}
		catch (Exception e) {
		}
		return location;
	}

	public StarType createStarType() {
		int starTableRoll = random.nextInt(1000); // The real probabilities of the star types can be found here
		StarType type = null;
		// https://en.wikipedia.org/wiki/Stellar_classification
		if (starTableRoll < 450) { // I have fudged the numbers a little bit for a bir more even a spread.
			type = StarType.M;
		} 
		else if (starTableRoll < 750) {
			type = StarType.K;
		} 
		else if (starTableRoll < 900) {
			type = StarType.G;
		} 
		else if (starTableRoll < 950) {
			type = StarType.F;
		} else if (starTableRoll < 990) {
			type = StarType.A;
		} else if (starTableRoll < 996) {
			type = StarType.B;
		} 
		else {
			type = StarType.O;
		}
		return type;
	}

	public long createPopulation() {
		long population = 0;
		for (int i = 0; i < planets.size(); i++) {
			population += planets.get(i).getPopulation();
		}
		return population;
	}

	public int createEconLevel() {
		int level = 0;
		for (int i = 0; i < planets.size(); i++) {
			level += planets.get(i).getEconomyLevel();
		}
		return level;
	}

	public int createSpaceResource() {
		int resource = 0;
		for (int i = 0; i < planets.size(); i++) {
			resource += planets.get(i).getNaturalResources();
		}
		return resource;
	}

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


}
