package tv.productions;

import org.junit.Test;
import tv.SessionTest;
import tv.people.Reporter;

import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.11.11
 * Time: 22:51
 * To change this template use File | Settings | File Templates.
 */
public class ReportageTest extends SessionTest{

    @Test
    public void testReporter(){
        newSessionFactoryAndTransaction();
        Reporter r = new Reporter();
        r.setSpeciality("Bleble");
        save(r);
        Reportage reportage = new Reportage();
        reportage.setAuthor(r);
        reportage.setSubject("Blablaliza");
        reportage.setContent("Blablablabla");
        reportage.setReportageId(new Long(1));
        reportage.setVersion((short)1);
        save(reportage);
        commitAndCreateNewTransaction();
        List<Reporter> reporters = session.createQuery("from Reporter ").list();
        assertEquals(1, reporters.size());
        assertEquals(1, reporters.get(0).getReportages().size());


    }
}
