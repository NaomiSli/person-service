package telran.java52.person.service;


import telran.java52.person.dto.AddressDto;
import telran.java52.person.dto.PersonDto;

public interface PersonService {
	Boolean addPerson(PersonDto personDto);
	
	PersonDto findPersonById(Integer id);
	
	Iterable<PersonDto> findByCity(String city);
	
	Iterable<PersonDto> findByAge(Integer minAge, Integer maxAge);
	
	PersonDto updateName(Integer id,String name);
	
	Iterable<PersonDto> findByName(String name);
	
//	Iterable<CityPopulationDto> getCityPopulation(List<String> cities);
	
	PersonDto updateAddress(Integer id, AddressDto address);

	PersonDto deletePerson(Integer id);
}
