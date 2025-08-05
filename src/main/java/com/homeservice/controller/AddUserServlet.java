package com.homeservice.controller;

import com.homeservice.dao.UserDAO;
import com.homeservice.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add-user")
public class AddUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/addUser.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        short role = Short.parseShort(request.getParameter("role"));

        if(fullName.isEmpty() || email.isEmpty() || password.isEmpty()
        || fullName == null || email == null || password == null){
            request.setAttribute("error", "Please fill all the fields");
            request.getRequestDispatcher("/WEB-INF/jsp/addUser.jsp").forward(request, response);
        }

        String[] nameParts = fullName.trim().split(" ", 2);

        String name = nameParts.length > 0 ? nameParts[0] : "";
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        User user = new User();
        UserDAO userDAO = new UserDAO();

        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setLastName(lastName);
        user.setRole(role);

        boolean success = userDAO.createUser(user);

        if(success){
            request.setAttribute("success", "User added successfully");
            response.sendRedirect("/users");
        }else {
            request.setAttribute("error", "User could not be added");
            request.getRequestDispatcher("/WEB-INF/jsp/addUser.jsp").forward(request, response);
        }

    }
}
