package org.acme.resource;

import org.acme.model.Person;
import org.acme.repository.PersonRepository;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;

public interface PeopleResource extends PanacheRepositoryResource<PersonRepository, Person, Long> {
    
}