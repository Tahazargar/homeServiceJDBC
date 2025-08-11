<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Expert jobs</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
<h2>Expert jobs</h2>

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
    <th>Cancel</th>
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
        <form action="changeOrderStatus" method="POST">
          <select id="orderStatus" name="orderStatus">
            <option value="" <c:if test="${order.status == 0}">selected</c:if>></option>
            <option value="1" <c:if test="${order.status == 1}">selected</c:if>>Approved</option>
            <option value="2" <c:if test="${order.status == 2}">selected</c:if>>Started</option>
            <option value="3" <c:if test="${order.status == 3}">selected</c:if>>Completed</option>
          </select>
          <input name="orderId" value="${order.id}" type="hidden">
          <button type="submit">Change status</button>
        </form>
      </td>
      <td>
        <form action="deleteExpertJob" method="POST">
          <input name="orderId" type="hidden" value="${order.id}">
          <button type="submit">Cancel</button>
        </form>
      </td>
    </tr>
    <% counter++; %>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
