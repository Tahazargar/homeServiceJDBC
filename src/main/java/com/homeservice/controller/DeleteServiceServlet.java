package com.homeservice.controller;

import com.homeservice.dao.ServiceDAO;
import com.homeservice.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/delete-service")
public class DeleteServiceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        HttpSession session = request.getSession(false);

        if(idParam != null) {
            try{
                int id = Integer.parseInt(idParam);
                ServiceDAO serviceDAO = new ServiceDAO();
                boolean deleted = serviceDAO.deleteServiceById(id);

                if(deleted) {
                    session.setAttribute("message", "Service deleted successfully.");
                }else{
                    session.setAttribute("message", "Service could not be deleted.");
                }
            }catch(NumberFormatException e) {
                session.setAttribute("message", "Invalid ID.");
            }
        }

        response.sendRedirect("services");
    }
}