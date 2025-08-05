package com.homeservice.controller;

import com.homeservice.dao.ServiceDAO;
import com.homeservice.dao.UserDAO;
import com.homeservice.model.Service;
import com.homeservice.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add-service")
public class AddServiceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/addService.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        int price = Integer.parseInt(request.getParameter("price"));
        String description = request.getParameter("description");
        short status = Short.parseShort(request.getParameter("status"));

        if(title.isEmpty()  || title == null){
            request.setAttribute("error", "Please fill all the fields");
            request.getRequestDispatcher("/WEB-INF/jsp/addService.jsp").forward(request, response);
        }

        Service service = new Service();
        ServiceDAO serviceDAO = new ServiceDAO();

        service.setTitle(title);
        service.setPrice(price);
        service.setDescription(description);
        service.setStatus(status);

        boolean success = serviceDAO.createService(service);

        if(success){
            request.setAttribute("success", "Service added successfully");
            response.sendRedirect("/services");
        }else {
            request.setAttribute("error", "Service could not be added");
            request.getRequestDispatcher("/WEB-INF/jsp/addService.jsp").forward(request, response);
        }

    }
}
