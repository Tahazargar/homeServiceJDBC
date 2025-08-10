package com.homeservice.controller;

import com.homeservice.dao.ServiceDAO;
import com.homeservice.dao.UserDAO;
import com.homeservice.dao.UserServiceDAO;
import com.homeservice.model.Service;
import com.homeservice.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/assignService")
public class AssignServiceServlet extends HttpServlet {
    private UserServiceDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new UserServiceDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        ServiceDAO serviceDao = new ServiceDAO();

        List<User> users = userDao.getAllUsersByRole(1);

        for (User user : users) {
            List<Service> assingedServicecs = serviceDao.getServiceByUserID(user.getId());
            user.setServices(assingedServicecs);
        }

        List<Service> services = serviceDao.getAllServices();

        req.setAttribute("users", users);
        req.setAttribute("services", services);

        req.getRequestDispatcher("/WEB-INF/jsp/assignServiceToUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long userId = Long.parseLong(req.getParameter("userId"));
        long serviceId = Long.parseLong(req.getParameter("serviceId"));

        boolean assigned = dao.assignServiceToUser(userId, serviceId);

        resp.sendRedirect("/assignService");

    }
}
