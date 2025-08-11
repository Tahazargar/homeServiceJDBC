package com.homeservice.controller;

import com.homeservice.dao.OrderDAO;
import com.homeservice.dao.ServiceDAO;
import com.homeservice.dao.UserDAO;
import com.homeservice.model.Order;
import com.homeservice.model.Service;
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
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        int userId = currentUser.getId();
        int serviceId = Integer.parseInt(request.getParameter("serviceId"));
        long currentUserCredit = currentUser.getCredit();
        ServiceDAO serviceDAO = new ServiceDAO();
        Service service = serviceDAO.getServiceById(serviceId);

        if(service.getPrice() > price) {
            throw new RuntimeException("Propose price is smaller than the base price");
        }else if(currentUserCredit < price) {
            throw new RuntimeException("You have not enough credit");
        }

        long newCredit = currentUserCredit - price;
        UserDAO userDAO = new UserDAO();
        userDAO.updateCredit(userId, newCredit);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date dueDate  = null;

        try {
            dueDate = formatter.parse(dueDateStr);
        }catch (ParseException e){
            e.printStackTrace();
        }

        Order order = new Order();

        order.setDescription(description);
        order.setPrice(price);
        order.setDueDate(dueDate);
        order.setAddress(address);
        order.setId(userId);

        OrderDAO orderDAO = new OrderDAO();

        orderDAO.createOrder(order);
        request.setAttribute("order", order);
        request.getRequestDispatcher("WEB-INF/jsp/submitOrder.jsp").forward(request, response);
    }
}
