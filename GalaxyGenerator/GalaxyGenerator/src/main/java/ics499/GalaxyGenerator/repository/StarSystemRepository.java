
package ics499.GalaxyGenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ics499.GalaxyGenerator.model.StarSystem;

@Repository
public interface StarSystemRepository extends JpaRepository<StarSystem, Integer> {
  
}