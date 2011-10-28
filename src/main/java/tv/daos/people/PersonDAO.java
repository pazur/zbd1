package tv.daos.people;

import org.hibernate.Query;
import tv.daos.DAO;
import tv.people.Person;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 28.10.11
 * Time: 01:17
 * To change this template use File | Settings | File Templates.
 */
public class PersonDAO extends DAO{
    public Query getQuery(){
        return currentSession().createQuery("from Person");
    }
    public Person createPerson(String name, String surname){
        Person person = new Person();
        person.setName(name);
        person.setSurname(surname);
        currentSession().save(person);
        return person;
    }
}
