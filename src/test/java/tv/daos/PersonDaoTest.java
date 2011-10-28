package tv.daos;

import org.junit.Test;
import tv.SessionTest;
import tv.TVStation;
import tv.daos.people.PersonDAO;
import tv.people.Person;
import tv.people.TVWorker;

import static org.junit.Assert.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 29.10.11
 * Time: 01:30
 * To change this template use File | Settings | File Templates.
 */
public class PersonDaoTest extends SessionTest{

    @Test
    public void testCreatePerson(){
        newSessionFactoryAndTransaction();
        PersonDAO dao = new PersonDAO();
        Person p = dao.createPerson("Jan", "Kowalski");
        commitAndCreateNewTransaction();
        List<Person> persons = dao.getQuery().list();
        assertEquals(1, persons.size());
        assertEquals(p.getId(), persons.get(0).getId());
    }

    @Test
    public void testCreateTVWorker(){
        newSessionFactoryAndTransaction();
        PersonDAO dao = new PersonDAO();
        TVWorker p = dao.createTVWorker("Jan", "Kowalski", TVStation.TV_1);
        commitAndCreateNewTransaction();
        List<Person> persons = dao.getQuery().list();
        List<TVWorker> tvWorkers = dao.getQuery("TVWorker").list();
        assertEquals(1, persons.size());
        assertEquals(1, tvWorkers.size());
        assertEquals(p.getId(), tvWorkers.get(0).getId());
    }
}
