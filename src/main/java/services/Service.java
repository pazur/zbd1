package services;

import org.hibernate.Session;
import util.HibernateUtil;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 09.11.11
 * Time: 21:58
 * To change this template use File | Settings | File Templates.
 */
public abstract class Service {
    protected void begin(){
        HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
    }
    protected void commit(){
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
    }
    protected void except(RuntimeException e){
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
        throw e;
    }
    protected Session currentSession(){
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    protected interface Command{
        public Object run();
    }

    protected Object execute(Command cmd){
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
}
