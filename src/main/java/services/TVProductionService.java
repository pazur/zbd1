package services;

import tv.daos.DAO;
import tv.daos.productions.NewsDAO;
import tv.daos.productions.ReportageDAO;
import tv.daos.productions.TVProductionDAO;
import tv.daos.productions.TVSeriesDAO;
import tv.productions.News;
import tv.productions.Reportage;
import tv.productions.TVProduction;
import tv.productions.TVSeries;

import java.util.HashSet;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 10.11.11
 * Time: 12:17
 * To change this template use File | Settings | File Templates.
 */
public class TVProductionService extends Service{
    private class Get implements Command{
        private Long id; DAO dao;
        public Get(Long id, DAO dao){this.id = id; this.dao = dao;}
        public Object run(){return dao.get(id);}
    }
    private TVProduction get(Long id, DAO dao){
        return (TVProduction)execute(new Get(id, dao));
    }

    private class GetAll implements Command{
        private DAO dao;
        public GetAll(DAO dao){this.dao=dao;}
        public List run(){return dao.getAll();}
    }
    private List getAll(DAO dao){
        return (List)execute(new GetAll(dao));
    }

    public List getAllNews(){
        return getAll(new NewsDAO());
    }
    public List getAllTVSeries(){
        return getAll(new TVSeriesDAO());
    }
    public News getNews(Long id){
        return (News)get(id, new NewsDAO());
    }
    public TVSeries getTVSeries(Long id){
        return (TVSeries)get(id, new TVProductionDAO());
    }

    private class AddReportage implements Command{
        private Long news; private Long reportage;
        public AddReportage(Long news, Long reportage){this.news=news;this.reportage=reportage;}
        public Boolean run(){return new NewsDAO().addReportage(news, reportage);}
    }
    public boolean addReportage(Long news, Long r){
        return (Boolean)execute(new AddReportage(news,r));
    }

    private class RemoveReportage implements Command{
        private News news; private Reportage reportage;
        public RemoveReportage(News news, Reportage reportage){this.news=news;this.reportage=reportage;}
        public Boolean run(){return new NewsDAO().removeReportage(news, reportage);}
    }
    public boolean removeReportage(News news, Reportage r){
        return (Boolean)execute(new RemoveReportage(news,r));
    }

    private class CreateNews implements Command{
        private int audience;
        public CreateNews(int audience){this.audience=audience;}
        public Long run(){return new NewsDAO().create(audience, new HashSet<Reportage>());}
    }
    public Long createNews(int audience){
        return (Long)execute(new CreateNews(audience));
    }
}
