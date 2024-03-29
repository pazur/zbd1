package tv.daos.people;

import org.hibernate.Query;
import tv.TVStation;
import tv.daos.DAO;
import tv.people.Actor;
import tv.people.Person;
import tv.people.Reporter;
import tv.people.TVWorker;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 28.10.11
 * Time: 01:17
 * To change this template use File | Settings | File Templates.
 */
public class PersonDAO extends DAO{
    public Query getQuery(){
        return getQuery("Person");
    }

    public Long create(String name, String surname){
        Person person = new Person(name, surname);
        return save(person);
    }
    protected Class getCls(){
        return Person.class;
    }
}
