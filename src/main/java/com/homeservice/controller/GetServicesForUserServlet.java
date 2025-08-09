package com.homeservice.controller;

import com.homeservice.dao.UserServiceDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/getServicesForUser")
public class GetServicesForUserServlet extends HttpServlet {

    private UserServiceDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new UserServiceDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long userId = Long.parseLong(req.getParameter("userId"));
        List<Long> serviceIds = dao.getServiceIdsForUser(userId);

        resp.setContentType("application/json");
        resp.getWriter().write(serviceIds.toString());
    }
}
