<%@ page import="ppp.ppp.ppp.report.ConstRpt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='pojo-section'>
<div class='pojo-section-header'>Report Edit</div>
<div class='pojo-section-body'>
<div align="center">
  <form id="formEditReport" method="POST" action="do">

    <input type='hidden' id="opEditReport" name='<%= ConstRpt.OP %>' value="">
    <input type='hidden' name='<%= ConstRpt.ID %>' value="${id}">
    <br />

    <div class="pojo-section-alternate">
    ID: ${id}
    <br /><br /></div>

    <div class="pojo-section-body">
      <label for="reportName">Report Name:</label>
      <input type="text" id="reportName" name="reportName" value="${reportName}" size='30' maxlength='30'/>
    <br /><br /></div>

    <div class="pojo-section-alternate">
      <label for="reportDesc">Report Description:</label>
      <input type="text" id="reportDesc" name="reportDesc" value="${reportDesc}" size='70' maxlength='70'/>
    <br /><br /></div>

    <div class="pojo-section-body">
    JRXML File: ${reportFileName}
    <br /><br /></div>

  <div id="msg" align='center'></div>

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

   <c:forEach items="${rptParamEdits}" var="item">
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
    <td>${item.name}</td>
    <td>
      <input type="text" name="desc<%= x %>" value="${item.desc}" size='50' maxlength='150'/>
   </td>
    <td>${item.req}</td>
    <td>${item.dataType}
       <input type="hidden" id="dataType<%= x %>" name="dataType<%= x %>" value="${item.dataType}"/>
    </td>

    <c:if test="${!empty item.dateFormat}">
       <input type="hidden" name="paramType<%= x %>" value="${item.paramType}"/>
       <td>${item.paramType}</td>
    </c:if>

    <input type="hidden" id="saveParamType<%= x %>" value="${item.paramType}"/>
    <c:if test="${empty item.dateFormat}">
      <td>
       <select id="paramType<%= x %>" name="paramType<%= x %>" onchange='setInfoDiv("<%= x %>");'>
       <c:forEach items="${item.paramOptions}" var="item2">
         <option value='<c:out value="${item2.option}"/>' <c:out value="${item2.selected}"/>><c:out value="${item2.option}"/></option>
       </c:forEach>
       </select>
      </td>
    </c:if>

    <td>
    <input type="hidden" name="paramId<%= x %>" value="${item.paramId}"/>
    <input type="hidden" id="saveMinValue<%= x %>" value="${item.minValue}"/>
    <input type="hidden" id="saveMaxValue<%= x %>" value="${item.maxValue}"/>
    <div id="infoDiv<%= x %>">

      <c:if test="${!empty item.minValue}">
         <input onchange='checkMinVal("<%= x %>");' type="text" id="minValue<%= x %>" name="minValue<%= x %>" value="${item.minValue}" size='10' maxlength='10'/>
         To
         <input onchange='checkMaxVal("<%= x %>");' type="text" id="maxValue<%= x %>" name="maxValue<%= x %>" value="${item.maxValue}" size='10' maxlength='10'/>
      </c:if>

      <c:if test="${!empty item.listOptions}">
         List Options Separated by '|': <textarea id="listOptions<%= x %>" name="listOptions<%= x %>" ROWS="3" COLS="70">${item.listOptions}</textarea>
      </c:if>

      <c:if test="${!empty item.sqlStmt}">
         SQL Select Statement: <textarea id="sqlStmt<%= x %>" name="sqlStmt<%= x %>" ROWS="3" COLS="70">${item.sqlStmt}</textarea>
      </c:if>

      <c:if test="${!empty item.dateFormat}">
         DatePicker Format:
         <select id="dateFormat<%= x %>" name="dateFormat<%= x %>">
         <c:forEach items="${item.datePickerOptions}" var="item3">
           <option value='<c:out value="${item3.option}"/>' <c:out value="${item3.selected}"/>><c:out value="${item3.option}"/></option>
         </c:forEach>
         </select>
      </c:if>

    </div>
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

    <button class='btnEdit' id="edit" type="button" onclick='goEditReport("${opAction}");'>Edit</button>
    &nbsp;&nbsp;&nbsp;
    <button class='btnBack' id="back" type="button" onclick='goEditReport("${opBack}");'>Back</button>
  </form>
</div>

<script>
  function goEditReport(op)
  {
    $('#opEditReport').val(op);
    $('#formEditReport').submit();
  }

  function checkMinVal(idx)
  {
    var minValue = $( "#minValue" + idx ).val();
    var saveMinValue = $( "#saveMinValue" + idx ).val();
    var maxValue = $( "#maxValue" + idx ).val();
    $('#msg').html("");

    if ( !$.isNumeric(minValue)) {
      $('#msg').html("<font class='pojo-msg-alert'><b>Range values must be numeric</b></font>");
      $("#minValue" + idx).val(saveMinValue);
      return;
    }

    if ( !(maxValue.trim() === "")) {
      var min = parseInt(minValue);
      var max = parseInt(maxValue);

      if ( min >= max) {
        $('#msg').html("<font class='pojo-msg-alert'><b>First value must be less than second value</b></font>");
        $("#minValue" + idx).val(saveMinValue);
        return;
      }
    }

    $("#saveMinValue" + idx).val(minValue);
  }

  function checkMaxVal(idx)
  {
    var maxValue = $( "#maxValue" + idx ).val();
    var saveMaxValue = $( "#saveMaxValue" + idx ).val();
    var minValue = $( "#minValue" + idx ).val();
    $('#msg').html("");

    if ( !$.isNumeric(maxValue)) {
      $('#msg').html("<font class='pojo-msg-alert'><b>Range values must be numeric</b></font>");
      $("#maxValue" + idx).val(saveMaxValue);
      return;
    }

    if ( !(minValue.trim() === "")) {
      var min = parseInt(minValue);
      var max = parseInt(maxValue);

      if ( min >= max) {
        $('#msg').html("<font class='pojo-msg-alert'><b>First value must be less than second value</b></font>");
        $("#maxValue" + idx).val(saveMaxValue);
        return;
      }
    }

    $("#saveMaxValue" + idx).val(maxValue);
  }

  function setInfoDiv(idx)
  {
    var paramType = $( "#paramType" + idx ).val();
    var saveParamType = $( "#saveParamType" + idx ).val();
    var dataType = $( "#dataType" + idx ).val();

    $('#msg').html("");

    if ( paramType === "VALUE") {
      $("#infoDiv" + idx).html('');
    }

    if ( paramType === "RANGE") {

      if ( dataType === "String") {
        $('#msg').html("<font class='pojo-msg-alert'><b>Data Type must not be String for RANGE option</b></font>");
        $("#paramType" + idx).val(saveParamType);
        return;
      }

      $("#infoDiv" + idx).html('' +
         '<input onchange=\'checkMinVal("' + idx + '");\' type="text" id="minValue' + idx + '" name="minValue' + idx + '" value="" size="10" maxlength="10"/>' +
         ' To ' +
         '<input onchange=\'checkMaxVal("' + idx + '");\' type="text" id="maxValue' + idx + '" name="maxValue' + idx + '" value="" size="10" maxlength="10"/>'
      );
    }

    if ( paramType === "LIST") {

      if ( !(dataType === "String")) {
        $('#msg').html("<font class='pojo-msg-alert'><b>Data Type must be String for LIST option</b></font>");
        $("#paramType" + idx).val(saveParamType);
        return;
      }

      $("#infoDiv" + idx).html('' +
         'List Data: <textarea id="listOptions' + idx + '" name="listOptions' + idx + '" ROWS="3" COLS="70"></textarea>'
      );
    }

    if ( paramType === "SELECT") {

      if ( !(dataType === "String")) {
        $('#msg').html("<font class='pojo-msg-alert'><b>Data Type must be String for SELECT option</b></font>");
        $("#paramType" + idx).val(saveParamType);
        return;
      }

      $("#infoDiv" + idx).html('' +
         'SQL Select: <textarea id="sqlStmt' + idx + '" name="sqlStmt' + idx + '" ROWS="3" COLS="70"></textarea>'
      );
    }

    $("#saveParamType" + idx).val(paramType);

  }
</script>
</div>
</div>
