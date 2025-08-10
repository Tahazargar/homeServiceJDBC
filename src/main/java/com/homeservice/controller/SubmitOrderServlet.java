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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;

@WebServlet("/submitOrder")
public class SubmitOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/submitOrder.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        long price = Long.parseLong(request.getParameter("price"));
        String address = request.getParameter("address");
        String dueDateStr = request.getParameter("dueDate");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date dueDate  = null;

        try {
            dueDate = formatter.parse(dueDateStr);
        }catch (ParseException e){
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        int userId = currentUser.getId();

        Order order = new Order();

        System.out.println("dueDate is" + dueDate);

        order.setDescription(description);
        order.setPrice(price);
        order.setDate(dueDate);
        order.setAddress(address);
        order.setId(userId);

        OrderDAO orderDAO = new OrderDAO();

        orderDAO.createOrder(order);
        request.setAttribute("order", order);
        request.getRequestDispatcher("WEB-INF/jsp/submitOrder.jsp").forward(request, response);
    }
}
