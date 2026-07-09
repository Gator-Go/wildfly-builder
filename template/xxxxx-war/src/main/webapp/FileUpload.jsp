<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='pojo-section'>
<div class='pojo-section-header'>${uploadTitle}</div>
<div class='pojo-section-body'>
<div align="center">
<c:if test="${!empty msg}">
   <font class="pojo-msg-alert"><b>${msg}</b></font><br /><br />
</c:if>

<form id="uploadForm" method="post" enctype="multipart/form-data" action="do">
    <input type='hidden' id="UploadActionId" value="${opUploadAction}">

    <div class="pojo-font">
       <font class="pojo-font">File Path:</font>
       <input type='file' id='filePath' name='filePath' value='' size='100' maxlength='100' class="pojo-form-input-field">
    <br /><br /></div>
    
    <button class='btnUpload' id="UploadSubmit" onclick='goUploadAction();'>Upload</button>
    &nbsp;&nbsp;&nbsp;
    <button class='btnBack' id="back" onclick='goUploadBack();'>No Upload</button>
</form>
</div> 
    
<script>
  function goUploadAction()
  {
    if ( $('#filePath').val() == '' ) {
        goUploadBack();
        return;
    }

    var op = $('#UploadActionId').val();
    $("#uploadForm").attr("action", "do?op=" + op + "&mode=${mode}&postType=${postType}")
    $('#uploadForm').submit();
  }
  function goUploadBack()
  {
    var op = $('#UploadActionId').val();
    $("#uploadForm").attr("action", "do?op=" + op + "&mode=${mode}&postType=${postType}&noUpload=true")
    $('#uploadForm').submit();
  }
</script>
</div>
</div>
