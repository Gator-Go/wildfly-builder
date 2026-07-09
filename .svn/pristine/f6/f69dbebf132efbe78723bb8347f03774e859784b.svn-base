<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String httpRoot = System.getProperty("com.sw-builder.sync.app.http-root");
%>

<div class='pojo-section'>
<div class='pojo-section-header'>DDDDD Add Form</div>
<div class='pojo-section-body'>
<div align="left">

    <c:set var="yyyyy" value="${yyyyy}"/>
    <form id="idYyyyyAdd" name="YyyyyAdd" action="do" method="POST">

        <p>
            <label style="color: red;width: 100%;text-align: left;">${errorMessage}</label> 
        </p>

        <input type='hidden' id="opYyyyyAdd" name='op' value="${opAction}">
        <input type='hidden' name='id' value="0">
        <input type='hidden' name='deviceId' value="0">
        <input type='hidden' name='yyyyyId' value="0">
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

___JSP_ADD_STRING___
___JSP_ADD_ENUM___
___JSP_ADD_TAG___
___JSP_ADD_INTEGER___
___JSP_ADD_LONG___
___JSP_ADD_DOUBLE___
___JSP_ADD_BOOLEAN___
___JSP_ADD_BIG_DECIMAL___
___JSP_ADD_MONEY___
___JSP_ADD_DATE___
___JSP_ADD_DATE_TIME___
___JSP_ADD_ONE2MANY_CHILD___
___JSP_ADD_ONE2MANY_CHILD_ALERT___
___JSP_TR_FINALLY_3___

___JSP_ADD_LOC___
___JSP_ADD_CURRENT_LOC___
___JSP_ADD_CLOB___
___JSP_ADD_CAMERA___
___JSP_ADD_VIDEO___
___JSP_ADD_THUMBNAIL___
___JSP_ADD_POST___

        </table>

___JSP_ADD_PIC_THUMBNAIL___
___JSP_ADD_THUMB_VIDEO___
___JSP_ADD_THUMB_CAMERA___
___JSP_ADD_THUMB_POST___

        <p>
            <button class='btnAdd' id="add" type="button" onclick='goYyyyyAdd("${opAction}");'>Add</button>
            &nbsp;&nbsp;&nbsp;
            <button class='btnBack' id="back" type="button" onclick='goYyyyyAdd("${opBack}");'>Back</button>
        </p>

    </form>
</div>

<script>
  function goYyyyyAdd(op)
  {
    $('#opYyyyyAdd').val(op);
    $('#idYyyyyAdd').submit();
  }

  function goYyyyyUpload(op, type)
  {
    $('#opYyyyyAdd').val(op);
    $('#postType').val(type);
    $('#idYyyyyAdd').submit();
  }

  function goDatePicker(y, fmt)
  {
    $(y).datepicker({dateFormat:fmt});
    $(y).focus();
  }
</script>

</div>
</div>
