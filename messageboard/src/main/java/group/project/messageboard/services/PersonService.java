package group.project.messageboard.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import group.project.messageboard.models.Person;
import group.project.messageboard.repositories.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    
	public Iterable<Person> findAll() {
		return personRepository.findAll();
	}
	
	public Person createPerson(Person post) {
        return personRepository.save(post);
    }

	public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
	
	public Person getById(Long id) {
        return personRepository.findById(id).get();
    }
	
	public Person updatePersonById(Long id, Person person) {
        Optional<Person> foundPerson = personRepository.findById(id);
        return personRepository.updatePerson(foundPerson.get(), person);
    }
}
