<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(session.getAttribute("currentUser") ==  null){
        response.sendRedirect("login");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Simple Dashboard</title>
    <style>
        body, html{
            overflow-x: hidden;
        }

        .sidebar {
            width: 220px;
            background-color: #2f3542;
            color: #fff;
            flex-shrink: 0;
            padding: 20px;
            position: fixed;
            height: 100vh;
        }

        .sidebar h2 {
            margin-bottom: 30px;
            font-size: 24px;
        }

        .sidebar ul {
            list-style: none;
        }

        .sidebar ul li {
            margin: 20px 0;
        }

        .sidebar ul li a {
            text-decoration: none;
            color: #dcdde1;
            transition: color 0.3s;
        }

        .sidebar ul li a:hover {
            color: #00a8ff;
        }

        .main-content {
            margin-left: 220px;
            padding: 20px;
            width: 100%;
        }

        .header {
            background-color: #fff;
            padding: 15px 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        .content {
            background-color: #fff;
            padding: 20px;
            border-radius: 6px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.05);
        }

        @media (max-width: 768px) {
            .sidebar {
                position: relative;
                width: 100%;
                height: auto;
            }

            .main-content {
                margin-left: 0;
            }
        }

    </style>
</head>
<body>
<div class="sidebar">
    <h2>Dashboard</h2>
    <ul>
        <li><a href="#">Overview</a></li>
        <c:if test="${currentUser.role == 2}">
            <li><a href="${pageContext.request.contextPath}/users">Users</a></li>
        </c:if>

        <li><a href="#">Experts</a></li>
        <li><a href="#">Requests</a></li>
        <li><a href="#">Settings</a></li>
        <li><a href="#">Logout</a></li>
    </ul>
</div>

<div class="main-content">
    <div class="header">
        <h1 style="margin-left: 30px">Welcome to the Admin Dashboard</h1>
    </div>

    <div class="content">
        <p style="margin-left: 30px">This is a simple dashboard layout you can use for different roles like admins, users, and experts. Customize the sidebar links and main content based on the user type after login.</p>
    </div>
</div>
</body>
</html>
