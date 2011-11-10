package tv.daos;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 09.11.11
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */

import org.hibernate.Hibernate;
import org.junit.Before;
import org.junit.Test;
import services.PeopleService;
import services.ReportageService;
import services.TVProductionService;
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
import util.HibernateUtil;

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
    public void testCreate(){
        Long rid = rdao.create("X", "Y", TVStation.TV_1, "AA");
        Reporter r = (Reporter)rdao.get(rid);
        Long id = dao.create("Temat", "Treść", r, new HashSet<News>());
        commitAndCreateNewTransaction();
        assertEquals(1, dao.getAll().size());
    }

    @Test
    public void dupa() throws Exception {
        ReportageService rs = new ReportageService();
        PeopleService ps = new PeopleService();
        TVProductionService ts = new TVProductionService();
        Long reporterId = ps.createReporter("A", "B", TVStation.TV_1, "spec");
        Reporter reporter = (Reporter)ps.get(reporterId, Reporter.class);
        Long r0id = rs.create("0", "txt", reporter, new HashSet<News>());
        Long r1id = rs.create("1", "txt", reporter, new HashSet<News>());
        Reportage r0 = rs.get(r0id);
        Reportage r1 = rs.get(r1id);
        Long n0id = ts.createNews(0);
        Long n1id = ts.createNews(1);
        News n0 = ts.getNews(n0id);
        News n1 = ts.getNews(n1id);
        HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
        assertTrue(ndao.addReportage(n0, r0));
        assertFalse(ndao.addReportage(n0, r0));
        assertTrue(ndao.addReportage(n1, r1));
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
    }
}
