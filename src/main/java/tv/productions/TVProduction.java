package tv.productions;

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
