package main.java.com.homeservice.model;

import java.time.OffsetDateTime;
import java.util.List;

public class Suggestion {
    private int id;
    private long price;
    private OffsetDateTime dueDate;
    private OffsetDateTime startDate;
    private Order order;
    private List<User> users;
    private List<Order> orders;

    public Suggestion(int id, long price, OffsetDateTime dueDate, OffsetDateTime startDate, Order order, List<User> users, List<Order> orders) {
        this.id = id;
        this.price = price;
        this.dueDate = dueDate;
        this.startDate = startDate;
        this.order = order;
        this.users = users;
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public OffsetDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(OffsetDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
