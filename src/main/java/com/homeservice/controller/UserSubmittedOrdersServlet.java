package com.homeservice.controller;

import com.homeservice.dao.OrderDAO;
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

@WebServlet("/userSubmittedOrders")
public class UserSubmittedOrdersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDAO orderDAO = new OrderDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        int userId = user.getId();

        List<Order> userOrders = orderDAO.getUserSubmittedOrders(userId);

        request.setAttribute("userOrders", userOrders);
        request.getRequestDispatcher("/WEB-INF/jsp/userSubmittedOrders.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userOrderId = Integer.parseInt(request.getParameter("id"));
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.deleteOrderById(userOrderId);

        response.sendRedirect("userSubmittedOrders");
    }
}
