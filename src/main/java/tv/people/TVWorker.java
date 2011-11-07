package tv.people;

import tv.TVStation;
import tv.productions.TVProduction;

import java.util.Set;

public class TVWorker extends Person{
    private TVStation station;
    private Set<TVProduction> productions;

    public Set<TVProduction> getProductions() {
        return productions;
    }

    public void setProductions(Set<TVProduction> productions) {
        this.productions = productions;
    }

    public TVWorker(){
    }

    public TVWorker(String name, String surname, TVStation station){
        super(name, surname);
        this.station = station;
    }

    public TVStation getStation(){
        return this.station;
    }

    public void setStation(TVStation station) {
        this.station = station;
    }
}