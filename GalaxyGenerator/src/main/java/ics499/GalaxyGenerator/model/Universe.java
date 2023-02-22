package ics499.GalaxyGenerator.model;

import java.util.List;
import java.util.Random;
import java.util.Stack;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

public class Universe {

  private Integer id;
  private int seed;
  private String shape;
  private List<StarSystem> starSystem;
  private Stack<String> names;
  private Random rand;

  public Universe(String shape, List<StarSystem> starSystem){
    this.shape = shape;
    this.starSystem = starSystem;
  }

  public Universe(){}
  
  public String newName() {
	  if(names.size()<=2) {
		  generateNames(1000);
	  }
	  return names.pop();
  }

  public void generate(){
	  seed = 1; //seed will be given value by input, eventually
	  rand = new Random(seed);
	  names = new Stack<String>();
	  generateNames(1000);
	  
	  
    /**
     * TODO 
     */
  }

  public String getShape() {
    return this.shape;
  }

  public void setShape(String shape) {
    this.shape = shape;
  }

  public List<StarSystem> getStarSystem() {
    return this.starSystem;
  }

  public void setStarSystem(List<StarSystem> starSystem) {
    this.starSystem = starSystem;
  }
  
  private void generateNames(int number) {
	String[] firstSylable = {"Ame","Shi", "Kiin", "Kael", "Sal","Sale", "Her", "Hur", "Hue", "New",
			"Old", "Bri", "Twi", "Kel", "Lit", "Le","Lye", "Deep", "Dark", "Alt", "Ber", "Bres", "Sat", "Fal",
			"Ka", "Ca", "Sej", "Con"};
	String[] secondSylable = {"rash", "nar", "'kan", "kan", "sho", "del", "le","la","ta","coru","mel",
			"'coru", "sin", "shin", "ba","sed","dro"};
	String[] thirdSylable = {"shin", "shen", "dale", "dreg", "je", "se", "ae", "te","to","toe","ri",
			"dro", "dra","jra","del","rel","ni"};

	for(int i = 0; i<number; i++) {
		int num = rand.nextInt(3);
		if(num == 0) {
			String name = firstSylable[rand.nextInt(firstSylable.length)];
			names.push(name);
		}else if(num == 1) {
			String name = firstSylable[rand.nextInt(firstSylable.length)] + secondSylable[rand.nextInt(secondSylable.length)];
			names.push(name);
		}else if(num == 2) {
			String name = firstSylable[rand.nextInt(firstSylable.length)] + secondSylable[rand.nextInt(secondSylable.length)] + thirdSylable[rand.nextInt(thirdSylable.length)];
			names.push(name);
		}else {
			System.out.println("Ranom number somehow out of range");
		}
		
	}
  }
}