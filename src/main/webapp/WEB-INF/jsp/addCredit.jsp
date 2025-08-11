<%@ page import="com.homeservice.model.User" %>
<%@ page import="com.homeservice.model.Service" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Add credit</title>
</head>
<body>

<h2>Add credit</h2>

<form id="credit" method="POST" action="/credits">
    <label for="user">Select User:</label>
    <select id="user" name="userId" required>
        <option value="">-- Choose a user --</option>
        <c:forEach var="user" items="${users}">
            <option value="${user.id}">${user.name}</option>
        </c:forEach>
    </select>

    <br><br>

    <label for="credit" id="credit">Credit</label>
    <input name="credit" type="number">

    <br><br>

    <button type="submit">Add credit</button>
</form>

</body>
</html>
