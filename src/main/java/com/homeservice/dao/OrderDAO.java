package com.homeservice.dao;

import com.homeservice.model.Order;
import com.homeservice.util.DatabaseUtil;

import java.sql.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public void createUser(Order order) {
        String sql = "INSERT INTO orders (description, price, date, status, address) VALUES (?,?,?,?,?)";

        try(Connection conn = DatabaseUtil.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,order.getDescription());
            ps.setLong(2,order.getPrice());
            ps.setTimestamp(3, Timestamp.from(OffsetDateTime.now(ZoneOffset.UTC).toInstant()));
            ps.setShort(4, order.getStatus());
            ps.setString(5, order.getAddress());

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
                return new Order(
                    rs.getString("description"),
                    rs.getLong("price"),
                    rs.getTimestamp("due_date").toInstant().atOffset(ZoneOffset.UTC),
                    rs.getShort("status"),
                    rs.getString("address")
                );
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
                orders.add(new Order(
                        rs.getString("description"),
                        rs.getLong("price"),
                        rs.getTimestamp("due_date").toInstant().atOffset(ZoneOffset.UTC),
                        rs.getShort("status"),
                        rs.getString("address")
                ));

                return orders;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return orders;
    }

    public void deleteOrderById(int id) {
        String sql = "DELETE FROM orders WHERE id = ?";

        try(Connection conn = DatabaseUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
