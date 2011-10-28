package tv.daos;

import org.hibernate.Session;
import util.HibernateUtil;

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
}
