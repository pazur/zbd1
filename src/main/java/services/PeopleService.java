package services;

import tv.TVStation;
import tv.daos.DAO;
import tv.daos.DAOUtil;
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
 * Time: 22:10
 * To change this template use File | Settings | File Templates.
 */
public class PeopleService extends Service{
    private interface Command{
        public Object run();
    }

    private Object execute(Command cmd){
        try{
            begin();
            Object result = cmd.run();
            commit();
            return result;
        } catch (RuntimeException e){
            except(e);
        }
        return null;
    }

    private class CreateActor implements Command{
        String name; String surname; TVStation station; short rating;
        public CreateActor(String name, String surname, TVStation station, short rating){
            this.name = name; this.surname = surname; this.station = station; this.rating = rating;
        }
        public Long run(){
            return new ActorDAO().create(name, surname, station, rating);
        }
    }
    public Long createActor(String name, String surname, TVStation station, short rating){
        return (Long)execute(new CreateActor(name, surname, station, rating));
    }

    private class CreatePerson implements Command{
        String name; String surname;
        public CreatePerson(String name, String surname){
            this.name = name; this.surname = surname;
        }
        public Long run(){
            return new PersonDAO().create(name, surname);
        }
    }
    public Long createPerson(String name, String surname){
        return (Long)execute(new CreatePerson(name, surname));
    }

    private class CreateReporter implements Command{
        String name; String surname; TVStation station; String speciality;
        public CreateReporter(String name, String surname, TVStation station, String speciality){
            this.name = name; this.surname = surname; this.station = station; this.speciality = speciality;
        }
        public Long run(){
            return new ReporterDAO().create(name, surname, station, speciality);
        }
    }
    public Long createReporter(String name, String surname, TVStation station, String speciality){
        return (Long)execute(new CreateReporter(name, surname, station, speciality));
    }

    private class CreateTVWorker implements Command{
        String name; String surname; TVStation station;
        public CreateTVWorker(String name, String surname, TVStation station){
            this.name = name; this.surname = surname; this.station = station;
        }
        public Long run(){
            return new TVWorkerDAO().create(name, surname, station);
        }
    }
    public Long createTVWorker(String name, String surname, TVStation station){
        return (Long)execute(new CreateTVWorker(name, surname, station));
    }

    private class GetAll implements Command{
        private DAO dao;
        public GetAll(DAO dao){this.dao = dao;}
        public List run(){return dao.getAll();}
    }
    private List getAll(Class cls) throws Exception {
        DAO dao = DAOUtil.getPeopleDAO(cls);
        if (dao == null)
            throw new Exception("Wrong class");
        return (List)execute(new GetAll(dao));
    }

    private class Get implements Command{
        private DAO dao; private Long id;
        public Get(Long id, DAO dao){this.id = id; this.dao = dao;}
        public Object run(){return dao.get(id);}
    }
    public Object get(Long id, Class cls) throws Exception {
        DAO dao = DAOUtil.getPeopleDAO(cls);
        if (dao == null)
            throw new Exception("Wrong class");
        return execute(new Get(id, dao));
    }
}
