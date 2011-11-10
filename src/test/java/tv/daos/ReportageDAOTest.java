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
    public void testCreate(){
        Long rid = rdao.create("X", "Y", TVStation.TV_1, "AA");
        Reporter r = (Reporter)rdao.get(rid);
        Long id = dao.create("Temat", "Treść", r, new HashSet<News>());
        commitAndCreateNewTransaction();
        assertEquals(1, dao.getAll().size());
    }
}
