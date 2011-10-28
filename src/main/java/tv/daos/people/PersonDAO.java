package tv.daos.people;

import org.hibernate.Query;
import tv.TVStation;
import tv.daos.DAO;
import tv.people.Actor;
import tv.people.Person;
import tv.people.Reporter;
import tv.people.TVWorker;

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

    public Query getQuery(String cls){
        return currentSession().createQuery(String.format("from %s", cls));

    }

    public Person createPerson(String name, String surname){
        Person person = new Person(name, surname);
        currentSession().save(person);
        return person;
    }

    public TVWorker createTVWorker(String name, String surname, TVStation station){
        TVWorker tvWorker = new TVWorker(name, surname, station);
        currentSession().save(tvWorker);
        return tvWorker;
    }

    public Actor createActor(String name, String surname, TVStation station, short rating){
        Actor actor = new Actor(name, surname, station, rating);
        currentSession().save(actor);
        return actor;
    }

    public Reporter createReporter(String name, String surname, TVStation station, String speciality){
        Reporter reporter = new Reporter(name, surname, station, speciality);
        currentSession().save(reporter);
        return reporter;
    }

    public void updateRating(Actor a, short ranting){
        a.setRating(ranting);
        currentSession().update(a);
    }

    public void updateSpeciality(Reporter r, String speciality){
        r.setSpeciality(speciality);
        currentSession().update(r);
    }

    public void deletePerson(Person p){
        currentSession().delete(p);
    }
}
