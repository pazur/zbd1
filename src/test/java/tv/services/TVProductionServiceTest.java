package tv.services;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 10.11.11
 * Time: 16:47
 * To change this template use File | Settings | File Templates.
 */
import org.junit.Test;
import services.PeopleService;
import services.TVProductionService;
import tv.people.Actor;
import tv.productions.TVSeries;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;


public class TVProductionServiceTest {

    @Test
    public void testCreating(){
        TVProductionService tvservice = new TVProductionService();
        Long s1id = tvservice.createTVSeries("A");
        Long s2id = tvservice.createTVSeries("B");
        Long s3id = tvservice.createTVSeries("C");
        TVSeries s1 = tvservice.getTVSeries(s1id);
        TVSeries s2 = tvservice.getTVSeries(s2id);
        TVSeries s3 = tvservice.getTVSeries(s3id);
        Long e111 = tvservice.createEpisode((short) 1, (short) 1, s1, new HashSet<Actor>());
        Long e112 = tvservice.createEpisode((short) 1, (short) 2, s1, new HashSet<Actor>());
        Long e121 = tvservice.createEpisode((short) 2, (short) 1, s1, new HashSet<Actor>());

        Long e211 = tvservice.createEpisode((short) 1, (short) 1, s2, new HashSet<Actor>());
        Long e221 = tvservice.createEpisode((short)2, (short)1, s2, new HashSet<Actor>());
        Long e231 = tvservice.createEpisode((short)3, (short)1, s2, new HashSet<Actor>());

        Long e311 = tvservice.createEpisode((short)1, (short)1, s3, new HashSet<Actor>());
        List<TVSeries> longestEpisodes = tvservice.getLongestByEpisodes();
        List<TVSeries> longestSeasons = tvservice.getLongestBySeasons();
        assertEquals(1, longestSeasons.size());
        assertEquals(2, longestEpisodes.size());

        Long e131 = tvservice.createEpisode((short) 3, (short) 1, s1, new HashSet<Actor>());
        longestEpisodes = tvservice.getLongestByEpisodes();
        longestSeasons = tvservice.getLongestBySeasons();
        assertEquals(2, longestSeasons.size());
        assertEquals(1, longestEpisodes.size());
    }

}
