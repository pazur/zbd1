package tv.people;

import tv.TVStation;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 28.10.11
 * Time: 22:21
 * To change this template use File | Settings | File Templates.
 */
public class Reporter extends TVWorker{
    private String speciality;

    public Reporter(){
    }

    public Reporter(String name, String surname, TVStation station, String speciality){
        super(name, surname, station);
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
