package tv.daos;

import tv.daos.people.ActorDAO;
import tv.daos.people.PersonDAO;
import tv.daos.people.ReporterDAO;
import tv.daos.people.TVWorkerDAO;
import tv.people.Actor;
import tv.people.Person;
import tv.people.Reporter;
import tv.people.TVWorker;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 09.11.11
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */
public class DAOUtil {
    public static DAO getPeopleDAO(Class cls){
        if (cls == Actor.class)
            return new ActorDAO();
        if (cls == Person.class)
            return new PersonDAO();
        if (cls == Reporter.class)
            return new ReporterDAO();
        if (cls == TVWorker.class)
            return new TVWorkerDAO();
        return null;
    }
}
