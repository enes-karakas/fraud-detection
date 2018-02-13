package be.kdg.fraudedetection.bl.dom;

/**
 * @author Loïc Gijsemans
 * @version 1.0 12/02/18 16:37 *
 */

import be.kdg.fraudedetection.bl.dom.RoleClaim;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Person {
    @GraphId private Long id;
    private String name;
    private RoleClaim role;

    private Person() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

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
        return role.toString();
    }
}