package tv.people;

import tv.TVStation;

public class TVWorker extends Person{
    private TVStation station;

    public TVStation getStation(){
        return this.station;
    }

    public void setStation(TVStation station) {
        this.station = station;
    }
}