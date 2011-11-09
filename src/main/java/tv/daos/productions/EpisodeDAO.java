package tv.daos.productions;

import org.hibernate.Query;
import tv.daos.DAO;
import tv.people.Actor;
import tv.productions.Episode;
import tv.productions.TVSeries;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 09.11.11
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 */
public class EpisodeDAO extends DAO{
    protected Query getQuery(){
        return getQuery("Episode");
    }
    protected Class getCls(){
        return Episode.class;
    }
    public boolean addActor(Episode episode, Actor actor){
        return episode.getActors().add(actor);
    }
    public boolean removeActor(Episode episode, Actor actor){
        return episode.getActors().remove(actor);
    }
    public Long create(TVSeries series, short season, short number, Set<Actor> actors){
        Episode episode = new Episode();
        episode.setActors(actors);
        episode.setSeason(season);
        episode.setNumber(number);
        episode.setSeries(series);
        return save(episode);
    }
}
