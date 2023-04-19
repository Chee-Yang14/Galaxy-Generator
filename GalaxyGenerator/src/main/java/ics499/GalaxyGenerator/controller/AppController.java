package ics499.GalaxyGenerator.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.engine.AttributeName;

import ics499.GalaxyGenerator.model.GalaxyShape;
import ics499.GalaxyGenerator.model.Planet;
import ics499.GalaxyGenerator.model.StarSystem;
import ics499.GalaxyGenerator.model.Universe;
import ics499.GalaxyGenerator.model.User;
import ics499.GalaxyGenerator.repository.PlanetRepository;
import ics499.GalaxyGenerator.repository.StarSystemRepository;
import ics499.GalaxyGenerator.repository.UniverseRepository;
import ics499.GalaxyGenerator.repository.UserRepository;

@Controller
public class AppController {
	@Autowired
	UserRepository userRepo;
	@Autowired
	PlanetRepository planetRepo;
	@Autowired
	PlanetController planetController;
	@Autowired
	StarSystemRepository starSystemRepo;
	@Autowired
	StarSystemController starSystemController;
	@Autowired
	UniverseController universeController;
	@Autowired
	UniverseRepository universeRepo;

	private boolean input = false;

	@GetMapping("/")
	public String homePage(Model model) { // this returns home.html
		List<Universe> ListUniverses = universeRepo.findAll();
		model.addAttribute("ListUniverses", ListUniverses);
		return "home";
	}

	@GetMapping("/home")
	public String home(Model model) { // this returns home.html
		List<Universe> ListUniverses = universeRepo.findAll();
		model.addAttribute("ListUniverses", ListUniverses);
		return "home";
	}
	
	@GetMapping("/login_page")
	public String loginPage() {
		return "custom_login";
	}
	
	@PostMapping("/error_login")
	public String errorLogin() {
		return "custom_log_error";
	}
	
	@GetMapping("/register") // this returns register.html
	public String register(Model model) {
		model.addAttribute("user", new User()); // @ModelAttribute is an annotation that binds a method parameter or
												// method
												// return value to a named model attribute, and then exposes it to a web
												// view.
		return "register";
	}

	@PostMapping("/adduser")
	public String addUser(User user, Model model) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Password encoder
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		model.addAttribute("user", new User());
		user.setPassword(encodedPassword);
		try {
			userRepo.save(user);
		} catch (Exception e) {
			return "register_fail";
		}
		return "register_success"; // returns register_sucess.html
	}

	@GetMapping("/planets")
	public String listPlanets(Model model) {
		List<Planet> listPlanets = planetRepo.findAll();
		model.addAttribute("listPlanets", listPlanets);
		List<Universe> ListUniverses = universeRepo.findAll();
		model.addAttribute("ListUniverses", ListUniverses);
		return "planets";
	}

	@GetMapping("/canvas/{id}")
	public String listStarSystems(@PathVariable(value = "id") Integer universeId, Model model) {
		Universe universeWithId = universeRepo.findById(universeId).get();
		List<StarSystem> listStarSystems = universeWithId.getStarSystem();
		List<Universe> universes = universeRepo.findAll();
		model.addAttribute("listUniverse", universes);
		model.addAttribute("listStarSystems", listStarSystems);
		model.addAttribute("currentUniverse", universeWithId);
		return "canvas";
	}

	/**
	 * the method accept a http request to return all planets of a star system
	 * the http request come with an id of the star system you want the planet of.
	 * the method search for the star system in the repository using the id
	 * if a match is found it return that star system's planets
	 * 
	 * @param starSystemId id of the starsystem you want
	 * @return the planets of the matching id star system
	 */
	@GetMapping("/planetfromstarsystem/{id}")
	public String getPlanetsFromStarSystemById(@PathVariable(value = "id") Integer starSystemId, Model model) {
		List<Object> planets = starSystemRepo.findPlanetsByStarSystem(starSystemId);
		model.addAttribute("planets", planets);
		return "planets_from_starsystem";
	}

	/**
	 * this method add a universe to the repository
	 * When the HTTP request is send
	 * universe is created
	 * and then added, saved and flushed
	 *
	 * @return repo with newly added universe
	 */
	@GetMapping("/generator")
	public String generateUniverse(Model model) {
		model.addAttribute("universe", new Universe());
		model.addAttribute("ListUniverses", universeRepo.findAll());
		return "generator";
	}

	@PostMapping("/generateuniverse")
	public String addUniverse(Universe universe, Model model) {
		model.addAttribute("universe", new Universe());
		model.addAttribute("ListUniverses", universeRepo.findAll());
		if (universe.getSize() == 0) {
			return "generate_fail";
		}
		Universe newUniverse = universe.generate(6, universe.getSize(), universe.getShape());
		universeRepo.save(newUniverse);
		return "generate_success";
	}

	@GetMapping("/StarSystemEditPage/{id}")
	public String starSystemPage(@PathVariable(value = "id") Integer starSystemId, Model model) {
		StarSystem starSystem = starSystemRepo.findById(starSystemId).get();
		model.addAttribute("starSystem", starSystem);
		return "Starsystem_PUT";
	}

	@PostMapping("/starSystemEdit/{id}")
	public String starSystemEdit(StarSystem starSystem, Model model) {
		ResponseEntity response = starSystemController.update(starSystem, starSystem.getStarsystemId());
		model.addAttribute("starSystem", starSystemRepo.findById(starSystem.getStarsystemId()).get());
		if (response.getStatusCode() == HttpStatus.OK) {
			return "Starsystem_PUT_success";
		}
		return "Starsystem_PUT_fail";
	}

	@GetMapping("/PlanetEditPage/{id}")
	public String planetPage(@PathVariable(value = "id") Integer planetId, Model model) {
		Planet planet = planetRepo.findById(planetId).get();
		model.addAttribute("planet", planet);
		return "PlanetEditPage";
	}

	@PostMapping("/planetEdit/{id}")
	public String planetEdit(Planet planet, Model model) {
		ResponseEntity response = planetController.update(planet, planet.getPlanetId());
		model.addAttribute("planet", planetRepo.findById(planet.getPlanetId()).get());
		if (response.getStatusCode() == HttpStatus.OK) {
			return "Planet_Edit_success";
		}
		return "Planet_Edit_fail";
	}

	@GetMapping("/universeSave/{id}")
	public ResponseEntity<InputStreamResource> saveFile(@PathVariable(value = "id") Integer universeId) {
		Universe universe = universeRepo.findById(universeId).get();
		String dir = "src/main/resources/saveFile/";
		String fileName = "universeSave.txt";
		File directory = new File(dir);
		File actualFile = new File(directory, fileName);
		try {
			FileWriter writer = new FileWriter(actualFile, false);
			writer.write("SaveFile\r\n");
			writer.write("Universe\r\n");
			writer.write(universe.toString() + "\r\n");
			List<StarSystem> starSystems = universe.getStarSystem();
			for (int i = 0; i < starSystems.size(); i++) {
				writer.write("StarSystem\r\n");
				writer.write(starSystems.get(i).toString() + "\r\n");
				List<Planet> planets = starSystems.get(i).getPlanets();
				for (int x = 0; x < planets.size(); x++) {
					writer.write("Planet\r\n");
					writer.write(planets.get(x).toString() + "\r\n");
				}
			}
			writer.close();
			InputStreamResource data = new InputStreamResource(new FileInputStream(actualFile));
			HttpHeaders http_Header = new HttpHeaders();
			http_Header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =" + actualFile.getName());
			http_Header.add("Cache-Control", "no-cache, no-store, must-revalidate");
			http_Header.add("Pragma", "no-cache");
			http_Header.add("Expires", "0");
			return ResponseEntity.ok().headers(http_Header)
					.contentLength(actualFile.length())
					.contentType(MediaType.parseMediaType("application/octet-stream")).body(data);

		} catch (IOException e) {
		}
		return null;
	}

	@PostMapping("/universeLoad")
	public ResponseEntity<?>  load(@RequestParam("file") MultipartFile file) {
		Universe universe = Universe.generate(0, 0, null);
		List<StarSystem> starSystems = new ArrayList<>();
		List<Planet> planets = new ArrayList<>();
		StarSystem starsystem = new StarSystem();
		Planet planet = new Planet();
		String fName = file.getOriginalFilename();
		try {
			if (fName.contains(".txt")) {
				System.out.println("run in here!");
				String line;
				BufferedReader buffer = new BufferedReader(new InputStreamReader(file.getInputStream()));
				line = buffer.readLine();
				if (line.contains("SaveFile") == false || line == null || file.isEmpty()) {
					input = false;
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				while ((line = buffer.readLine()) != null) {
					String[] lineArr = line.split(",");
					switch (lineArr[0]) {
						case "Universe":
							if ((line = buffer.readLine()) != null) {
								lineArr = line.split(",");
								lineArr[0] = lineArr[0].replace("shape= ", "");
								if (lineArr[0].equals("CLUSTER")) {
									universe.setShape(GalaxyShape.CLUSTER);
								} else if (lineArr[0].equals("SCATTER")) {
									universe.setShape(GalaxyShape.SCATTER);
								} else {
									universe.setShape(GalaxyShape.SCATTERED_CLUSTER);
								}
								lineArr[1] = lineArr[1].replace(" size= ", "");
								System.out.println(lineArr[1]);
								universe.setSize(Integer.parseInt(lineArr[1]));
								lineArr[2] = lineArr[2].replace(" universeName= ", "");
								universe.setUniverseName(lineArr[2]);
							}
							break;
						case "StarSystem":
							starsystem = new StarSystem();
							planets = new ArrayList<>();
							if ((line = buffer.readLine()) != null) {
								lineArr = line.split(",");
								lineArr[0] = lineArr[0].replace("name= ", "");
								starsystem.setName(lineArr[0]);
								lineArr[1] = lineArr[1].replace(" capital= ", "");
								if (lineArr[1].equals("true")) {
									starsystem.setCapital(true);
								} else {
									starsystem.setCapital(false);
								}
								lineArr[2] = lineArr[2].replace(" liegeSystemName= ", "");
								starsystem.setLiegeSystemName(lineArr[2]);
								lineArr[3] = lineArr[3].replace(" type= ", "");
								starsystem.setType(lineArr[3]);
								lineArr[4] = lineArr[4].replace(" goverment= ", "");
								starsystem.setGoverment(lineArr[4]);
								lineArr[5] = lineArr[5].replace(" population= ", "");
								starsystem.setPopulation(Long.parseLong(lineArr[5]));
								lineArr[6] = lineArr[6].replace(" economyLevel= ", "");
								starsystem.setEconomyLevel(Integer.parseInt(lineArr[6]));
								lineArr[7] = lineArr[7].replace(" spaceResources= ", "");
								starsystem.setSpaceResources(Integer.parseInt(lineArr[7]));
								int[] location = { 1, 2 };
								lineArr[8] = lineArr[8].replace(" location= [", "");
								location[0] = Integer.parseInt(lineArr[8]);
								lineArr[9] = lineArr[9].replace(" ", "");
								lineArr[9] = lineArr[9].replace("]", "");
								location[1] = Integer.parseInt(lineArr[9]);
								starsystem.setLocation(location);
								starSystems.add(starsystem);
							}
							break;
						case "Planet":
							planet = new Planet();
							if ((line = buffer.readLine()) != null) {
								lineArr = line.split(",");
								lineArr[0] = lineArr[0].replace("name= ", "");
								planet.setName(lineArr[0]);
								lineArr[1] = lineArr[1].replace(" size= ", "");
								planet.setSize(Integer.parseInt(lineArr[1]));
								lineArr[2] = lineArr[2].replace(" population= ", "");
								planet.setPopulation(Long.parseLong(lineArr[2]));
								lineArr[3] = lineArr[3].replace(" naturalResources= ", "");
								planet.setNaturalResources(Integer.parseInt(lineArr[3]));
								lineArr[4] = lineArr[4].replace(" economyLevel= ", "");
								planet.setEconomyLevel(Integer.parseInt(lineArr[4]));
								lineArr[5] = lineArr[5].replace(" economyType= ", "");
								planet.setEconomyType(lineArr[5]);
								lineArr[6] = lineArr[6].replace(" description= ", "");
								planet.setDescription(lineArr[6]);
								lineArr[7] = lineArr[7].replace(" type= ", "");
								planet.setType(lineArr[7]);
								planets.add(planet);
								starsystem.setPlanets(planets);
							}
							break;
					}
				}
				if (starSystems.size() != 0) {
					input = true;
					universe.setStarSystem(starSystems);
					universeRepo.saveAndFlush(universe);
				}
				buffer.close();
			} else {
				input = false;
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/load")
	public String loading(Model model) {
		model.addAttribute("ListUniverses", universeRepo.findAll());
		return "Load_File";
	}

	@GetMapping("/deleteUniverse")
	public String checkDeleteUniverse() {
		return "delete_Universe";
	}

	@GetMapping("/checkUpload")
	public String checkUpload() {
		if (!input) {
			return "upload_fail";
		}
		return "upload_success";
	}

	/**
	 * this method delete a planet from the repository
	 * it take an id and use it to find and delete a planet in the repositroy
	 * 
	 * @param planetId id of the planet you want to remove
	 */
	@DeleteMapping("/deleteplanet/{id}")
	public String delete(@PathVariable(value = "id") Integer planetId) {
		List<Universe> universeList = universeRepo.findAll();
		for (int i = 0; i < universeList.size(); i++) {
			boolean indicator = false;
			List<StarSystem> starSystems = universeList.get(i).getStarSystem();
			for (int x = 0; x < starSystems.size(); x++) {
				boolean indicator2 = false;
				List<Planet> Planets = starSystems.get(x).getPlanets();
				for (int y = 0; y < Planets.size(); y++) {
					if (Planets.get(y).getPlanetId() == planetId) {
						starSystems.get(x).setEconomyLevel(starSystems.get(x).getEconomyLevel() - Planets.get(y).getEconomyLevel());
						starSystems.get(x).setPopulation(starSystems.get(x).getPopulation() - Planets.get(y).getPopulation());
						starSystems.get(x).setSpaceResources(starSystems.get(x).getSpaceResources() - Planets.get(y).getNaturalResources());
						Planets.remove(y);
						indicator2 = true;
						break;
					}
				}
				if (indicator2 == true) {
					starSystems.get(x).setPlanets(Planets);
					indicator = true;
					break;
				}
			}
			if (indicator == true) {
				universeList.get(i).setStarSystem(starSystems);
				universeController.update(universeList.get(i), universeList.get(i).getUniverseId());
				break;
			}
		}
		planetRepo.deleteById(planetId);
		return "delete";
	}

	@DeleteMapping("/deleteUniverse/{id}")
	public String deleteUniverse(@PathVariable(value = "id") Integer universeId) {
		List<Universe> universe = universeRepo.findAll();
		boolean indicator = false;
		for (int i = 0; i < universe.size(); i++) {
			if (universe.get(i).getUniverseId() == universeId) {
				universe.remove(i);
			}
		}
		universeRepo.deleteById(universeId);
		return "universe_delete";
	}

	@DeleteMapping("/deletestarsystem/{id}")
	public String deletestarsystem(@PathVariable(value = "id") Integer starSystemId, Model model) {
		List<Universe> universes = universeRepo.findAll();
		for (int i = 0; i < universes.size(); i++) {
			boolean indicator = false;
			List<StarSystem> starSystems = universes.get(i).getStarSystem();
			for (int x = 0; x < starSystems.size(); x++) {
				if (starSystems.get(x).getStarsystemId() == starSystemId) {
					starSystems.remove(x);
					indicator = true;
					break;
				}
			}
			if (indicator == true) {
				if (starSystems.size() == 0) {
					deleteUniverse(universes.get(i).getUniverseId());
					return "universe_delete";
				}
				universes.get(i).setStarSystem(starSystems);
				universeController.update(universes.get(i), universes.get(i).getUniverseId());
				break;
			}
		}
		starSystemRepo.deleteById(starSystemId);
		return "delete";
	}
}
