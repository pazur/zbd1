package tv.productions;

import org.junit.Test;
import tv.SessionTest;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import static org.junit.Assert.*;
/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 29.10.11
 * Time: 13:14
 * To change this template use File | Settings | File Templates.
 */
public class TVProductionTest extends SessionTest{

    @Test
    public void testSchema(){
        newSessionFactoryAndTransaction();
        TVProduction production = new TVProduction();
        HashSet<Date> dates = new HashSet<Date>();
        dates.add(new Date(123));
        dates.add(new Date(321));
        production.setAiringDate(dates);
        save(production);
        commitAndCreateNewTransaction();
        List<TVProduction> productions = session.createQuery("from TVProduction").list();
        assertEquals(1, productions.size());
        assertEquals(dates.size(), productions.get(0).getAiringDate().size());
    }
}
