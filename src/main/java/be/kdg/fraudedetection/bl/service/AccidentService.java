package be.kdg.fraudedetection.bl.service;

import be.kdg.fraudedetection.bl.dom.Accident;
import be.kdg.fraudedetection.dal.neo4j.AccidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("claimService")
@Transactional
public class AccidentService {
    private final AccidentRepository repo;

    @Autowired
    public AccidentService(AccidentRepository repo) {
        this.repo = repo;
    }

    public Accident saveAccident(Accident accident) {
        final Accident acc = this.repo.save(accident);
        if (acc == null) {
            throw new RuntimeException(String.format("Accident with name \"%s\" and date \"%s\" could not be saved.", accident.getName(), accident.getDate()));
        }

        return acc;
    }

    public List<Accident> getAccidentsForUser(Integer userId) {
        final List<Accident> accidentsByUserId = this.repo.findAccidentsByUserId(userId);

        if (accidentsByUserId == null) {
            throw new RuntimeException("No accidents for user with ID: " + userId + ".");
        }

        return accidentsByUserId;
    }

    public List<Long> deleteAllAccidentsByUserId(Integer userId) {
       return repo.removeAllByUserId(userId);
    }
}
