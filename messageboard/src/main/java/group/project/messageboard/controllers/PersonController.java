package group.project.messageboard.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import group.project.messageboard.models.Person;
import group.project.messageboard.repositories.PersonRepository;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public Iterable<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @PostMapping(value="/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody Person post) {
        return personRepository.save(post);
    }

    // TODO: Modify this PUT endpoint so that it updates an existing person by ID
    @PutMapping("/{id}")
    public Person updatePersonById(@PathVariable Long id, @RequestBody Person person) {
        Optional<Person> foundPerson = personRepository.findById(id);
        return personRepository.updatePerson(foundPerson.get(), person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePerson(@PathVariable Long id)
    {
        personRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable Long id)
    {
        return personRepository.findById(id).get();
    }
}
