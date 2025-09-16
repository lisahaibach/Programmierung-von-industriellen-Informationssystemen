package de.iu.ghostnet.repository;

import de.iu.ghostnet.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> { }