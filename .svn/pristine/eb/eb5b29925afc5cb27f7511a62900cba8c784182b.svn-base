<%@ page import="ppp.ppp.ppp.report.ConstRpt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="do" var="addURL">
      <c:param name="<%= ConstRpt.OP %>"   value="<%= ConstRpt.OP_ADMIN_RPT_ADD %>" />
</c:url>

<div class='pojo-section'>
<div class='pojo-section-header'>Admin Report List</div>
<div class='pojo-section-body'>
<table border ='0'>
  <tr>
    <td align='center' valign='top' width='13%'>
      <a href='<c:out value="${addURL}" />'>
        <button class='btnAdd'>Add</button>
      </a>
    </td>

    <td align='center' valign='top' width='70%'></td>
  </tr>
</table>
<br>

   <c:if test="${!empty msg}">
   <font class="pojo-msg-alert"><b>${msg}</b></font><br><br>
   </c:if>

<table id="listtable" border ='0' class="pojo_tablestyle" >

  <tr>
    <th valign='top' width='15%'>Action</th>
    <th valign='top' width='20%'>Name</th>
    <th valign='top' width='50%'>Description</th>
  </tr>


  <c:forEach items="${reports}" var="item">

    <tr>

    <c:url value="do" var="ViewURL">
      <c:param name="<%= ConstRpt.OP %>"   value="<%= ConstRpt.OP_ADMIN_RPT_VIEW %>" />
      <c:param name="<%= ConstRpt.ID %>"   value="${item.id}" />
    </c:url>

    <c:url value="do" var="EditURL">
      <c:param name="<%= ConstRpt.OP %>"   value="<%= ConstRpt.OP_ADMIN_RPT_EDIT %>" />
      <c:param name="<%= ConstRpt.ID %>"   value="${item.id}" />
    </c:url>

    <c:url value="do" var="DeleteURL">
      <c:param name="<%= ConstRpt.OP %>"   value="<%= ConstRpt.OP_ADMIN_RPT_DELETE %>" />
      <c:param name="<%= ConstRpt.ID %>"   value="${item.id}" />
    </c:url>

    <td valign='top'>
      <a href='<c:out value="${ViewURL}" />'><button class='btnViewIcon'>View Report</button></a>
      <a href='<c:out value="${EditURL}" />'><button class='btnEditIcon'>Edit Report</button></a>
      <a href='<c:out value="${DeleteURL}" />'><button class='btnDeleteIcon'>Delete Report</button></a>
    </td>

    <td>${item.reportName}</td>
    <td>${item.reportDesc}</td>

    </tr>
  </c:forEach>
</table>
</div>
</div>
