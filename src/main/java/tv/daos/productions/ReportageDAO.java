package tv.daos.productions;

import org.hibernate.Query;
import tv.daos.DAO;
import tv.people.Reporter;
import tv.productions.News;
import tv.productions.Reportage;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 09.11.11
 * Time: 16:07
 * To change this template use File | Settings | File Templates.
 */
public class ReportageDAO extends DAO{
    protected Query getQuery(){
        Query result = getQuery("Reportage");
        result.setReadOnly(true);
        return result;
    }
    @Override
    protected Class getCls() {
        return Reportage.class;
    }

    public Long create(String subject, String content, Reporter author, Set<News> news,int version, Long reportageId){
        Reportage reportage = new Reportage();
        reportage.setSubject(subject);
        reportage.setContent(content);
        reportage.setAuthor(author);
        reportage.setNews(news);
        reportage.setVersion((short)version);
        reportage.setReportageId(reportageId);
        Long result = save(reportage);
        currentSession().setReadOnly(reportage, true);
        return result;
    }

    public void updateReportageId(Reportage r){
        if (r.getReportageId() == 0){
            r.setReportageId(r.getId());
            update(r);
        }
    }

    public Long create(String subject, String content, Reporter author, Set<News> news){
        return create(subject, content, author, news, (short)1, 0L);
    }

    public Long updateReportage(Reportage reportage){
        Long result = create(reportage.getSubject(), reportage.getContent(), reportage.getAuthor(), reportage.getNews(), reportage.getVersion() + 1, reportage.getReportageId());
        return result;
    }

    @Override
    public Long save(Object o) {
        Long result = super.save(o);
        currentSession().setReadOnly(o, true);
        return result;
    }

    @Override
    public Object get(Long id) {
        Object result = super.get(id);    //To change body of overridden methods use File | Settings | File Templates.
        currentSession().setReadOnly(result, true);
        return result;
    }
}
