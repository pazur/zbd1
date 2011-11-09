package tv.daos.productions;

import org.hibernate.Query;
import tv.people.Actor;
import tv.productions.Episode;
import tv.productions.News;
import tv.productions.Reportage;
import tv.productions.TVSeries;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 09.11.11
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */
public class TVSeriesDAO extends TVProductionDAO{
    public Query getQuery(){
        return getQuery("TVSeries");
    }
    protected Class getCls(){
        return TVSeries.class;
    }

    public Long create(String title, Set<Episode> episodes){
        TVSeries series = new TVSeries();
        series.setTitle(title);
        series.setEpisodes(episodes);
        return save(series);
    }

    public boolean addEpisode(TVSeries series, Episode episode){
        return series.getEpisodes().add(episode);
    }
    public boolean removeEpisode(TVSeries series, Episode episode){
        return series.getEpisodes().remove(episode);
    }
}
