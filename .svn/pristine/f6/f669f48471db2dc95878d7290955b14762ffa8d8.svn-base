
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='pojo-section'>
<div class='pojo-section-header'>User Email Subs Delete</div>
<div class='pojo-section-body'>
<div align="center">
    <c:if test="${!empty msg}">
       <font class="pojo-msg-alert"><b>${msg}</b></font><br /><br />
    </c:if>

    <font class="Verdana">Do you really want to delete this item?</font>
    <br /><br />
    <c:set var="parent" value="${emailSubs}"/>
    <div class="pojo-section-alternate">
      ID = ${parent.id} - First Name = ${parent.firstName} - Last Name = ${parent.lastName}
    </div>
    <br />
    <font class="Verdana"><b>Warning - This delete operation can not be undone</b></font>
    <br /><br />

  <form id="formDeleteEmailSubs" method="POST" action="do">
    <input type='hidden' id="opDeleteEmailSubs" name="op" value="">
    <input type='hidden' name="id" value="${parent.id}">
   
    <button class='btnDelete' id="add" type="button" onclick='goDeleteEmailSubs("${opAction}");'>Delete</button>
    &nbsp;&nbsp;&nbsp;
    <button class='btnBack' id="back" type="button" onclick='goDeleteEmailSubs("${opBack}");'>Back</button>

  </form>
</div>

<script>
  function goDeleteEmailSubs(op)
  {
    $('#opDeleteEmailSubs').val(op);
    $('#formDeleteEmailSubs').submit();
  }
</script>
</div>
</div>
