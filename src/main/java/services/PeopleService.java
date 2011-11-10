package services;

import org.hibernate.criterion.Criterion;
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
import util.HibernateUtil;

import javax.persistence.Id;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 09.11.11
 * Time: 22:10
 * To change this template use File | Settings | File Templates.
 */
public class PeopleService extends Service{


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
    public List getAll(Class cls) throws Exception {
        return (List)execute(new GetAll(getDao(cls)));
    }

    private class Get implements Command{
        private DAO dao; private Long id;
        public Get(Long id, DAO dao){this.id = id; this.dao = dao;}
        public Object run(){return dao.get(id);}
    }
    public Object get(Long id, Class cls) throws Exception {
        return execute(new Get(id, getDao(cls)));
    }

    private class Save implements Command{
        private Object o;
        public Save(Object o){this.o = o;}
        public Object run(){return new PersonDAO().save(o);}
    }
    public Long save(Person p){
        return (Long)execute(new Save(p));
    }

    private class Update implements Command{
        private Object o;
        public Update(Object o){this.o = o;}
        public Object run(){new PersonDAO().update(o); return null;}
    }
    public Long update(Person p){
        return (Long)execute(new Update(p));
    }

    private class GetByCriterions implements Command{
        private List<Criterion> criterions; private DAO dao;
        public GetByCriterions(List<Criterion>criterions, DAO dao){this.dao = dao; this.criterions = criterions;}
        public Object run(){return new PersonDAO().getByCriterions(criterions);}
    }
    public List getByCriterions(Class cls, List<Criterion> criterions) throws Exception {
        return (List)execute(new GetByCriterions(criterions, getDao(cls)));
    }
    private DAO getDao(Class cls) throws Exception {
        DAO dao = DAOUtil.getPeopleDAO(cls);
        if (dao == null)
            throw new Exception("Wrong class");
        return dao;
    }
}
