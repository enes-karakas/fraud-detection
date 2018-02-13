package be.kdg.fraudedetection.bl.dom;

/**
 * @author Loïc Gijsemans
 * @version 1.0 12/02/18 16:47 *
 */

import be.kdg.fraudedetection.presentation.DTO.AccidentDTO;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Accident {
    @GraphId private Long id;
    private String name;
    private String date;
    private Integer userId;

    private Accident() {
        // Empty constructor required as of Neo4j API 2.0.5
    };


    public Accident(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public Accident(AccidentDTO accidentDTO) {
        this.name = accidentDTO.getName();
        this.date = accidentDTO.getDate();
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Person> getWitnesses() {
        return witnesses;
    }

    public void setWitnesses(Set<Person> witnesses) {
        this.witnesses = witnesses;
    }

    public Set<Person> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Person> drivers) {
        this.drivers = drivers;
    }

    public Set<Person> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Person> passengers) {
        this.passengers = passengers;
    }
}
