package be.kdg.fraudedetection.bl.dom;

/**
 * @author Loïc Gijsemans
 * @version 1.0 12/02/18 16:37 *
 */

import be.kdg.fraudedetection.bl.dom.RoleClaim;
import be.kdg.fraudedetection.presentation.DTO.PersonDTO;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NodeEntity
public class Person {
    @GraphId private Long id;
    private String name;
    private String role;

    private Person() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    public Person(PersonDTO personDTO) {
        this.name = personDTO.getName();
        this.role = personDTO.getRole();
    }


    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return this.role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(name, person.name) &&
                Objects.equals(role, person.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, role);
    }
}