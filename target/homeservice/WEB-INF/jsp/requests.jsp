<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Requests</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
<h2>Requests</h2>

<div class="buttons" style="margin-bottom: 15px;">
    <a href="/">
        <button>Back To Dashboard</button>
    </a>
</div>

<table border="1" cellpadding="10" cellspacing="0">
    <thead>
    <tr>
        <th>NO.</th>
        <th>Order Id</th>
        <th>Description</th>
        <th>Proposed price</th>
        <th>Due date</th>
        <th>Address</th>
        <th>Approve</th>
    </tr>
    </thead>
    <tbody>
    <% int counter = 1; %>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td><%= counter %></td>
            <td>#${order.id}</td>
            <td>${order.description}</td>
            <td>${order.price}$</td>
            <td><fmt:formatDate value="${order.dueDate}" pattern="yyyy-MM-dd"/></td>
            <td>${order.address}</td>
            <td>
                <form action="approveOrder" method="POST">
                    <input name="userId" value="${userId}" type="hidden">
                    <input name="orderId" value="${order.id}" type="hidden">
                    <button type="submit">Approve</button>
                </form>
            </td>
        </tr>
        <% counter++; %>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
