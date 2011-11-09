package tv.daos;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 09.11.11
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */

import org.junit.Before;
import org.junit.Test;
import tv.SessionTest;
import tv.TVStation;
import tv.daos.people.ActorDAO;
import tv.daos.people.PersonDAO;
import tv.daos.people.ReporterDAO;
import tv.daos.people.TVWorkerDAO;
import tv.daos.productions.NewsDAO;
import tv.daos.productions.ReportageDAO;
import tv.people.Reporter;
import tv.productions.News;
import tv.productions.Reportage;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class ReportageDAOTest extends SessionTest{
    private ReportageDAO dao;
    private ReporterDAO rdao;
    private NewsDAO ndao;

    @Before
    public void setUp(){
        newSessionFactoryAndTransaction();
        dao = new ReportageDAO();
        rdao = new ReporterDAO();
        ndao = new NewsDAO();
    }

    @Test
    public void testUpdate(){
        Long rid = rdao.create("X", "Y", TVStation.TV_1, "AA");
        Reporter r = (Reporter)rdao.get(rid);
        Long nid = ndao.create(0, new HashSet<Reportage>());
        News n = (News)ndao.get(nid);
        Long id = dao.create("Temat", "Treść", r, n);
        commitAndCreateNewTransaction();
        assertEquals(1, dao.getAll().size());
        assertEquals(1, ((News)ndao.get(nid)).getReportages().size());
        Reportage newReportage = (Reportage)dao.get(id);
        newReportage.setSubject("Blablaliza");
        Long newId = dao.updateReportage(newReportage);
        assertNotSame(id, newId);
        commitAndCreateNewTransaction();
        assertEquals(2, dao.getAll().size());
        Reportage oldr = (Reportage)dao.get(id);
        Reportage newr = (Reportage)dao.get(newId);
        assertEquals(1, oldr.getVersion());
        assertEquals("Temat", oldr.getSubject());
        assertEquals("Blablaliza", newr.getSubject());
        assertEquals(2, newr.getVersion());
    }
}
