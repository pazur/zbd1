package services;

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
}
