<%@ page import="com.homeservice.model.User" %>
<%@ page import="com.homeservice.model.Service" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<User> users = (List<User>) request.getAttribute("users"); %>
<% List<Service> services = (List<Service>) request.getAttribute("services"); %>
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


</body>
</html>
