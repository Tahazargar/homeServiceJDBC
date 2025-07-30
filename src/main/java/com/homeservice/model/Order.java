package main.java.com.homeservice.model;

import java.time.OffsetDateTime;
import java.util.List;

public class Order {
    private int id;
    private String description;
    private long price;
    private OffsetDateTime date;
    private short status;
    private String address;
    private User user;
    private List<Suggestion> suggestions;

    public Order(int id, String description, long price, OffsetDateTime date, short status, String address, User user, List<Suggestion> suggestions) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.date = date;
        this.status = status;
        this.address = address;
        this.user = user;
        this.suggestions = suggestions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }
}
