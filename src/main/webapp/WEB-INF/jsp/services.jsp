<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Services List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
<h2>All Services</h2>

<div class="buttons" style="margin-bottom: 15px;">
    <a href="/">
        <button>Back To Dashboard</button>
    </a>
<c:if test="${sessionScope.currentUser.role == 2}">
    <a href="add-service">
        <button>Add Service</button>
    </a>
</c:if>
</div>

<table border="1" cellpadding="10" cellspacing="0">
    <thead>
    <tr>
        <th>NO.</th>
        <th>Title</th>
        <th>Price</th>
        <th>Description</th>
        <c:if test="${sessionScope.currentUser.role == 0}">
            <th>Order</th>
        </c:if>
        <c:if test="${sessionScope.currentUser.role == 2}">
            <th>Status</th>
            <th>Parent</th>
            <th>Created at</th>
            <th>Update</th>
            <th>Delete</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <% int counter = 1; %>
    <c:forEach var="service" items="${services}">
        <tr>
            <td><%= counter %></td>
            <td>${service.title}</td>
            <td>${service.price} $</td>
            <td>${service.description}</td>
            <c:if test="${sessionScope.currentUser.role == 0}">
                <td>
                    <a href="${pageContext.request.contextPath}/submitOrder">Order</a>
                </td>
            </c:if>
            <c:if test="${sessionScope.currentUser.role == 2}">
                <td>
                    <c:choose>
                        <c:when test="${service.status == 0}">Inactive</c:when>
                        <c:when test="${service.status == 1}">Active</c:when>
                        <c:otherwise>Not set</c:otherwise>
                    </c:choose>
                </td>
                <td>
                        ${service.parentTitle}
                </td>
                <td>${service.createdAt}</td>
                <td>
                    Update
                </td>
                <td>
                    <a href="delete-service?id=${service.id}">Delete</a>
                </td>
            </c:if>
        </tr>
        <% counter++; %>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
