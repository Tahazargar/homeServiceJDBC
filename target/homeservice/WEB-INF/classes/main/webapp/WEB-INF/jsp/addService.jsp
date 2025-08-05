<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Service</title>
</head>
<body>
<h2>Create Service</h2>
<form action="add-service" method="POST">
    <label for="title">Title:</label><br>
    <input type="text" id="title" name="title" required><br><br>

    <label for="price">Price:</label><br>
    <input type="number" id="price" name="price" required><br><br>

    <label for="description">Description:</label><br>
    <input type="text" id="description" name="description" required><br><br>

    <label for="status">Status:</label><br>
    <select id="status" name="status">
        <option value="0">Inactive</option>
        <option value="1" selected>Active</option>
    </select>

    <button type="submit">Create Service</button>
</form>
</body>
</html>
