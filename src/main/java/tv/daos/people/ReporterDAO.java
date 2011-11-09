package tv.daos.people;

import org.hibernate.Query;
import tv.TVStation;
import tv.people.Actor;
import tv.people.Reporter;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 08.11.11
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 */
public class ReporterDAO extends PersonDAO{
    public Query getQuery(){
        return getQuery("Reporter");
    }
    public Long create(String name, String surname, TVStation station, String speciality){
        return save(new Reporter(name, surname, station, speciality));
    }

    protected Class getCls(){
        return Reporter.class;
    }
}
