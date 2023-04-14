package group.project.messageboard.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import group.project.messageboard.models.Person;
import group.project.messageboard.services.PersonService;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = "http://localhost:3000")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public Iterable<Person> getAllPersons() {
        return personService.findAll();
    }

    @PostMapping(value="/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody Person post) {
        return personService.createPerson(post);
    }

    @PutMapping("/{id}")
    public Person updatePersonById(@PathVariable Long id, @RequestBody Person person) {
        return personService.updatePersonById(id, person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePerson(@PathVariable Long id)
    {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable Long id)
    {
        return personService.getById(id);
    }
}
