package tv.services;

import org.junit.Before;
import org.junit.Test;
import services.PeopleService;
import services.ReportageService;
import tv.TVStation;
import tv.people.Reporter;
import tv.productions.Reportage;
import util.HibernateUtil;

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
}
