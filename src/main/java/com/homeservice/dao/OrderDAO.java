package com.homeservice.dao;

import com.homeservice.model.Order;
import com.homeservice.util.DatabaseUtil;

import java.sql.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public void createOrder(Order order) {
        String sql = "INSERT INTO orders (description, price, due_date, address, user_id) VALUES (?,?,?,?,?)";

        try(Connection conn = DatabaseUtil.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,order.getDescription());
            ps.setLong(2,order.getPrice());
            ps.setDate(3, (Date) order.getDueDate());
            ps.setString(4, order.getAddress());
            ps.setInt(5, order.getId());

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Order getOrderById(int id) {
        String sql = "SELECT * FROM orders WHERE id = ?";

        try(Connection conn = DatabaseUtil.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setDescription(rs.getString("description"));
                order.setPrice(rs.getLong("price"));
                order.setStatus(rs.getShort("status"));
                order.setAddress(rs.getString("address"));
                order.setDueDate(rs.getTimestamp("due_date"));

                return order;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM orders";
        List<Order> orders = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                Order order = new Order();

                order.setId(rs.getInt("id"));
                order.setDescription(rs.getString("description"));
                order.setPrice(rs.getLong("price"));
                order.setStatus(rs.getShort("status"));
                order.setAddress(rs.getString("address"));
                order.setDueDate(rs.getTimestamp("due_date"));

                orders.add(order);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return orders;
    }

    public void deleteOrderById(int id) {
        String sql = "DELETE FROM orders WHERE id = ?";

        try(Connection conn = DatabaseUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Order> getUserSubmittedOrders(int userId) {
        String sql = "SELECT * FROM orders WHERE user_id = ?";
        List<Order> orders = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setDescription(rs.getString("description"));
                order.setPrice(rs.getLong("price"));
                order.setStatus(rs.getShort("status"));
                order.setAddress(rs.getString("address"));
                order.setDueDate(rs.getTimestamp("due_date"));
                orders.add(order);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> getExpertJobsById(int expertId) {
        String sql = "SELECT * FROM orders WHERE expert_id = ?";
        List<Order> orders = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,expertId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setDescription(rs.getString("description"));
                order.setPrice(rs.getLong("price"));
                order.setStatus(rs.getShort("status"));
                order.setAddress(rs.getString("address"));
                order.setDueDate(rs.getTimestamp("due_date"));
                order.setStatus(rs.getShort("status"));
                orders.add(order);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return orders;
    }

    public void assignExpertToOrder(int userId, int orderId) {
        String sql = "UPDATE orders SET expert_id = ? WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,userId);
            ps.setInt(2,orderId);

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void cancelExpertJob(int orderId) {
        String sql = "UPDATE orders SET expert_id = null WHERE id = ?";

        try(Connection conn = DatabaseUtil.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,orderId);

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void changeOrderStatus(int orderId, int status) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";

        try(Connection conn = DatabaseUtil.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,status);
            ps.setInt(2,orderId);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int getPriceById(int id) {
        String sql = "SELECT price FROM orders WHERE id = ?";
        int price = 0;

        try(Connection conn = DatabaseUtil.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                price = rs.getInt("price");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return price;
    }
}
