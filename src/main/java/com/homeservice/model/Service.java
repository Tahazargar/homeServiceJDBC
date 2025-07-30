package main.java.com.homeservice.model;

import java.util.List;

public class Service {
    private int id;
    private String title;
    private long price;
    private short status;
    private String description;
    private List<Comment> comments;

    public Service(int id, String title, long price, short status, String description, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.status = status;
        this.description = description;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
