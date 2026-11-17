<%@ page import="java.net.InetAddress" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="resources/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="resources/css/pojo_style.css" />
<script src="resources/js/jquery-1.11.1.min.js"></script>
<script src="resources/js/jquery-ui.min.js"></script>
<script src="resources/js/pojo-ui.js"></script>
</head>
<body>
<header class="app-header">
  <div class="app-header-content">
    <img src="resources/gfx/logo.png" alt="Logo" />
    <div>
      <h1 class="app-title">App Login</h1>
    </div>
  </div>
</header>

<main class="container">
  <div id="pojo-login" class="card">
    <form name="login" method="POST" action="j_security_check">
      <div>
        <label for="j_username">User</label>
        <input type="text" id="j_username" name="j_username" required />
      </div>
      <div>
        <label for="j_password">Password</label>
        <input type="password" id="j_password" name="j_password" required />
      </div>
      <div>
        <button class="btn" type="submit" name="login">Login</button>
      </div>
    </form>
  </div>
</main>
</body>
</html>
