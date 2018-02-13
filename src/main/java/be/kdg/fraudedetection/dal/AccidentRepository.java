package be.kdg.fraudedetection.dal;

/**
 * @author Loïc Gijsemans
 * @version 1.0 12/02/18 17:32 *
 */

import be.kdg.fraudedetection.bl.dom.Accident;
import be.kdg.fraudedetection.bl.dom.Person;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface AccidentRepository extends GraphRepository<Accident> {
    Person findByDrivers(Person person);
    Person findByPassengers(Person person);
    Person findByWitnesses(Person person);


}