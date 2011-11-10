package services;

import tv.daos.DAO;
import tv.daos.people.TVWorkerDAO;
import tv.daos.productions.*;
import tv.people.Actor;
import tv.people.TVWorker;
import tv.productions.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private class CreateTVSeries implements Command{
        private String title;
        public CreateTVSeries(String title){this.title=title;}
        public Long run(){return new TVSeriesDAO().create(title, new HashSet<Episode>());}
    }
    public Long createTVSeries(String title){
        return (Long)execute(new CreateTVSeries(title));
    }

    private class GetLongestByEpisode implements Command{
        public List run(){
            return currentSession().createQuery(" " +
                    "from TVSeries series " +
                    "where series.episodes.size >= (select max(ser.episodes.size) from TVSeries ser) "
            ).list();
        }
    }
    public List getLongestByEpisodes(){
        return (List)execute(new GetLongestByEpisode());
    }


    public List getLongestBySeasons(){
        return (List)execute(new GetLongestBySeasons());
    }

    private class GetLongestBySeasons implements Command {
        public List run(){
            return currentSession().createQuery(" " +
                    "select srs " +
                    "from TVSeries srs " +
                    "where (select count(distinct epis.season) from Episode epis where epis.series = srs) >= " +
                    "all(select count(distinct season) from Episode group by series)"
            ).list();
        }
    }
    private class CreateEpisode implements Command{
        private short season; private short number; private TVSeries series; private Set<Actor> actors;
        private CreateEpisode(short season, short number, TVSeries series, Set<Actor> actors) {
            this.season = season;this.number = number;this.series = series;this.actors = actors;
        }

        public Long run(){return new EpisodeDAO().create(series, season, number, actors);}
    }
    public Long createEpisode(short season, short number, TVSeries series, Set<Actor> actors){
        return (Long)execute(new CreateEpisode(season, number, series, actors));
    }

    private class AddAiringDate implements Command{
        private Long pId; private Date date;
        private AddAiringDate(Long pId, Date date) {this.pId = pId; this.date = date;}
        public Object run() {
            return new TVProductionDAO().addAiringDate(pId ,date);
        }
    }
    public boolean addAiringDate(Long productionId, Date date){
        return (Boolean) execute(new AddAiringDate(productionId, date));
    }
    private class RemoveAiringDate implements Command{
        private Long pId; private Date date;
        private RemoveAiringDate(Long pId, Date date) {this.pId = pId; this.date = date;}
        public Object run() {
            return new TVProductionDAO().removeAiringDate(pId, date);
        }
    }
    public boolean removeAiringDate(Long productionId, Date date){
        return (Boolean) execute(new RemoveAiringDate(productionId, date));
    }

    private class AddAiringDateSet implements Command{
        private Long pId; private Set<Date> dates;
        private AddAiringDateSet(Long pId, Set<Date> dates) {this.pId = pId; this.dates = dates;}
        public Object run() {
            TVProduction production = (TVProduction)new TVProductionDAO().get(pId);
            return new TVProductionDAO().addAiringDates(production, dates);
        }
    }
    public boolean RemoveAiringDates(Long productionId, Set<Date> date){
        return (Boolean) execute(new RemoveAiringDateSet(productionId, date));
    }

    private class RemoveAiringDateSet implements Command{
        private Long pId; private Set<Date> dates;
        private RemoveAiringDateSet(Long pId, Set<Date> dates) {this.pId = pId; this.dates = dates;}
        public Object run() {
            TVProduction production = (TVProduction)new TVProductionDAO().get(pId);
            return new TVProductionDAO().removeAiringDates(production, dates);
        }
    }
    public boolean addAiringDates(Long productionId, Set<Date> date){
        return (Boolean) execute(new AddAiringDateSet(productionId, date));
    }

    private class AddWorkerSet implements Command{
        private Long pId; private Set<Long> workers;
        private AddWorkerSet(Long pId, Set<Long> workers) {this.pId = pId; this.workers = workers;}
        public Object run() {
            List<TVWorker> w = new TVWorkerDAO().get(workers);
            TVProduction production = (TVProduction)new TVProductionDAO().get(pId);
            return new TVProductionDAO().addWorkers(production, new HashSet<TVWorker>(w));
        }
    }
    public boolean addWorkers(Long productionId, Set<Long> workers){
        return (Boolean) execute(new AddWorkerSet(productionId, workers));
    }

    private class Delete implements Command{
        private Object o;
        public Delete(Object o){this.o = o;}
        public Object run(){new DAO().delete(o); return null;}
    }
    public void delete(TVProduction production){
        execute(new Delete(production));
    }
    public void delete(Reportage r){
        execute(new Delete(r));
    }
    public void delete(Episode e){
        execute(new Delete(e));
    }
}

