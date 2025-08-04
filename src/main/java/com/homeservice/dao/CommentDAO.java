package com.homeservice.dao;

import com.homeservice.model.Comment;
import main.java.com.homeservice.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    public int addComment(Comment comment) {
        String sql = "insert into comments(message,star, status) values(?,?,?)";

        try(Connection connection = DatabaseUtil.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, comment.getMessage());
            ps.setInt(2, comment.getStar());
            ps.setInt(3, comment.getStatus());

            ps.executeUpdate();
            return comment.getId();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    public Comment getComment(int id) {
        String sql = "select * from comments where id = ?";

        try (Connection connection = DatabaseUtil.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return new Comment(
                    rs.getString("message"),
                        rs.getShort("star"),
                        rs.getShort("status")
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public List<Comment> getAllComments() {
        String sql = "select * from comments";
        List<Comment> comments = new ArrayList<>();

        try(Connection connection = DatabaseUtil.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                comments.add(
                        new Comment(
                                rs.getString("message"),
                                rs.getShort("star"),
                                rs.getShort("status")
                        )
                );
            }

            return comments;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return comments;
    }

    public void deleteComment(int id) {
        String sql = "delete from comments where id = ?";

        try (Connection connection = DatabaseUtil.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
