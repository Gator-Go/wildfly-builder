<%@ page import="ppp.ppp.ppp.servlet.ControllerEnums" %>
<%
String op = (String)request.getAttribute("op");
if (op == null) {
  op = "Home";
}
String jsp = ControllerEnums.Calls.get(op).getJsp();
String tabIdx2 = ControllerEnums.Calls.get(op).getTabIdx2();
%>
<div class='pojo-section'>
<div class='pojo-section-header'>Admin Home</div>
<div class='pojo-section-body'>
<div id="adminTabs">
	<ul>
		<li><a href="#Logs">Logs</a></li>
		<li><a href="#Events">Events</a></li>
		<li><a href="#EmailSubs">Email Subs</a></li>
		<li><a href="#SmsSubs">SMS Subs</a></li>
___JSP_ADMIN_INDEX_LI_REPORT_FUNCTION___
	</ul>

	<div id="Logs">
<% if ("0".equals(tabIdx2)) { %>
<jsp:include page="<%= jsp %>" />
<% } %>
	</div>
	<div id="Events">
<% if ("1".equals(tabIdx2)) { %>
<jsp:include page="<%= jsp %>" />
<% } %>
	</div>
	<div id="EmailSubs">
<% if ("2".equals(tabIdx2)) { %>
<jsp:include page="<%= jsp %>" />
<% } %>
	</div>
	<div id="SmsSubs">
<% if ("3".equals(tabIdx2)) { %>
<jsp:include page="<%= jsp %>" />
<% } %>
	</div>

___JSP_ADMIN_INDEX_REPORT_FUNCTION___

</div>
</div>
</div>
