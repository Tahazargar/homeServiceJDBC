package com.homeservice.controller;

import com.homeservice.dao.OrderDAO;
import com.homeservice.dao.UserDAO;
import com.homeservice.model.Order;
import com.homeservice.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/requests")
public class RequestsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDAO orderDAO = new OrderDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        int userId = user.getId();

        UserDAO userDAO = new UserDAO();
        long price = orderDAO.getPriceById(userId);
        long finalPrice = price + user.getCredit();
        userDAO.updateCredit(userId, finalPrice);


        List<Order> orders = orderDAO.getAllOrders();

        request.setAttribute("orders", orders);
        request.setAttribute("userId", userId);
        request.getRequestDispatcher("/WEB-INF/jsp/requests.jsp").forward(request, response);
    }
}
