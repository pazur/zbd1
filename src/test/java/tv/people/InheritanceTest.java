package tv.people;

import org.junit.Test;
import tv.SessionTest;
import tv.TVStation;
import static org.junit.Assert.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 28.10.11
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */
public class InheritanceTest extends SessionTest{

    @Test
    public void testPeopleInheritance(){
        newSessionFactoryAndTransaction();
        Actor a = new Actor();
        a.setRating((short) 3);
        a.setName("Jan");
        a.setSurname("Kowalski");
        a.setStation(TVStation.TV_1);
        save(a);
        commitAndCreateNewTransaction();
        List<Person> persons= session.createQuery("from Person").list();
        List<TVWorker> tvWorkers= session.createQuery("from TVWorker").list();
        List<Actor> actors= session.createQuery("from Actor").list();
        List<Actor> reporters= session.createQuery("from Reporter").list();
        commit();
        assertEquals(1, persons.size());
        assertEquals(1, tvWorkers.size());
        assertEquals(1, actors.size());
        assertEquals(0, reporters.size());
        assertEquals(actors.get(0).getId(), tvWorkers.get(0).getId());
        assertEquals(actors.get(0).getId(), persons.get(0).getId());
    }
}
