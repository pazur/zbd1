package tv.services;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 09.11.11
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 */

import org.junit.Before;
import org.junit.Test;
import services.PeopleService;
import services.Service;
import tv.TVStation;
import tv.people.Actor;
import util.HibernateUtil;

import java.util.List;

import static org.junit.Assert.*;

public class PeopleServiceTest {

//    @Before
//    public void before(){
//        HibernateUtil.getNewSessionFactory();
//    }
//
//    @Test
//    public void testSimpleService() throws Exception {
//        PeopleService service = new PeopleService();
//        service.createActor("Jan", "Kowalski", TVStation.TV_7, (short)2);
//        List<Actor> actors = service.getAll(Actor.class);
//        assertEquals(1, actors.size());
//        assertEquals("Jan", actors.get(0).getName());
//    }

    @Test
    public void testSaveAndUpdate() throws Exception {
        PeopleService service = new PeopleService();
        Actor a = new Actor("Jan", "Kowalski", TVStation.TV_7, (short)2);
        Actor b = new Actor("Janina", "Kowalski", TVStation.TV_7, (short)1);
        service.save(a);
        //service.save(b);
        //a.setName("Roman");
        //service.save(a);
        List<Actor> actors = service.getAll(Actor.class);
        assertEquals(1, actors.size());
        Actor act = (Actor)service.get(a.getId(), Actor.class);
        System.out.print("DDDDDDDDDDDDDDDDDDD ");
        System.out.println(HibernateUtil.getSessionFactory().getCurrentSession());
        HibernateUtil.getSessionFactory().getCurrentSession().update(act);
        System.out.print("DDDDDDDDDDDDDDDDDDD ");
        System.out.println(HibernateUtil.getSessionFactory().getCurrentSession());
        act.getName();
        //assertEquals("Jan", ((Actor)service.get(a.getId(), Actor.class)).getName());
        //assertEquals("Janina", service.get(b.getId(), Actor.class));
    }
}
