package com.homeservice.model;

public class Comment {
    private int id;
    private String message;
    private short star;
    private short status;
    private Service service;
    private User user;

    public Service getService() {
        return service;
    }

    public Comment(String message, short star, short status) {
        this.message = message;
        this.star = star;
        this.status = status;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public short getStar() {
        return star;
    }

    public void setStar(short star) {
        this.star = star;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

