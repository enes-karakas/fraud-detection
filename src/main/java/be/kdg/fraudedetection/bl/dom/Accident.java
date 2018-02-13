package be.kdg.fraudedetection.bl.dom;

/**
 * @author Loïc Gijsemans
 * @version 1.0 12/02/18 16:47 *
 */

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Accident {
    @GraphId private Long id;
    private String name;

    private Accident() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    public Accident(String name) {
        this.name = name;
    }

    @Relationship(type = "WITNESS", direction = Relationship.UNDIRECTED)
    public Set<Person> witnesses;
    @Relationship(type = "DRIVER", direction = Relationship.UNDIRECTED)
    public Set<Person> drivers;
    @Relationship(type = "PASSENGER", direction = Relationship.UNDIRECTED)
    public Set<Person> passengers;

    public void IsPartOfClaims(Person person) {

        if (person.getRole().equals(RoleClaim.PASSENGER.getRole())) {
            if (passengers == null) {
                passengers = new HashSet<>();
            }
            passengers.add(person);
        }
        if (person.getRole().equals(RoleClaim.DRIVER.getRole())) {
            if (drivers == null) {
                drivers = new HashSet<>();
            }
            drivers.add(person);
        }
        if (person.getRole().equals(RoleClaim.WITNESS.getRole())) {
            if (witnesses == null) {
                witnesses = new HashSet<>();
            }
            witnesses.add(person);
        }
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
