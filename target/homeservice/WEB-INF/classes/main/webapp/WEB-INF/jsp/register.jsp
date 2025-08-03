<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <!-- You can move this style block to a separate file -->
    <!-- Paste shared CSS here -->
</head>
<body>
<div class="form-box">
    <h2>Sign Up</h2>
    <form action="/signup" method="post">
        <input type="text" name="fullname" placeholder="Full Name" required />
        <input type="email" name="email" placeholder="Email" required />
        <input type="password" name="password" placeholder="Password" required />
        <button type="submit">Sign Up</button>
    </form>
    <div class="form-footer">
        Already have an account? <a href="login">Login</a>
    </div>
</div>
</body>
</html>
