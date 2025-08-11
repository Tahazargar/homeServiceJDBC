package com.homeservice.controller;

import com.homeservice.dao.UserDAO;
import com.homeservice.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/credits")
public class AddCreditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.getAllUsers();

        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/jsp/addCredit.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        int credit = Integer.parseInt(req.getParameter("credit"));

        UserDAO userDAO = new UserDAO();
        userDAO.addCreditToUser(userId, credit);

        resp.sendRedirect("/credits");
    }
}
