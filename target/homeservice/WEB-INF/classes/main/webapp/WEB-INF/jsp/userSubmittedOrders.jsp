<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <title>My Orders</title>
</head>
<body>
<h2>My orders</h2>

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
        <th>Status</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <% int counter = 1; %>
    <c:forEach var="userOrder" items="${userOrders}">
        <tr>
            <td><%= counter %></td>
            <td>#${userOrder.id}</td>
            <td>${userOrder.description}</td>
            <td>$${userOrder.price}</td>
            <td><fmt:formatDate value="${userOrder.dueDate}" pattern="yyyy-MM-dd"/></td>
            <td>${userOrder.address}</td>
            <td>
                <c:choose>
                    <c:when test="${userOrder.status == 0}">Wating for experts</c:when>
                    <c:when test="${userOrder.status == 1}">Approved</c:when>
                    <c:when test="${userOrder.status == 2}">Started</c:when>
                    <c:when test="${userOrder.status == 3}">Completed</c:when>
                    <c:otherwise>Not defined</c:otherwise>
                </c:choose>
            </td>
            <td>
                <form action="userSubmittedOrders" method="POST">
                    <input name="orderId" value="${userOrder.id}" type="hidden">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
        <% counter++; %>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
