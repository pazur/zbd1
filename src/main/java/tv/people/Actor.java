package tv.people;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 28.10.11
 * Time: 22:21
 * To change this template use File | Settings | File Templates.
 */
public class Actor extends TVWorker{
    private short rating;

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }
}
