package tv.people;

import tv.TVStation;

public class TVWorker extends Person{
    private TVStation station;

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