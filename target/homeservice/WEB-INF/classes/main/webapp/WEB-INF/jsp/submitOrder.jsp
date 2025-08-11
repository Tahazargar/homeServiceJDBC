<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Submit Order</title>
</head>
<body>
<h2>Submit Order</h2>
<form action="submitOrder" method="POST">
    <label for="price">Proposed Price:</label><br>
    <input type="number" id="price" name="price" required><br><br>

    <input name="serviceId" type="hidden" value="${param.serviceId}">

    <label for="description">Work Description:</label><br>
    <input type="text" id="description" name="description" required><br><br>

    <label for="dueDate">Due Date:</label><br>
    <input type="date" id="dueDate" name="dueDate" required><br><br>

    <label for="address">Address:</label><br>
    <input type="text" id="address" name="address" required><br><br>

    <button type="submit">Submit Order</button>
</form>
</body>
</html>
