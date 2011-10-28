package tv.daos;

import org.junit.Before;
import org.junit.Test;
import tv.SessionTest;
import tv.TVStation;
import tv.daos.people.PersonDAO;
import tv.people.Actor;
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
    PersonDAO dao;

    @Before
    public void setUp(){
        newSessionFactoryAndTransaction();
        dao = new PersonDAO();
    }

    @Test
    public void testCreatePerson(){
        Person p = dao.createPerson("Jan", "Kowalski");
        commitAndCreateNewTransaction();
        List<Person> persons = dao.getQuery().list();
        assertEquals(1, persons.size());
        assertEquals(p.getId(), persons.get(0).getId());
    }

    @Test
    public void testCreateTVWorker(){
        TVWorker p = dao.createTVWorker("Jan", "Kowalski", TVStation.TV_1);
        commitAndCreateNewTransaction();
        List<Person> persons = dao.getQuery().list();
        List<TVWorker> tvWorkers = dao.getQuery("TVWorker").list();
        assertEquals(1, persons.size());
        assertEquals(1, tvWorkers.size());
        assertEquals(p.getId(), tvWorkers.get(0).getId());
    }

    @Test
    public void testUpdateActor(){
        Actor a = dao.createActor("Jan", "Kowalski", TVStation.TV_7, (short)3);
        commitAndCreateNewTransaction();
        dao.updateRating(a, (short)5);
        commitAndCreateNewTransaction();
        List<Actor> actors = dao.getQuery("Actor").list();
        assertEquals(1, actors.size());
        assertEquals(5, actors.get(0).getRating());
    }

    @Test
    public void testDeletePerson(){
        Person p = dao.createPerson("A", "B");
        commitAndCreateNewTransaction();
        dao.deletePerson(p);
        commitAndCreateNewTransaction();
        List<Person> persons = dao.getQuery().list();
        assertEquals(0, persons.size());
    }
    @Test
    public void testDeleteActor(){
        Actor p = dao.createActor("A", "B", TVStation.TV_1, (short)3);
        commitAndCreateNewTransaction();
        dao.deletePerson(p);
        commitAndCreateNewTransaction();
        List<Person> persons = dao.getQuery().list();
        List<TVWorker> tvWorkers  = dao.getQuery("TVWorker").list();
        List<Actor> actors = dao.getQuery("Actor").list();
        assertEquals(0, persons.size());
        assertEquals(0, tvWorkers.size());
        assertEquals(0, actors.size());
        assertEquals(0, session.createSQLQuery("SELECT * FROM ACTORS").list().size());
    }

}
