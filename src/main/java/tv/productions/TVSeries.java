package tv.productions;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 29.10.11
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
public class TVSeries extends TVProduction{
    private String title;
    private Set<Episode> episodes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Set<Episode> episodes) {
        this.episodes = episodes;
    }
}
