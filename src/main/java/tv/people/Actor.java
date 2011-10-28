package tv.people;

import tv.TVStation;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 28.10.11
 * Time: 22:21
 * To change this template use File | Settings | File Templates.
 */
public class Actor extends TVWorker{
    private short rating;

    public Actor(){
    }

    public Actor(String name, String surname, TVStation station, short rating){
        super(name, surname, station);
        this.rating = rating;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }
}
