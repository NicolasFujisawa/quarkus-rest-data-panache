package org.acme.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.acme.model.Person;
import org.acme.model.Status;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {
    
    public Person findByName(String name) {
        return find("name", name).firstResult();
    }

    public List<Person> findAlive() {
        return list("status", Status.Alive);
    }

    public void deleteDeads() {
        delete("status", Status.Dead);
    }
}