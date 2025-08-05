<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
<h2>All Users</h2>

<div class="buttons" style="margin-bottom: 15px;">
    <a href="/">
        <button>Back To Dashboard</button>
    </a>
    <a href="add-user">
        <button>Add User</button>
    </a>
</div>

<table border="1" cellpadding="10" cellspacing="0">
    <thead>
    <tr>
        <th>NO.</th>
        <th>Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Role</th>
        <th>Status</th>
        <th>Update</th>
        <th>Delete</th>
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
                <c:choose>
                    <c:when test="${user.role == 2}">Admin</c:when>
                    <c:when test="${user.role == 1}">Expert</c:when>
                    <c:otherwise>User</c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${user.status == 0}">Inactive</c:when>
                    <c:when test="${user.status == 1}">Active</c:when>
                    <c:otherwise>Banned</c:otherwise>
                </c:choose>

            </td>
            <td>
                Update
            </td>
            <td>
                <a href="delete-user?id=${user.id}">Delete</a>
            </td>
        </tr>
        <% counter++; %>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
