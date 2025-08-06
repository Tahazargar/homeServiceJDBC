package com.homeservice.dao;

import com.homeservice.model.Comment;
import com.homeservice.model.Suggestion;
import com.homeservice.model.User;
import com.homeservice.util.DatabaseUtil;
import com.homeservice.util.PasswordUtil;

import java.sql.*;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public boolean createUser(User user) {
        String sql = "INSERT INTO users (name, last_name, email, password, role, status) VALUES (?,?,?,?,?,?)";
        try(Connection connection = DatabaseUtil.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, PasswordUtil.hashPassword(user.getPassword()));
            stmt.setShort(5, user.getRole());
            stmt.setShort(6, user.getStatus());

            stmt.executeUpdate();

            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

                user.setComments(loadCommentsForUser(id));
                user.setSuggestion(loadSuggestionForUser(id));

                return user;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try(Connection connection = DatabaseUtil.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getShort("role"));
                user.setStatus(rs.getShort("status"));
                user.setPassword(rs.getString("password"));

                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public boolean deleteUserById(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

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

    public User getUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try(Connection connection = DatabaseUtil.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);

            String hashedPassword = PasswordUtil.hashPassword(password);

            stmt.setString(1, email);
            stmt.setString(2, hashedPassword);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getShort("role"));
                user.setStatus(rs.getShort("status"));
                user.setPassword(hashedPassword);

                return user;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    private Suggestion loadSuggestionForUser(int userId) {
        String sql = "SELECT * FROM suggestions WHERE user_id = ?";

        try (Connection connection = DatabaseUtil.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Suggestion suggestion = new Suggestion(
                        rs.getInt("id"),
                        rs.getLong("price"),
                        rs.getTimestamp("dueDate").toInstant().atOffset(ZoneOffset.UTC),
                        rs.getTimestamp("startDate").toInstant().atOffset(ZoneOffset.UTC)
                );

                return suggestion;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    private List<Comment> loadCommentsForUser(int userId){
        String sql = "SELECT * FROM comments WHERE user_id = ?";
        List<Comment> comments = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Comment comment = new Comment(
                        rs.getString("message"),
                        rs.getShort("star"),
                        rs.getShort("status")
                );

                comments.add(comment);
                return comments;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return comments;
    }
}
