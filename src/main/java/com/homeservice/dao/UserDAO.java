package main.java.com.homeservice.dao;

import main.java.com.homeservice.model.Comment;
import main.java.com.homeservice.model.Suggestion;
import main.java.com.homeservice.model.User;
import main.java.com.homeservice.util.DatabaseUtil;

import java.sql.*;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public void createUser(User user) {
        String sql = "INSERT INTO users (name, last_name, email, password, role, status) VALUES (?,?,?,?,?,?)";
        try(Connection connection = DatabaseUtil.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setShort(5, user.getRole());
            stmt.setShort(6, user.getStatus());

            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("last_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("image"),
                        rs.getLong("credit"),
                        rs.getShort("status"),
                        rs.getShort("role")
                );

                user.setComments(loadCommentsForUser(id));
                user.setSuggestion(loadSuggestionForUser(id));
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
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("last_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("image"),
                        rs.getLong("credit"),
                        rs.getShort("status"),
                        rs.getShort("role")
                ));

                return users;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void deleteUserById(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
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
                        rs.getInt("id"),
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
