package tv.daos;

import org.junit.Before;
import org.junit.Test;
import tv.SessionTest;
import tv.TVStation;
import tv.daos.people.ActorDAO;
import tv.daos.people.PersonDAO;
import tv.daos.people.ReporterDAO;
import tv.daos.people.TVWorkerDAO;
import tv.people.Actor;
import tv.people.Person;
import tv.people.Reporter;
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
    PersonDAO pdao;
    ActorDAO adao;
    ReporterDAO rdao;
    TVWorkerDAO wdao;

    @Before
    public void setUp(){
        newSessionFactoryAndTransaction();
        pdao = new PersonDAO();
        adao = new ActorDAO();
        rdao = new ReporterDAO();
        wdao = new TVWorkerDAO();
    }

    @Test
    public void testCreatePerson(){
        pdao.create("Jan", "Kowalski");
        commitAndCreateNewTransaction();
        List<Person> persons = pdao.getAll();
        assertEquals(1, persons.size());
        assertEquals("Jan", persons.get(0).getName());
    }

    @Test
    public void testCreateTVWorker(){
        wdao.create("Jan", "Kowalski", TVStation.TV_1);
        commitAndCreateNewTransaction();
        List<Person> persons = pdao.getAll();
        List<TVWorker> tvWorkers = wdao.getAll();
        assertEquals(1, persons.size());
        assertEquals(1, tvWorkers.size());
        assertEquals("Jan", tvWorkers.get(0).getName());
    }

    @Test
    public void testCreateActor(){
        adao.create("Jan", "Kowalski", TVStation.TV_7, (short)3);
        commitAndCreateNewTransaction();
        List<Person> persons = pdao.getAll();
        List<Actor> actors = adao.getAll();
        List<TVWorker> tvWorkers = wdao.getAll();
        assertEquals(1, persons.size());
        assertEquals(1, tvWorkers.size());
        assertEquals(1, actors.size());
        assertEquals("Jan", actors.get(0).getName());
    }

    @Test
    public void testDeletePersonById(){
        Long id = pdao.create("A", "B");
        commitAndCreateNewTransaction();
        pdao.delete(id);
        commitAndCreateNewTransaction();
        List<Person> persons = pdao.getAll();
        assertEquals(0, persons.size());
    }

    @Test
    public void testDeletePerson(){
        Long id = pdao.create("A", "B");
        commitAndCreateNewTransaction();
        Person p = (Person)pdao.getAll().get(0);
        pdao.delete(p);
        commitAndCreateNewTransaction();
        List<Person> persons = pdao.getAll();
        assertEquals(0, persons.size());
    }


    @Test
    public void testDeleteActor(){
        adao.create("A", "B", TVStation.TV_1, (short)3);
        commitAndCreateNewTransaction();
        adao.delete(new Long(1));
        commitAndCreateNewTransaction();
        List<Person> persons = pdao.getAll();
        List<TVWorker> tvWorkers  = wdao.getAll();
        List<Actor> actors = adao.getAll();
        assertEquals(0, persons.size());
        assertEquals(0, tvWorkers.size());
        assertEquals(0, actors.size());
        assertEquals(0, session.createSQLQuery("SELECT * FROM ACTORS").list().size());
    }

    @Test
    public void testDifferentPeople(){
        adao.create("A", "B", TVStation.TV_1, (short)3);
        rdao.create("A", "B", TVStation.TV_7, "assassin");
        commitAndCreateNewTransaction();
        List<Person> persons = pdao.getAll();
        List<Actor> actors = adao.getAll();
        List<Reporter> reporters = rdao.getAll();
        assertEquals(2, persons.size());
        assertEquals(1, actors.size());
        assertEquals(1, reporters.size());
    }

    @Test
    public void testEdit(){
        pdao.create("Jan", "Kowalski");
        commitAndCreateNewTransaction();
        Person p = (Person)pdao.getAll().get(0);
        p.setName("Janka");
        pdao.save(p);
        commitAndCreateNewTransaction();
        List<Person> persons = pdao.getAll();
        assertEquals(1, persons.size());
        assertEquals("Janka", persons.get(0).getName());
    }
}
