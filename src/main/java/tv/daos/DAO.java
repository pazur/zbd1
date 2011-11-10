package tv.daos;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.omg.PortableInterceptor.NON_EXISTENT;
import tv.people.Person;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.Collection;
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

    public void update(Object o){
        currentSession().update(o);
    }

    protected Query getQuery(){
        return null;
    }
    public List getByCriterions(List<Criterion> criterions){
        DetachedCriteria criteria = DetachedCriteria.forClass(getCls());
        for (Criterion c: criterions){
            criteria.add(c);
        }
        return criteria.getExecutableCriteria(currentSession()).list();
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
        return currentSession().get(getCls(), id);
    }
    protected Class getCls(){
        return null;
    }

    public List get(Collection<Long> ids){
        List<Criterion> crits = new ArrayList<Criterion>();
        crits.add(Restrictions.in("id", ids));
        return getByCriterions(crits);
    }
}
