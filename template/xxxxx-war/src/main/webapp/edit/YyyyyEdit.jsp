<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String httpRoot = System.getProperty("com.sw-builder.sync.app.http-root");
%>

<div class='pojo-section'>
<div class='pojo-section-header'>DDDDD Edit Form</div>
<div class='pojo-section-body'>
<div align="left">

    <c:set var="yyyyy" value="${yyyyy}"/>
    <form id="idYyyyyEdit" name="Yyyyy" action="do" method="POST">

        <p>
            <label style="color: red;width: 100%;text-align: left;">${errorMessage}</label> 
        </p>

        <input type='hidden' id="opYyyyyEdit" name='op' value="${opAction}">
        <input type='hidden' name='id' value="${yyyyy.id}">
        <input type='hidden' name='deviceId' value="${yyyyy.deviceId}">
        <input type='hidden' name='yyyyyId' value="${yyyyy.yyyyyId}">
        <input type='hidden' name='lastUpdate' value="${yyyyy.lastUpdate}">
        <input type='hidden' name='deleteFlag' value="${yyyyy.deleteFlag}">
        <input type='hidden' name='mode' value="${mode}">

        <table class="pojo_tablestyle">

            <tr>
               <th width="10%"></th>
               <th width="23%"></th>
               <th width="10%"></th>
               <th width="23%"></th>
               <th width="10%"></th>
               <th width="23%"></th>
            </tr>


___JSP_EDIT_STRING___
___JSP_EDIT_ENUM___
___JSP_EDIT_TAG___
___JSP_EDIT_INTEGER___
___JSP_EDIT_LONG___
___JSP_EDIT_DOUBLE___
___JSP_EDIT_BOOLEAN___
___JSP_EDIT_BIG_DECIMAL___
___JSP_EDIT_MONEY___
___JSP_EDIT_DATE___
___JSP_EDIT_DATE_TIME___
___JSP_ADD_ONE2MANY_CHILD___
___JSP_ADD_ONE2MANY_CHILD_ALERT___
___JSP_TR_FINALLY_3___

___JSP_EDIT_LOC___
___JSP_EDIT_CURRENT_LOC___
___JSP_EDIT_CLOB___
___JSP_EDIT_CAMERA___
___JSP_EDIT_VIDEO___
___JSP_EDIT_THUMBNAIL___
___JSP_EDIT_POST___
        </table>

___JSP_EDIT_PIC_THUMBNAIL___
___JSP_EDIT_THUMB_VIDEO___
___JSP_EDIT_THUMB_CAMERA___
___JSP_EDIT_THUMB_POST___

        <p>
            <button class='btnEdit' id="edit" type="button" onclick='goYyyyyEdit("${opAction}");'>Edit</button>
            &nbsp;&nbsp;&nbsp;
            <button class='btnBack' id="back" type="button" onclick='goYyyyyEdit("${opBack}");'>Back</button>
        </p>

    </form>

</div>

<script>
  function goYyyyyEdit(op)
  {
    $('#opYyyyyEdit').val(op);
    $('#idYyyyyEdit').submit();
  }

  function goYyyyyUpload(op, type)
  {
    $('#opYyyyyEdit').val(op);
    $('#postType').val(type);
    $('#idYyyyyEdit').submit();
  }

  function goDatePicker(y, fmt)
  {
    $(y).datepicker({dateFormat:fmt});
    $(y).focus();
  }
</script>

</div>
</div>
