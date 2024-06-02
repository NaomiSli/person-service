package telran.java52.person.service;

import java.time.LocalDate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import telran.java52.person.dao.PersonRepository;
import telran.java52.person.dto.AddressDto;
import telran.java52.person.dto.PersonDto;
import telran.java52.person.dto.exceptions.PersonNotFoundException;
import telran.java52.person.model.Address;
import telran.java52.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
	final PersonRepository personRepository;
	final ModelMapper modelMapper;

	@Transactional
	@Override
	public Boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		personRepository.save(modelMapper.map(personDto, Person.class));
		return true;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public Iterable<PersonDto> findByCity(String city) {
		return personRepository.findByAddressCity(city).stream().map(person -> modelMapper.map(person, PersonDto.class))
				.toList();
	}

	@Override
	public Iterable<PersonDto> findByAge(Integer minAge, Integer maxAge) {
		LocalDate now = LocalDate.now();
		LocalDate minDate = now.minusYears(maxAge);
		LocalDate maxDate = now.minusYears(minAge);

		return personRepository.findByBirthDateBetween(minDate, maxDate)
				.stream()
				.map(person -> modelMapper.map(person, PersonDto.class))
				.toList();
	}

	@Override
	public PersonDto updateName(Integer id, String name) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		if( name == null) {
			person.setName(name);
		}
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public Iterable<PersonDto> findByName(String name) {
		return personRepository.findByName(name)
				.stream()
				.map(person -> modelMapper.map(person, PersonDto.class))
				.toList();
	}

	@Override
	public PersonDto updateAddress(Integer id, AddressDto address) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		if (address != null) {
			Address newAddress = modelMapper.map(address, Address.class);
			person.setAddress(newAddress);
		}
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}
	

	@Override
	public PersonDto deletePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		personRepository.delete(person);
		return modelMapper.map(person, PersonDto.class);
	}

}



