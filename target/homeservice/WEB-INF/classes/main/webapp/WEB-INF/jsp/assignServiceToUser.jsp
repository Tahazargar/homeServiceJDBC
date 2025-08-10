<%@ page import="com.homeservice.model.User" %>
<%@ page import="com.homeservice.model.Service" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<User> users = (List<User>) request.getAttribute("users"); %>
<% List<Service> services = (List<Service>) request.getAttribute("services"); %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Assign Service to User</title>
</head>
<body>

<h2>Assign Service to User</h2>

<form id="assignForm" method="POST" action="/assignService">
    <label for="userSelect">Select User:</label>
    <select id="userSelect" name="userId" required>
        <option value="">-- Choose a user --</option>
        <% for (User user : users) { %>
        <option value="<%= user.getId() %>"><%= user.getName() %></option>
        <% } %>
    </select>

    <br><br>

    <label for="serviceSelect">Select Service:</label>
    <select id="serviceSelect" name="serviceId" required>
        <option value="">-- Choose a service --</option>
        <% for (Service service : services) { %>
        <option value="<%= service.getId() %>"><%= service.getTitle() %></option>
        <% } %>
    </select>

    <br><br>

    <button type="submit">Assign Service</button>
</form>

<table border="1" cellpadding="10" cellspacing="0">
    <thead>
    <tr>
        <th>NO.</th>
        <th>Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Service</th>
        <th>Assigned at</th>
        <th>Unassign</th>
    </tr>
    </thead>
    <tbody>
    <% int counter = 1; %>
    <c:forEach var="user" items="${users}">
        <tr>
            <td><%= counter %></td>
            <td>${user.name}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>
                <c:forEach var="srv" items="${user.services}">
                    ${srv.title}<br/>
                </c:forEach>
            </td>

            <td>
                Update
            </td>
            <td>
                <c:forEach var="srv" items="${user.services}">
                    <form action="/unassignService" method="POST" style="margin-bottom:5px;">
                        <input type="hidden" name="userId" value="${user.id}">
                        <input type="hidden" name="serviceId" value="${srv.id}">
                        <button type="submit">Unassign ${srv.title}</button>
                    </form>
                </c:forEach>
            </td>
        </tr>
        <% counter++; %>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
