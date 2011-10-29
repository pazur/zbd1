package tv.productions;

import tv.people.Reporter;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 29.10.11
 * Time: 13:32
 * To change this template use File | Settings | File Templates.
 */
public class Reportage {
    private Long id;
    private String subject;
    private short version;
    private String content;
    private Reporter author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public short getVersion() {
        return version;
    }

    public void setVersion(short version) {
        this.version = version;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Reporter getAuthor() {
        return author;
    }

    public void setAuthor(Reporter author) {
        this.author = author;
    }
}
