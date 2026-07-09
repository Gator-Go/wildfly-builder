<%@ page import="ppp.ppp.ppp.servlet.ControllerEnums"%>
<%@ page import="java.net.InetAddress" %>
<%@ page import="java.security.Principal" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String ipDisplay = System.getProperty("com.sw-builder.sync.app.ip-display");
String op = (String)request.getAttribute("op");
if (op == null) {
  op = "Home";
}
String role = (String)request.getAttribute("role");
if (role == null)
    role = "GUEST";
String jsp = ControllerEnums.Calls.get(op).getJsp();
String indexJsp = ControllerEnums.Calls.get(op).getIndexJsp();
String tabIdx = ControllerEnums.Calls.get(op).getTabIdx();
String tabIdx2 = ControllerEnums.Calls.get(op).getTabIdx2();
%>

<c:url value="do" var="HomeURL">
  <c:param name="op"   value="Home" />
</c:url>

<c:url value="do" var="AdminLogsURL">
  <c:param name="op"   value="AdminLogs" />
</c:url>

<c:url value="do" var="AdminEventsURL">
  <c:param name="op"   value="AdminEvents" />
</c:url>

<c:url value="do" var="AdminEmailSubsURL">
  <c:param name="op"   value="AdminEmailSubs" />
</c:url>

<c:url value="do" var="AdminSmsSubsURL">
  <c:param name="op"   value="AdminSmsSubs" />
</c:url>

___JSP_INDEX_URL_REPORT_FUNCTION___

___JSP_INDEX_URL_FUNCTION___
___JSP_REPORT_INDEX_URL_FUNCTION___

<!DOCTYPE html>

<html>
<head>
<title>XDXDXDXDX</title>
<link rel="shortcut icon" href="resources/gfx/logo.png" type="image/x-icon" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" type="text/css" href="resources/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/pojo_style.css" />

<script src="resources/js/jquery-1.11.1.min.js"></script>
<script src="resources/js/jquery-ui.min.js"></script>
<script src="https://maps.google.com/maps/api/js"></script>
<script src="resources/js/jquery.ui.map.js"></script>
<script src="resources/js/pojo-ui.js"></script>

<script>

$(document).ready(function() {

<c:set var="role" scope="session" value="<%= role %>"/>
<c:if test="${ role != 'ADMIN' }">
    $('#AdminTab').hide();
</c:if>

});

$(function () {

  $( "#tabs" ).tabs({

    active: <%= tabIdx %>,

    beforeActivate: function (event, ui) {

      var tabId = ui.newPanel.attr('id');

      if (tabId == "Home"){
        document.location = '<c:out value="${HomeURL}" />';
        return false;
      }
      if (tabId == "Admin"){
        document.location = '<c:out value="${AdminLogsURL}" />';
        return false;
      }

___JSP_INDEX_ID_FUNCTION___
___JSP_REPORT_INDEX_ID_FUNCTION___
    }

  });


<c:set var="tabIdx" scope="session" value="<%= tabIdx %>"/>
<c:if test="${ tabIdx == 1 }">
  $( "#adminTabs" ).tabs({
    active: <%= tabIdx2 %>,

    beforeActivate: function (event, ui) {
      var tabId = ui.newPanel.attr('id');

      if (tabId == "Logs"){
        document.location = '<c:out value="${AdminLogsURL}" />';
        return false;
      }
      if (tabId == "Events"){
        document.location = '<c:out value="${AdminEventsURL}" />';
        return false;
      }
      if (tabId == "EmailSubs"){
        document.location = '<c:out value="${AdminEmailSubsURL}" />';
        return false;
      }
      if (tabId == "SmsSubs"){
        document.location = '<c:out value="${AdminSmsSubsURL}" />';
        return false;
      }

___JSP_INDEX_ID_REPORT_FUNCTION___

    }
  });
</c:if>


___JSP_REPORT_ACTIVATE_FUNCTION___



});

</script>

</head>
<body>


<%
  Principal principal = request.getUserPrincipal();
  String username = "<a href='do?op=Home'>Login</a>";
  String logout = "";
  if (principal != null) {
    username = principal.toString();
    logout = "<a href='do?op=logout'>Logout</a>";
  }
%>

<table style="width: 100%;">
<tr><td style="padding:20px; display: block; float: left;">

<div class="ext-box">
        <img src="resources/gfx/logo.png" />
    <div class="int-box">
        <span>XDXDXDXDX</span>
    </div>
</div>

</td><td style="padding:20px; display: block; float: right;">

<div id="pojo-header">
      <span class="pojo-header-info"> <%= username %> &nbsp;&nbsp;&nbsp; <%= logout %> <br /> IP=<%= ipDisplay %> </span>
      <span class="pojo-section-break" />
</div>

</td></tr></table>

<div id="tabs">
	<ul>
		<li><a href="#Home">Home</a></li>
		<li id="AdminTab"><a href="#Admin">Admin</a></li>
___JSP_INDEX_LI_FUNCTION___
___JSP_REPORT_INDEX_LI_FUNCTION___


	</ul>
	<div id="Home">
<% if (tabIdx == "0") { %>
<jsp:include page="<%= jsp %>" />
<% } %>
        </div>

	<div id="Admin">
<% if (tabIdx == "1") { %>
<jsp:include page="<%= indexJsp %>" />
<% } %>
        </div>
___JSP_INDEX_DIV_FUNCTION___
___JSP_REPORT_INDEX_DIV_FUNCTION___

</div>

<c:if test="${ !empty showPicture }">
    <div id="pictureInclude"> <jsp:include page="picture.jsp" /> </div>
</c:if>

<c:if test="${ !empty showVideo }">
    <div id="videoInclude"> <jsp:include page="video.jsp" /> </div>
</c:if>

<c:if test="${ !empty showMap }">
    <div id="mapInclude"> <jsp:include page="map.jsp" /> </div>
</c:if>

<c:if test="${ !empty showComments }">
    <div id="commentsInclude"> <jsp:include page="list/PostingCommentList.jsp" /> </div>
</c:if>

</body>
</html>
