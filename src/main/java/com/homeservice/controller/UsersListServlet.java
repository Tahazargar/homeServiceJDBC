package com.homeservice.controller;

import com.homeservice.dao.UserDAO;
import com.homeservice.model.User;
import com.homeservice.util.CheckRoleUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("currentUser");

        if(currentUser == null || !CheckRoleUtil.isAdmin(currentUser.getRole())) {
            response.sendRedirect("login");
            return;
        }

        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.getAllUsers();

        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/jsp/users.jsp").forward(request, response);
    }
}
