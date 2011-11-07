package tv.productions;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 29.10.11
 * Time: 13:28
 * To change this template use File | Settings | File Templates.
 */
public class News extends TVProduction{
    private int audience;
    private Set<Reportage> reportages;

    public Set<Reportage> getReportages() {
        return reportages;
    }

    public void setReportages(Set<Reportage> reportages) {
        this.reportages = reportages;
    }

    public int getAudience() {
        return audience;
    }

    public void setAudience(int audience) {
        this.audience = audience;
    }
}
