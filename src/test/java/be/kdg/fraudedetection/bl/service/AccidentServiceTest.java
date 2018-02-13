package be.kdg.fraudedetection.bl.service;

import be.kdg.fraudedetection.bl.dom.Accident;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableNeo4jRepositories(basePackages = "be.kdg.fraudedetection.dal.neo4j")
public class AccidentServiceTest {
    @Autowired
    private AccidentService service;

    private final Integer USER_ID = -1;

    @Before
    public void setUp() throws Exception {
        Accident acc1 = new Accident("acc1", "05/08/2018 11:32");
        acc1.setUserId(USER_ID);
        Accident acc2 = new Accident("acc2", "06/07/2017 11:22");
        acc2.setUserId(USER_ID);
        Accident acc3 = new Accident("acc3", "01/01/2016 11:32");
        acc3.setUserId(USER_ID);
        service.saveAccident(acc1);
        service.saveAccident(acc2);
        service.saveAccident(acc3);
    }

    @Test
    public void testSaveAccident() {
        Accident accident = new Accident("acc1", "05/05/2018 10:38");
        accident.setUserId(USER_ID);
        Accident acc = service.saveAccident(accident);
        assertThat(acc, is(notNullValue()));
        assertThat(acc.getName(), is(equalTo(accident.getName())));
        assertThat(acc.getDate(), is(equalTo(accident.getDate())));
        assertThat(acc.getId(), is(notNullValue()));
        assertThat(acc.getUserId(), is(equalTo(USER_ID)));
    }

    /*
    MATCH (n)
DETACH DELETE n
     */

    @Test
    public void testGetAccidentsForUser() {
        List<Accident> accidentsForUser = service.getAccidentsForUser(USER_ID);
        assertThat(accidentsForUser, hasSize(3));
    }

    @After
    public void tearDown() throws Exception {
        service.deleteAllAccidentsByUserId(USER_ID);
    }
}