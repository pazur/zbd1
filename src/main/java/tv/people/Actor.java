package tv.people;

import tv.TVStation;
import tv.productions.Episode;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 28.10.11
 * Time: 22:21
 * To change this template use File | Settings | File Templates.
 */
public class Actor extends TVWorker{
    private short rating;
    private Set<Episode> episodes;
    public Actor(){
    }

    public Actor(String name, String surname, TVStation station, short rating){
        super(name, surname, station);
        this.rating = rating;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }

    public Set<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Set<Episode> episodes) {
        this.episodes = episodes;
    }
}
