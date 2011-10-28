package tv;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

public abstract class SessionTest
{
  protected Session session;

  protected void newSessionFactoryAndTransaction()
  {
    this.session = HibernateUtil.getNewSessionFactory().getCurrentSession();
    this.session.beginTransaction();
  }
  protected void commitAndCreateNewTransaction() {
    this.session.getTransaction().commit();
    this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    this.session.beginTransaction();
  }
  protected void commit() {
    this.session.getTransaction().commit();
  }
  protected void save(Object a) {
    this.session.save(a);
  }
}