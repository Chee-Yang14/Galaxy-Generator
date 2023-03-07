
package ics499.GalaxyGenerator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ics499.GalaxyGenerator.model.Planet;
import ics499.GalaxyGenerator.model.StarSystem;

@Repository
public interface StarSystemRepository extends JpaRepository<StarSystem, Integer> {
  @Query(nativeQuery = true, value = "select p1_1.planet_id,p1_1.description,p1_1.economy_level,p1_1.economy_type,p1_1.location,p1_1.name,p1_1.natural_resources,p1_1.population,p1_1.size,p1_1.type from starsystem s1_0 left join (starsystem_planets p1_0 join planet p1_1 on p1_1.planet_id=p1_0.planets_planet_id) on s1_0.starsystem_id=p1_0.star_system_starsystem_id where s1_0.starsystem_id=:id")
  List<Object> findPlanetsByStarSystem(@Param("id") Integer id);

}