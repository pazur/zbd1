package tv;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 27.10.11
 * Time: 11:43
 * To change this template use File | Settings | File Templates.
 */
import org.junit.Test;
import tv.people.Person;

import java.util.List;

import static org.junit.Assert.*;

public class DBTest extends SessionTest{
    private Person createPerson(){
        return createPerson("Jan", "Kowalski", true);
    }

    private Person createPerson(String name, String surname){
        return createPerson(name, surname, true);
    }

    private Person createPerson(String name, String surname, Boolean save){
        Person result = new Person();
        result.setName(name);
        result.setSurname(surname);
        if (save){
            save(result);
        }
        return result;
    }

    @Test
    public void testAdding(){
        newSessionFactoryAndTransaction();
        createPerson();
        commit();
    }

    @Test
    public void testGetting(){
        newSessionFactoryAndTransaction();
        Person p = createPerson();
        commitAndCreateNewTransaction();
        List result = session.createQuery("from Person").list();
        commit();
        assertEquals(1, result.size());
        assertEquals(p.getId(), ((Person)result.get(0)).getId());
    }
}
