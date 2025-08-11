package com.homeservice.controller;

import com.homeservice.dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/changeOrderStatus")
public class ChangeOrderStatus extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        short orderStatus = Short.parseShort(request.getParameter("orderStatus"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.changeOrderStatus(orderId, orderStatus);

        response.sendRedirect("approveOrder");
    }
}
