package tv.productions;

import java.util.Date;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 29.10.11
 * Time: 12:59
 * To change this template use File | Settings | File Templates.
 */
public class TVProduction {
    private Long id;
    private SortedSet<Date> airingDate;

    public TVProduction(){
        airingDate = new TreeSet<Date>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SortedSet<Date> getAiringDate() {
        return airingDate;
    }

    public void setAiringDate(Set<Date> airingDate) {
        this.airingDate = new TreeSet<Date>(airingDate);
    }
}
