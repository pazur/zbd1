package tv.daos.people;

import org.hibernate.Query;
import tv.TVStation;
import tv.people.Actor;
import tv.people.Person;
import tv.people.TVWorker;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 08.11.11
 * Time: 13:25
 * To change this template use File | Settings | File Templates.
 */
public class TVWorkerDAO extends PersonDAO{
    public Query getQuery(){
        return getQuery("TVWorker");
    }

    public Long create(String name, String surname, TVStation station){
        return save(new TVWorker(name, surname, station));
    }

    protected Class getCls(){
        return TVWorker.class;
    }
}
