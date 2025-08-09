package com.homeservice.controller;

import com.homeservice.dao.UserServiceDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

//@WebServlet("/getUsersForService")
//public class GetUsersForServiceServlet extends HttpServlet {
//
//    private UserServiceDAO dao;
//
//    @Override
//    public void init() throws ServletException {
//        dao = new UserServiceDAO();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        long serviceId = Long.parseLong(req.getParameter("serviceId"));
//        List<Long> userIds = dao.getUserIdsForService(serviceId);
//
//        resp.setContentType("application/json");
//        resp.getWriter().write(userIds.toString());
//    }
//}
