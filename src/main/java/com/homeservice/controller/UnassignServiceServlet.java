package com.homeservice.controller;

import com.homeservice.dao.UserServiceDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/unassignService")
public class UnassignServiceServlet extends HttpServlet {

    private UserServiceDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new UserServiceDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long userId = Long.parseLong(req.getParameter("userId"));
        long serviceId = Long.parseLong(req.getParameter("serviceId"));

        boolean unassigned = dao.unassignServiceFromUser(userId, serviceId);

        resp.setContentType("application/json");
        resp.getWriter().write("{\"unassigned\": " + unassigned + "}");
    }
}
