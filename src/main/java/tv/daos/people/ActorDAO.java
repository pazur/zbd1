package tv.daos.people;

import org.hibernate.Query;
import tv.TVStation;
import tv.people.Actor;
import tv.people.Person;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 08.11.11
 * Time: 13:30
 * To change this template use File | Settings | File Templates.
 */
public class ActorDAO extends PersonDAO{
    public Long create(String name, String surname, TVStation station, short rating){
        return save(new Actor(name, surname, station, rating));
    }

    @Override
    public Query getQuery() {
        return getQuery("Actor");
    }
    protected Class getCls(){
        return Actor.class;
    }
}
