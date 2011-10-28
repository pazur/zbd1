package tv.people;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 27.10.11
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */
public class Person {
    private String name;
    private String surname;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
