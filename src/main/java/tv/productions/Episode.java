package tv.productions;

import tv.people.Actor;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 29.10.11
 * Time: 13:32
 * To change this template use File | Settings | File Templates.
 */
public class Episode {
    private Long id;
    private short season;
    private short number;
    private TVSeries series;
    private Set<Actor> actors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getSeason() {
        return season;
    }

    public void setSeason(short season) {
        this.season = season;
    }

    public short getNumber() {
        return number;
    }

    public void setNumber(short number) {
        this.number = number;
    }

    public TVSeries getSeries() {
        return series;
    }

    public void setSeries(TVSeries series) {
        this.series = series;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }
}
