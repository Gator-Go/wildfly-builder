<%@ page import="ppp.ppp.ppp.report.ConstRpt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="do" var="backURL">
  <c:param name="<%= ConstRpt.OP %>"   value="<%= ConstRpt.OP_ADMIN_HOME %>" />
</c:url>

<div class='pojo-section'>
<div class='pojo-section-header'>Report View</div>
<div class='pojo-section-body'>
<table border ='0'>
<tr>
  <td valign='top' width='40%'>
    <a href='<c:out value="${backURL}" />'>
      <button class='btnBack'>Back</button>
    </a>
  </td>

  <td valign='top' width='50%'></td>
</tr></table>

<div align="center">

<div class="pojo-section-alternate">
ID: ${id}
<br><br></div>

<div class="pojo-section-body">
Report Name: ${reportName}
<br><br></div>

<div class="pojo-section-alternate">
Report Descrition: ${reportDesc}
<br><br></div>

<div class="pojo-section-body">
JRXML File: ${reportFileName}
<br><br></div>
</div>

<% int x = 0; %>
<table id="paramlist" class="pojo_tablestyle" >
  <tr>
    <th valign='top' width='10%'>Name</th>
    <th valign='top' width='20%'>Description</th>
    <th valign='top' width='5%'>Req</th>
    <th valign='top' width='10%'>Data Type</th>
    <th valign='top' width='10%'>Param Type</th>
    <th valign='top' width='45%'>Info</th>
  </tr>

  <c:forEach items="${rptParams}" var="item">
<%
    if ( (x++) % 2 == 0 )
    {
%>
    <tr class="pojo-section-body">
<%
    }
    else
    {
%>
    <tr class="pojo-section-alternate">
<%
    }
%>
    <td>${item.paramName}</td>
    <td>${item.paramDesc}</td>
    <td>${item.paramRequired}</td>
    <td>${item.paramDataType}</td>
    <td>${item.paramType}</td>

    <td>
      <c:if test="${!empty item.paramMinValue}">
         ${item.paramMinValue} To ${item.paramMaxValue}
      </c:if>

      <c:if test="${!empty item.paramListOptions}">
         ${item.paramListOptions}
      </c:if>

      <c:if test="${!empty item.paramSqlStmt}">
         ${item.paramSqlStmt}
      </c:if>

      <c:if test="${!empty item.paramDateFormat}">
         DatePicker Format: ${item.paramDateFormat}
      </c:if>
    </td>
    </tr>
  </c:forEach>
</table>

<% x = 0; %>
<table id="sortlist" class="pojo_tablestyle" >
  <tr>
    <th valign='top' width='50%'>Sort Options</th>
  </tr>

  <c:forEach items="${rptSortOptions}" var="item2">
<%
    if ( (x++) % 2 == 0 )
    {
%>
    <tr class="pojo-section-body">
<%
    }
    else
    {
%>
    <tr class="pojo-section-alternate">
<%
    }
%>
    <td>${item2.sortOption}</td>
    </tr>
  </c:forEach>
</table>

</div>
</div>
