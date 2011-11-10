package services;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.loader.custom.Return;
import tv.daos.DAO;
import tv.daos.productions.ReportageDAO;
import tv.people.Reporter;
import tv.productions.News;
import tv.productions.Reportage;
import util.HibernateUtil;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 10.11.11
 * Time: 00:39
 * To change this template use File | Settings | File Templates.
 */
public class ReportageService extends Service{

    private class Create implements Command{
        private String subject; private String content; private Reporter author; private Set<News> news;
        public Create(String subject, String content, Reporter author, Set<News> news){
            this.subject=subject;this.content=content;this.author=author;this.news=news;
        }
        public Object run(){
            return new ReportageDAO().create(subject, content, author, news);
        }
    }
    private class UpdateId implements Command{
        private Reportage r;
        public UpdateId(Reportage r){this.r = r;}
        public Object run(){
            new ReportageDAO().updateReportageId(r);
            return null;
        }
    }
    public Long create(String subject, String content, Reporter author, Set<News> news){
        Long result = (Long)execute(new Create(subject, content, author, news));
        execute(new UpdateId(get(result)));
        return result;
    }

    private class Update implements Command{
        private Reportage r;
        public Update(Reportage r){this.r = r;}
        public Object run(){
            return new ReportageDAO().updateReportage(r);
        }
    }
    public Long update(Reportage r){
        return (Long)execute(new Update(r));
    }

    private class Get implements Command{
        private Long id;
        public Get(Long id){this.id = id;}
        public Object run(){return new ReportageDAO().get(id);}
    }
    public Reportage get(Long id){
        return (Reportage)execute(new Get(id));
    }

    private class GetAll implements Command{
        public List run(){return new ReportageDAO().getAll();}
    }
    public List getAll(){
        return (List)execute(new GetAll());
    }

    private class Latest implements Command{
        public Object run(){
            return currentSession().createQuery(" " +
                    "from Reportage as r " +
                    "where version >= " +
                    "(select max(version) from Reportage as maxs where maxs.reportageId=r.reportageId)"
            ).list();
        }
    }
    public List latest(){
        return (List)execute(new Latest());
    }

    private class TopAverage implements Command{
        public Object run(){
            return currentSession().createQuery(" " +
                    "from Reportage re " +
                    "where (select avg(news.audience) from News as news where re in elements(news.reportages)) >= " +
                    "all(select avg(news.audience) " +
                    "from Reportage as r " +
                    "join r.news as news " +
                    "group by r)"
            ).list();
        }
    }
    public List topAverage(){
        return (List)execute(new TopAverage());
    }

    private class Top implements Command{
        public Object run(){
            return currentSession().createQuery(" " +
                    "from Reportage re " +
                    "join re.news as nws " +
                    "where  nws.audience >= (select max(audience) from News)"
            ).list();
        }
    }
    public List top(){
        return (List)execute(new Top());
    }
}