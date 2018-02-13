package be.kdg.fraudedetection.dal.neo4j;

/**
 * @author Loïc Gijsemans
 * @version 1.0 12/02/18 17:32 *
 */

import be.kdg.fraudedetection.bl.dom.Accident;
import be.kdg.fraudedetection.bl.dom.Person;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;


public interface AccidentRepository extends GraphRepository<Accident> {
    Person findByDrivers(Person person);

    Person findByPassengers(Person person);

    Person findByWitnesses(Person person);

    List<Accident> findAccidentsByUserId(Integer userId);

    List<Long> removeAllByUserId(Integer userId);



}