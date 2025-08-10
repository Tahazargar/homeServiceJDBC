package com.homeservice.dao;

import com.homeservice.model.Service;
import com.homeservice.util.DatabaseUtil;

import java.sql.*;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {
    public boolean createService(Service service) {
        String sql = "INSERT INTO services (title, price, status, description, parent_id) VALUES (?,?,?,?,?)";
        try(Connection connection = DatabaseUtil.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, service.getTitle());
            stmt.setDouble(2, service.getPrice());
            stmt.setInt(3, service.getStatus());
            stmt.setString(4, service.getDescription());
            stmt.setObject(5, service.getParentID(), Types.INTEGER);


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

        try(Connection connection = DatabaseUtil.getConnection()) {
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
                service.setParentID(rs.getObject("parent_id", Integer.class));
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

        try (Connection connection = DatabaseUtil.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);
            stmt.executeUpdate();

            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public List<Service> getRootServices() {
        String sql = "SELECT id, title FROM services WHERE parent_id IS null";
        List<Service> services = new ArrayList<>();

        try(Connection connection = DatabaseUtil.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Service service = new Service();

                service.setId(rs.getInt("id"));
                service.setTitle(rs.getString("title"));
                services.add(service);
            }
            return services;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return services;
    }

    public List<Service> getServiceByUserID(int userID) {
        List<Service> services = new ArrayList<>();

        String sql = "SELECT s.id, s.title, s.description " + // add more columns if needed
                "FROM services s " +
                "INNER JOIN user_service us ON s.id = us.service_id " +
                "WHERE us.user_id = ?";

        try(Connection conn = DatabaseUtil.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Service service = new Service();
                service.setId(rs.getInt("id"));
                service.setTitle(rs.getString("title"));
                service.setDescription(rs.getString("description"));
                services.add(service);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return services;
    }
}
