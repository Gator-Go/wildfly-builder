<%@ page import="ppp.ppp.ppp.report.ConstRpt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='pojo-section'>
<div class='pojo-section-header'>Report Delete</div>
<div class='pojo-section-body'>
<div align="center">
  <c:if test="${!empty msg}">
    <font class="pojo-msg-alert"><b>${msg}</b></font><br><br>
  </c:if>

  <font class="Verdana">Do you really want to delete this item?</font>
  <br /><br />
  <div class="pojo-section-alternate">
    ID = ${id} - Name = ${reportName} - Description = ${reportDesc}
  </div>
  <div class="pojo-section-body">
    JRXML To Be Deleted = ${reportFileName}
  </div>
  <br />
  <font class="Verdana"><b>Warning - This delete operation can not be undone</b></font>
  <br />

  <form id="formDeleteReport" method="POST" action="do">
    <input type='hidden' id="opDeleteReport" name='<%= ConstRpt.OP %>' value="">
    <input type='hidden' name='<%= ConstRpt.ID %>' value="${id}">
   
    <button class='btnDelete' id="delete" type="button" onclick='goDeleteReport("${opAction}");'>Delete</button>
    &nbsp;&nbsp;&nbsp;
    <button class='btnBack' id="back" type="button" onclick='goDeleteReport("${opBack}");'>Back</button>
  </form>
</div>

<script>
  function goDeleteReport(op)
  {
    $('#opDeleteReport').val(op);
    $('#formDeleteReport').submit();
  }
</script>
</div>
</div>
