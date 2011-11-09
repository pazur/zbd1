package tv.daos.productions;

import org.hibernate.Query;
import tv.people.Actor;
import tv.productions.News;
import tv.productions.Reportage;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 08.11.11
 * Time: 23:38
 * To change this template use File | Settings | File Templates.
 */
public class NewsDAO extends TVProductionDAO{
    public Query getQuery(){
        return getQuery("News");
    }
    protected Class getCls(){
        return News.class;
    }
    public Long create(int audience, Set<Reportage> reportages){
        News news = new News();
        news.setAudience(audience);
        news.setReportages(reportages);
        return save(news);
    }
    public boolean addReportage(News news, Reportage reportage){
        return news.getReportages().add(reportage);
    }
    public boolean removeReportage(News news, Reportage reportage){
        return news.getReportages().remove(reportage);
    }
}
