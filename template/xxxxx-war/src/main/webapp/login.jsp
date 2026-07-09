<%@ page import="java.net.InetAddress" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Social - Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="resources/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="resources/css/pojo_style.css" />
<script src="resources/js/jquery-1.11.1.min.js"></script>
<script src="resources/js/jquery-ui.min.js"></script>
<script src="resources/js/pojo-ui.js"></script>
</head>
<body>
<div id="pojo-header">
  <img src="resources/gfx/logo.png" />
<%
  String ipAddress = InetAddress.getLocalHost().getHostAddress();
%>
  <span class="pojo-header-info">  IP=<%= ipAddress %></span>
  <span class="pojo-section-break" />
</div>
<div id="pojo-login">
  <form name="login" method="POST" action="j_security_check">
    <div>
      <label>User</label>
      <input type="text" name="j_username" />
    </div>
    <div>
      <label>Password</label>
      <input type="password" name="j_password" />
    </div>
    <div><button class="btnBlank" type="submit" name="login">Login</button></div>
  </form>
</div>
</body>
</html>
