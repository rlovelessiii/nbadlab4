<%-- 
    Document   : profile
    Created on : Jul 16, 2019, 12:17:21 PM
    Author     : bobbysmedley
--%>

<%@page import="edu.uncc.nbad.UserInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <style>
            body {background-color: ${color}}
        </style>
    </head>
    <body>
        <p>Background color: 
            <a href="/nbadlab5_lab4-fork/membership?action=profile&color=white">White</a>
            <a href="/nbadlab5_lab4-fork/membership?action=profile&color=red">Red</a>
            <a href="/nbadlab5_lab4-fork/membership?action=profile&color=blue">Blue</a>
        </p>
        <%
            UserInfo user = (UserInfo) session.getAttribute("UserInfo");
            if (user != null) {
                out.println("<p>Name: " + user.getName() + "</p>");
                out.println("<p>Username: " + user.getUsername() + "</p>");
                out.println("<p>Password: " + user.getPassword() + "</p>");
                out.println("<p>Address: " + user.getAddress() + "</p>");
                out.println("<p>Country: " + user.getCountry() + "</p>");
                out.println("<p>Zip Code: " + user.getZip() + "</p>");
                out.println("<p>Email: " + user.getEmail() + "</p>");
                out.println("<p>Sex: " + user.getSex() + "</p>");
                out.println("<p>Languages: " + user.getLanguages() + "</p>");
                out.println("<p>Description: " + user.getAbout() + "</p>");
            }
            else {
                out.println("<p>Unauthorized!</p>");
                out.println("<p>Please <a href='/nbadlab5_lab4-fork/membership?action=signup'>Sign-Up</a></p>");
            }
        %>
    </body>
</html>
