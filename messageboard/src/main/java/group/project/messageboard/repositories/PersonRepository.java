package group.project.messageboard.repositories;

import org.springframework.data.repository.CrudRepository;

import group.project.messageboard.models.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

}