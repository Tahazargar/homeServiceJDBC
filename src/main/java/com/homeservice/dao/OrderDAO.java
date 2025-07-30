package main.java.com.homeservice.dao;

import main.java.com.homeservice.model.Order;
import main.java.com.homeservice.util.DatabaseUtil;

import java.sql.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

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
}
