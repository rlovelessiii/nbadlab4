<%-- 
    Document   : profile
    Created on : Jul 16, 2019, 12:17:21 PM
    Author     : bobbysmedley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
    </head>
    <body>
        <p>Name: ${UserData.name}</p>
        <p>Username: ${UserData.username}</p>
        <p>Password: ${UserData.password}</p>
        <p>Address: ${UserData.address}</p>
        <p>Country: ${UserData.country}</p>
        <p>Zip Code: ${UserData.zip}</p>
        <p>Email: ${UserData.email}</p>
        <p>Sex: ${UserData.sex}</p>
        <p>Languages: ${UserData.languages}</p>
        <p>Description: ${UserData.about}</p>
    </body>
</html>
