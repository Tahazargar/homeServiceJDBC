<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <!-- You can move this style block to a separate file -->
    <!-- Paste shared CSS here -->
</head>
<body>
<div class="form-box">
    <h2>Login</h2>
    <form action="/login" method="post">
        <input type="text" name="username" placeholder="Username" required />
        <input type="password" name="password" placeholder="Password" required />
        <button type="submit">Login</button>
    </form>
    <div class="form-footer">
        Don't have an account? <a href="register">Sign Up</a>
    </div>
</div>
</body>
</html>
