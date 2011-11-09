package tv.daos;

import org.hibernate.Query;
import org.hibernate.Session;
import org.omg.PortableInterceptor.NON_EXISTENT;
import tv.people.Person;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 28.10.11
 * Time: 23:41
 * To change this template use File | Settings | File Templates.
 */
public class DAO {
    protected Session currentSession(){
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public Long save(Object o){
        return (Long) currentSession().save(o);
    }

    protected Query getQuery(){
        return null;
    }

    protected Query getQuery(String cls){
        return currentSession().createQuery(String.format("from %s", cls));
    }

    public List getAll(){
        return this.getQuery().list();
    }
    public void delete(Object o){
        currentSession().delete(o);
    }
    public void delete(Long id){
        delete(get(id));
    }
    public Object get(Long id){
        return currentSession().load(getCls(), id);
    }
    protected Class getCls(){
        return null;
    }
}
