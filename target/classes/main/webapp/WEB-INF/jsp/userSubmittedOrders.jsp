<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
            <td>${userOrder.price}</td>
<%--            <td><fmt:formatDate value="${userOrder.dueDate}" pattern="yyyy-MM-dd"/></td>--%>
            <td>${userOrder.address}</td>
            <td>
                <c:choose>
                    <c:when test="${userOrder.status == 0}">Inactive</c:when>
                    <c:when test="${userOrder.status == 1}">Active</c:when>
                    <c:otherwise>Banned</c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="userSubmittedOrders?id=${userOrder.id}">Delete</a>
            </td>
        </tr>
        <% counter++; %>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
