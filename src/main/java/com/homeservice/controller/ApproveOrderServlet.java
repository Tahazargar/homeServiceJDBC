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

@WebServlet("/approveOrder")
public class ApproveOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        OrderDAO orderDAO = new OrderDAO();
        orderDAO.assignExpertToOrder(userId, orderId);

        response.sendRedirect("approveOrder");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDAO orderDAO = new OrderDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        int userId = user.getId();

        List<Order> orders = orderDAO.getExpertJobsById(userId);

        request.setAttribute("orders", orders);
        request.setAttribute("userId", userId);
        request.getRequestDispatcher("/WEB-INF/jsp/expertJobs.jsp").forward(request, response);
    }
}
