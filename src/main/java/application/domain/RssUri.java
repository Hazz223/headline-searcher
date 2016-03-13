package application.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by Harry on 13/03/2016.
 */
public class RssUri {

    @Id
    private String id;
    private String title;
    private String uri;
    private Date insertDate;

    public RssUri(String title, String uri, Date insertDate) {
        this.title = title;
        this.uri = uri;
        this.insertDate = insertDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }
}
