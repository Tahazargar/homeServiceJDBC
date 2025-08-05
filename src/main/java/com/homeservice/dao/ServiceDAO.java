package com.homeservice.dao;

import com.homeservice.model.Service;

import java.sql.*;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {
    public boolean createService(Service service) {
        String sql = "INSERT INTO services (title, price, status, description) VALUES (?,?,?,?)";
        try(Connection connection = main.java.com.homeservice.util.DatabaseUtil.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, service.getTitle());
            stmt.setDouble(2, service.getPrice());
            stmt.setInt(3, service.getStatus());
            stmt.setString(4, service.getDescription());

            stmt.executeUpdate();

            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public List<Service> getAllServices() {
        String sql = "SELECT s.id, s.title, s.price, s.description, s.status, s.created_at,\n" +
                "       s.parent_id,\n" +
                "       p.title AS parent_title\n" +
                "FROM services s\n" +
                "LEFT JOIN services p ON s.parent_id = p.id\n";
        List<Service> services = new ArrayList<>();

        try(Connection connection = main.java.com.homeservice.util.DatabaseUtil.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Service service = new Service();
                service.setId(rs.getInt("id"));
                service.setTitle(rs.getString("title"));
                service.setPrice(rs.getLong("price"));
                service.setDescription(rs.getString("description"));
                service.setStatus(rs.getShort("status"));
                service.setCreatedAt(rs.getTimestamp("created_at").toInstant().atOffset(ZoneOffset.UTC));
                service.setParentTitle(rs.getString("parent_title"));

                services.add(service);
            }
            return services;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }

    public boolean deleteServiceById(int id) {
        String sql = "DELETE FROM services WHERE id = ?";

        try (Connection connection = main.java.com.homeservice.util.DatabaseUtil.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);
            stmt.executeUpdate();

            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }


}
