
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='pojo-section'>
<div class='pojo-section-header'>Event Delete</div>
<div class='pojo-section-body'>
<div align="center">
    <c:if test="${!empty msg}">
       <font class="pojo-msg-alert"><b>${msg}</b></font><br /><br />
    </c:if>

    <font class="Verdana">Do you really want to delete this item?</font>
    <br /><br />
    <div class="pojo-section-alternate">
      ID = ${id} - Name = ${name} - Description = ${description}
    </div>
    <br />
    <font class="Verdana"><b>Warning - This delete operation can not be undone</b></font>
    <br />

  <form id="formDeleteEvent" method="POST" action="do">
    <input type='hidden' id="opDeleteEvent" name="op" value="">
    <input type='hidden' name="id" value="${id}">
   
    <button class='btnDelete' id="add" type="button" onclick='goDeleteEvent("${opAction}");'>Delete</button>
    &nbsp;&nbsp;&nbsp;
    <button class='btnBack' id="back" type="button" onclick='goDeleteEvent("${opBack}");'>Back</button>

  </form>
</div>

<script>
  function goDeleteEvent(op)
  {
    $('#opDeleteEvent').val(op);
    $('#formDeleteEvent').submit();
  }
</script>
</div>
</div>
