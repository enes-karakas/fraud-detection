package be.kdg.fraudedetection.bl;

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
    @Relationship(type = "DRIVER1", direction = Relationship.UNDIRECTED)
    public Set<Person> drivers1;
    @Relationship(type = "DRIVER2", direction = Relationship.UNDIRECTED)
    public Set<Person> drivers2;
    @Relationship(type = "PASSENGER", direction = Relationship.UNDIRECTED)
    public Set<Person> passengers;

    public void IsPartOfClaims(Person person) {

        if (person.getRole().equals("PASSENGER")) {
            if (passengers == null) {
                passengers = new HashSet<>();
            }
            passengers.add(person);
        }
        if (person.getRole().equals("DRIVER")) {
            if (drivers1 == null) {
                drivers1 = new HashSet<>();
            }
            drivers1.add(person);
        }
        if (person.getRole().equals("WITNESS")) {
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
