package tv.services;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 09.11.11
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 */

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import services.PeopleService;
import services.Service;
import tv.TVStation;
import tv.people.Actor;
import util.HibernateUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PeopleServiceTest {

    @Before
    public void before(){
        HibernateUtil.getNewSessionFactory();
    }

    @Test
    public void testSimpleService() throws Exception {
        PeopleService service = new PeopleService();
        service.createActor("Jan", "Kowalski", TVStation.TV_7, (short)2);
        List<Actor> actors = service.getAll(Actor.class);
        assertEquals(1, actors.size());
        assertEquals("Jan", actors.get(0).getName());
    }

    @Test
    public void testSaveAndUpdate() throws Exception {
        PeopleService service = new PeopleService();
        Actor a = new Actor("Jan", "Kowalski", TVStation.TV_7, (short)2);
        Actor b = new Actor("Janina", "Kowalski", TVStation.TV_7, (short)1);
        service.save(a);
        service.save(b);
        a.setName("Roman");
        service.update(a);
        List<Actor> actors = service.getAll(Actor.class);
        assertEquals(2, actors.size());
        assertEquals("Roman", ((Actor)service.get(a.getId(), Actor.class)).getName());
        assertEquals("Janina", ((Actor)service.get(b.getId(), Actor.class)).getName());
    }

    @Test
    public void testGetByCriterions() throws Exception {
        PeopleService service = new PeopleService();
        service.createActor("Jan", "Kowalski", TVStation.TV_7, (short)2);
        service.createActor("Janina", "Kowalski", TVStation.TV_7, (short)1);
        service.createActor("Jan", "Kowal", TVStation.TV_7, (short)1);
        List<Actor> actors = service.getByCriterions(Actor.class, new ArrayList());
        List criterions = new ArrayList();
        criterions.add(Restrictions.eq("name", "Jan"));
        List<Actor> jans = service.getByCriterions(Actor.class, criterions);
        assertEquals(3, actors.size());
        assertEquals(2, jans.size());
    }

    @Test
    public void testGetEpisodes() throws Exception {
        PeopleService service = new PeopleService();
        Long id = service.createActor("Jan", "Kowalski", TVStation.TV_7, (short)2);
        Actor a = (Actor)service.get(id, Actor.class);
        assertEquals(0, a.getEpisodes().size());
    }
}
