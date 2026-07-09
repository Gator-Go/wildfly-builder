<%@ page import="ppp.ppp.ppp.report.ConstRpt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='pojo-section'>
<div class='pojo-section-header'>Reports List</div>
<div class='pojo-section-body'>
<table id="listtable" class="pojo_tablestyle" >
  <tr>
    <th valign='top' width='10%'>Action</th>
    <th valign='top' width='20%'>Name</th>
    <th valign='top' width='50%'>Description</th>
  </tr>

  <c:forEach items="${reports}" var="item">

  <tr>
    <c:url value="do" var="GenReportURL">
      <c:param name="<%= ConstRpt.OP %>"   value="<%= ConstRpt.OP_GEN_RPT %>" />
      <c:param name="<%= ConstRpt.ID %>"   value="${item.id}" />
    </c:url>

    <td valign='top'>
      <a href='<c:out value="${GenReportURL}" />'><button class='btnGenerateIcon'>Generate Report</button></a>
    </td>

    <td>${item.reportName}</td>
    <td>${item.reportDesc}</td>

    </tr>
  </c:forEach>
</table>
</div>
</div>
