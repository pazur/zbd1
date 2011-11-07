package tv.productions;

import tv.people.TVWorker;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 29.10.11
 * Time: 12:59
 * To change this template use File | Settings | File Templates.
 */
public abstract class TVProduction {
    private Long id;
    private HashSet<Date> airingDate;
    private Set<TVWorker> people;

    public Set<TVWorker> getPeople() {
        return people;
    }

    public void setPeople(Set<TVWorker> people) {
        this.people = people;
    }

    public TVProduction(){
        airingDate = new HashSet<Date>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Date> getAiringDate() {
        return airingDate;
    }

    public void setAiringDate(Set<Date> airingDate) {
        this.airingDate = new HashSet<Date>(airingDate);
    }
}
