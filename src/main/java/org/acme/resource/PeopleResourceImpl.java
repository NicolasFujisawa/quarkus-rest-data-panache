package org.acme.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.model.Person;
import org.acme.repository.PersonRepository;

import io.quarkus.panache.common.Sort;

@Path("/people")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PeopleResourceImpl {
    
    @Inject
    private PersonRepository personRepository;

    @Transactional
    @POST
    public Response add(Person person) {
        if(person.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        person.persist();
        return Response.ok(person).status(201).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        Person person = personRepository.findById(id);
        if(person.id == null) {
            throw new WebApplicationException("Person with id of " + id + " does not exist.", 404);
        }
        person.delete();
    }

    @Transactional
    @GET
    @Path("{id}")
    public Person get(@PathParam("id") Long id) {
        Person person = personRepository.findById(id);
        if(person == null) {
            throw new WebApplicationException("Person with id of " + id + " does not exist.", 404);
        }
        return person;
    }

    @Transactional
    public Response list() {
        return Response.ok(personRepository.listAll(Sort.by("name"))).build();
    }

    @Transactional
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Person person) {
        if(person.getName() == null) {
            throw new WebApplicationException("Person Name was not set on request.", 422);
        }

        Person entity = personRepository.findById(id);
        if(entity == null) {
            throw new WebApplicationException("Person with id of " + id + " does not exist.", 404);
        }
        entity.setName(person.getName());
        entity.setBirth(person.getBirth());
        entity.setStatus(person.getStatus());
        return Response.ok(entity).status(202).build();
    }
    
}