package group.project.messageboard.repositories;

import org.springframework.data.repository.CrudRepository;

import group.project.messageboard.models.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

    public default Person updatePerson(Person existingPerson, Person newPerson) {
        existingPerson.setEmail(newPerson.getEmail());
        existingPerson.setFirstName(newPerson.getFirstName());
        existingPerson.setLastName(newPerson.getLastName());
        existingPerson.setEmail(newPerson.getEmail());
        existingPerson.setPostList(newPerson.getPostList());
        existingPerson.setPersonRole(newPerson.getPersonRole());
        return this.save(existingPerson);
    }

    Person findByEmail(String email);
}