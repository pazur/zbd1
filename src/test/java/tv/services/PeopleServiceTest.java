package tv.services;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 09.11.11
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 */

import org.junit.Test;
import services.PeopleService;
import services.Service;
import tv.TVStation;

import static org.junit.Assert.*;

public class PeopleServiceTest {

    @Test
    public void testService(){
        PeopleService service = new PeopleService();
        service.createActor("Jan", "Kowalski", TVStation.TV_7, (short)2);
    }
}
