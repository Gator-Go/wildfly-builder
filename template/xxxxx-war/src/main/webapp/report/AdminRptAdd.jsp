<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='pojo-section'>
<div class='pojo-section-header'>${title}</div>
<div class='pojo-section-body'>
<div align="center">
<c:if test="${!empty msg}">
   <font class="pojo-msg-alert"><b>${msg}</b></font><br><br>
</c:if>

<form id="uploadForm" method="post" enctype="multipart/form-data" action="do">

    <input type='hidden' id="actionId" value="${opAction}">
    <input type='hidden' id="backId" value="${opBack}">

    <div class="pojo-font">
       <font class="pojo-font">File Path:</font>
       <input type='file' name='filePath' value='' size='100' maxlength='100' class="pojo-form-input-field">
    <br><br></div>

    <button class='btnAdd' id="submit" onclick='goAction();'>Submit</button>
    &nbsp;&nbsp;&nbsp;
    <button class='btnBack' id="back" onclick='goBack();'>Back</button>
</form>
</div>

<script>
  function goAction()
  {
    var op = $('#actionId').val();
    $("#uploadForm").attr("action", "do?op=" + op)
    $('#uploadForm').submit();
  }
  function goBack()
  {
    var op = $('#backId').val();
    $("#uploadForm").attr("action", "do?op=" + op)
    $('#uploadForm').submit();
  }
</script>
</div>
</div>
