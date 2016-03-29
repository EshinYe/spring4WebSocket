<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>输入用户名</title>
</head>
<body>
   <form action="/Spring4WebSocketDemo/websocket/toClient" method="post">
        <table border =1 >
            <tr>
                <td>用户名：</td>
                <td><input type = "text" name = "user" id = "user"></td>
            </tr>
            <tr>
                <td>提交：</td>
                <td><input type = "submit" ></td>
            </tr>
        </table>
     </form>
</body>
</html>