package com.homeservice.dao;

import com.homeservice.util.DatabaseUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceDAO {
    public boolean assignServiceToUser(long userId, long serviceId) {
        String sql = "INSERT INTO user_service (user_id, service_id, assigned_at) " +
                "VALUES (?, ?, now()) ON CONFLICT (user_id, service_id) DO NOTHING";

        try (Connection conn = DatabaseUtil.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, userId);
            ps.setLong(2, serviceId);

            int rows = ps.executeUpdate();
            return rows > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean unassignServiceFromUser(long userId, long serviceId) {
        String sql = "DELETE FROM user_service WHERE user_id = ? AND service_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, userId);
            ps.setLong(2, serviceId);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Long> getServiceIdsForUser(long userId) {
        String sql = "SELECT service_id FROM user_service WHERE user_id = ?";
        List<Long> result = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(rs.getLong("service_id"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void assignMultipleServicesToUser(long userId, List<Long> serviceIds) {
        String sql = "INSERT INTO user_service (user_id, service_id, assigned_at) " +
                "VALUES (?, ?, now()) ON CONFLICT (user_id, service_id) DO NOTHING";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false); // begin transaction

            for (Long sid : serviceIds) {
                ps.setLong(1, userId);
                ps.setLong(2, sid);
                ps.addBatch();
            }

            ps.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
