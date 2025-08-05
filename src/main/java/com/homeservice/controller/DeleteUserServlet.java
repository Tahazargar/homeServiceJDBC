package com.homeservice.controller;

import com.homeservice.dao.UserDAO;
import com.homeservice.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/delete-user")
public class DeleteUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        HttpSession session = request.getSession(false);

        if(idParam != null) {
            try{
                int id = Integer.parseInt(idParam);
                UserDAO userDAO = new UserDAO();
                boolean deleted = userDAO.deleteUserById(id);

                if(deleted) {
                    session.setAttribute("message", "User deleted successfully.");
                }else{
                    session.setAttribute("message", "User could not be deleted.");
                }
            }catch(NumberFormatException e) {
                session.setAttribute("message", "Invalid ID.");
            }
        }

        response.sendRedirect("users");
    }
}
