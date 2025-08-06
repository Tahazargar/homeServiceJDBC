package com.homeservice.controller;

import com.homeservice.dao.ServiceDAO;
import com.homeservice.model.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/services")
public class ServicesListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        ServiceDAO serviceDAO = new ServiceDAO();
        List<Service> services = serviceDAO.getAllServices();

        // Load Parent Names Of Each Service
        Map<Integer, String> idToName = services.stream().collect(Collectors.toMap(Service::getId, Service::getTitle));

        for( Service s : services ) {
            System.out.println("Service: " + s.getTitle() + ", Parent ID: " + s.getParentID() + ", Parent Title: " + s.getParentTitle());
            if(s.getParentID() != null && s.getParentID() != 0){
                s.setParentTitle(idToName.get(s.getParentID()));
            }
        }


        request.setAttribute("services", services);
        request.getRequestDispatcher("/WEB-INF/jsp/services.jsp").forward(request, response);
    }
}
