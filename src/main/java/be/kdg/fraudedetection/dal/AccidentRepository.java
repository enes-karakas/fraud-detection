package be.kdg.fraudedetection.dal;

/**
 * @author Loïc Gijsemans
 * @version 1.0 12/02/18 17:32 *
 */

import java.util.List;

import be.kdg.fraudedetection.bl.Accident;
import be.kdg.fraudedetection.bl.Person;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.CrudRepository;

public interface AccidentRepository extends GraphRepository<Accident> {
    Person findByDrivers1(Person person);
    Person findByDrivers2(Person person);
    Person findByPassengers(Person person);
    Person findByWitnesses(Person person);


}