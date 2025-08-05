<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<h2>Create User</h2>
<form action="add-user" method="POST">
    <label for="fullName">Full Name:</label><br>
    <input type="text" id="fullName" name="fullName" required><br><br>

    <label for="email">Email:</label><br>
    <input type="email" id="email" name="email" required><br><br>

    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password" required><br><br>

    <label for="role">Role:</label><br>
    <select id="role" name="role">
        <option value="0" selected>customer</option>
        <option value="2">admin</option>
        <option value="1">expert</option>
    </select>

    <button type="submit">Create User</button>
</form>
</body>
</html>
