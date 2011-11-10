package tv.services;

import com.sun.org.apache.regexp.internal.RE;
import org.junit.Before;
import org.junit.Test;
import services.PeopleService;
import services.ReportageService;
import services.TVProductionService;
import tv.TVStation;
import tv.people.Person;
import tv.people.Reporter;
import tv.productions.News;
import tv.productions.Reportage;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 10.11.11
 * Time: 00:53
 * To change this template use File | Settings | File Templates.
 */
public class ReportageServiceTest{
    @Before
    public void before(){
        HibernateUtil.getNewSessionFactory();
    }

    @Test
    public void testCreateManyVersions() throws Exception {
        Long authorId = new PeopleService().createReporter("Jan", "Kowalski", TVStation.TV_1, "Bla");
        Reporter author = (Reporter)new PeopleService().get(authorId, Reporter.class);
        ReportageService service = new ReportageService();
        Long id0 = service.create("1", "Treść", author, null);
        Reportage r0 = service.get(id0);
        r0.setSubject("2");
        Long id1 = service.update(r0);
        List<Reportage> reportages = service.getAll();
        assertEquals(2, reportages.size());
        Reportage r01 = service.get(id0);
        Reportage r11 = service.get(id1);
        assertEquals("1", r01.getSubject());
        assertEquals("Treść", r01.getContent());
        assertEquals("2", r11.getSubject());
        assertEquals("Treść", r11.getContent());
    }

    @Test
    public void testLatest() throws Exception {
        Long authorId = new PeopleService().createReporter("Jan", "Kowalski", TVStation.TV_1, "Bla");
        Reporter author = (Reporter)new PeopleService().get(authorId, Reporter.class);
        ReportageService service = new ReportageService();
        Long id0 = service.create("1", "000", author, null);
        Long id10 = service.create("Drugi", "001", author, null);
        Reportage r0 = service.get(id0);
        r0.setSubject("2");
        Long id1 = service.update(r0);
        List<Reportage> latest = service.latest();
        int x0 = 0;
        int x1 = 1;
        assertEquals(2, latest.size());
        if (latest.get(0).getSubject() == "Drugi"){
            x0 = 1;
            x1 = 0;
        }
        assertEquals(2, latest.get(x0).getVersion());
        assertEquals("2", latest.get(x0).getSubject());
        assertEquals(1, latest.get(x1).getVersion());
        assertEquals("Drugi", latest.get(x1).getSubject());
    }

    @Test
    public void testTop() throws Exception {
        ReportageService rs = new ReportageService();
        PeopleService ps = new PeopleService();
        TVProductionService ts = new TVProductionService();
        Long reporterId = ps.createReporter("A", "B", TVStation.TV_1, "spec");
        Reporter reporter = (Reporter)ps.get(reporterId, Reporter.class);
        Long r0id = rs.create("0", "txt", reporter, new HashSet<News>());
        Long r1id = rs.create("1", "txt", reporter, new HashSet<News>());
        Long r2id = rs.create("2", "txt", reporter, new HashSet<News>());
        Reportage r0 = rs.get(r0id);
        Reportage r1 = rs.get(r1id);
        Reportage r2 = rs.get(r1id);
        Long n0id = ts.createNews(0);
        Long n1id = ts.createNews(1);
        News n0 = ts.getNews(n0id);
        News n1 = ts.getNews(n1id);
        assertTrue(ts.addReportage(n0id, r0id));
        assertTrue(ts.addReportage(n1id, r2id));
        assertFalse(ts.addReportage(n0id, r0id));
        assertTrue(ts.addReportage(n1id, r1id));
        assertEquals(3, rs.getAll().size());
        assertEquals(2, ts.getAllNews().size());
        assertEquals(1, ps.getAll(Person.class).size());
        r0 = rs.get(r0id);
        n0 = ts.getNews(n0id);
        assertEquals(1, n0.getReportages().size());
        assertEquals(1, r0.getNews().size());
        assertEquals(0, new ArrayList<News>(r0.getNews()).get(0).getAudience());
        List<Reportage> topavg = rs.topAverage();
        assertEquals(2, topavg.size());
        List<Reportage> top = rs.top();
        assertEquals(2, top.size());

        Long r3id = rs.create("3", "txt", reporter, new HashSet<News>());
        assertTrue(ts.addReportage(n0id, r3id));
        assertTrue(ts.addReportage(n1id, r3id));
        topavg = rs.topAverage();
        assertEquals(2, topavg.size());
        top = rs.top();
        assertEquals(3, top.size());

        Long n2id = ts.createNews(1);
        Long r4id = rs.create("2", "txt", reporter, new HashSet<News>());
        assertTrue(ts.addReportage(n2id, r4id));
        topavg = rs.topAverage();
        assertEquals(3, topavg.size());
        top = rs.top();
        assertEquals(4, top.size());

        Long n3id = ts.createNews(2);
        assertTrue(ts.addReportage(n3id, r4id));
        topavg = rs.topAverage();
        assertEquals(1, topavg.size());
        top = rs.top();
        assertEquals(1, top.size());
    }
}
