package tv.productions;

import org.junit.Test;
import static org.junit.Assert.*;
import tv.SessionTest;
import tv.people.Actor;

import java.util.HashSet;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.11.11
 * Time: 12:53
 * To change this template use File | Settings | File Templates.
 */
public class EpisodeTest extends SessionTest{
//    @Test
    public void testFields(){
        newSessionFactoryAndTransaction();
        TVSeries s = new TVSeries();
        s.setTitle("Klan");
        save(s);
        Episode e = new Episode();
        e.setNumber((short)1);
        e.setSeason((short)1);
        e.setActors(new HashSet<Actor>());
        e.setSeries(s);
        save(e);
        commitAndCreateNewTransaction();
        List<Episode> episodes = session.createQuery("from Episode").list();
        assertEquals(1, episodes.size());
        Episode episode = episodes.get(0);
        assertEquals(s.getId(), episode.getSeries().getId());
        assertEquals(e.getNumber(), episode.getNumber());
        assertEquals(e.getSeason(), episode.getSeason());
    }

    @Test
    public void testActors(){
        newSessionFactoryAndTransaction();
        Actor a1 = new Actor();
        a1.setRating((short)1);
        Actor a2 = new Actor();
        a2.setRating((short)1);
        save(a1);
        save(a2);
        HashSet<Actor> actors= new HashSet<Actor>();
        actors.add(a1);
        actors.add(a2);
        TVSeries s = new TVSeries();
        s.setTitle("Klan");
        save(s);
        Episode e = new Episode();
        e.setNumber((short)1);
        e.setSeason((short)1);
        e.setSeries(s);
        e.setActors(actors);
        save(e);
        session.update(e);
        commitAndCreateNewTransaction();
        List<Episode> episodes = session.createQuery("from Episode").list();
        assertEquals(1, episodes.size());
        Episode episode = episodes.get(0);
        assertEquals(s.getId(), episode.getSeries().getId());
        assertEquals(e.getNumber(), episode.getNumber());
        assertEquals(e.getSeason(), episode.getSeason());
        assertEquals(2, episode.getActors().size());
    }
}
