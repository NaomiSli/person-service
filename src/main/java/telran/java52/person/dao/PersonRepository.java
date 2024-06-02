package telran.java52.person.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java52.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	List<Person> findByAddressCity(String city);
	
	List<Person> findByBirthDateBetween(LocalDate fromDate, LocalDate toDate);
	
	List<Person> findByName(String name);
	
}
