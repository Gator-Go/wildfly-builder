
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
var Parameters = [];

function Parameter (required, name, fireValue, description) {
    this.required = required;
    this.name = name;
    this.fireValue = fireValue;
    this.description = description;
}
Parameter.prototype.updateTo = function (required, name, fireValue, description) {
    this.required = required;
    this.name = name;
    this.fireValue = fireValue;
    this.description = description;
}
</script>

<div class='pojo-section'>
<div class='pojo-section-header'>Fire Event</div>
<div class='pojo-section-body'>
<div align="center">
  <form id="formFireEvent" method="POST" action="do">
    <input type='hidden' id="opFireEvent" name="op" value="">
    <input type='hidden' name="id" value="${id}">
    <input type='hidden' name="name" value="${name}">
    <input type='hidden' name="description" value="${description}">
    <input type='hidden' id="nbrParam" name="nbrParam" value="${nbrParam}">

    <c:if test="${!empty msg}">
       <font class="pojo-msg-alert"><b>${msg}</b></font><br /><br />
    </c:if>

    <div class="pojo-section-alternate">
    Name: ${name}
    <br /><br />
    </div>

    <div class="pojo-section-body">
    Description: ${description}
    <br /><br />
    </div>

    <div class="pojo-section-alternate">
    Message:<br />
    <textarea name="message" rows="10" cols="130" style="resize: none;" data-role="none"></textarea>
    <br />
    </div>

    <br /><br />

    <c:forEach items="${parameterFmts}" var="item">
       <script>Parameters.push(new Parameter("${item.required}","${item.name}","${item.fireValue}",
                                             "${item.description}"));</script>
    </c:forEach>

  <div id="showBlock">
    <font class="Verdana"><strong>Enter Parameter Values</strong></font>
    <br />

    <table id="listtable" cellpadding="2" cellspacing="2" width="98%">
    <thead>
      <tr class="pojo-section-alternate">
      <th valign='top' width='5%'>Required</th>
      <th valign='top' width='10%'>Name</th>
      <th valign='top' width='20%'>Value</th>
      <th valign='top' width='50%'>Description</th>
      </tr>
    </thead>
    <tbody id="parameters"></tbody>
    </table>
  </div>

    <button class='btnFire' id="add" type="button" onclick='goFireEvent("${opAction}");'>Fire</button>
    &nbsp;&nbsp;&nbsp;
    <button class='btnBack' id="back" type="button" onclick='goFireEvent("${opBack}");'>Back</button>

  </form>
</div>

<script>
  showParameters();

  function goFireEvent(op)
  {
    $('#opFireEvent').val(op);
    $('#formFireEvent').submit();
  }

  function goUpdateParameter(idx)
  {

    var requiredId = "#required" + idx;
    var nameId = "#name" + idx;
    var fireValueId = "#fireValue" + idx;
    var descriptionId = "#description" + idx;

    Parameters[idx].updateTo($( requiredId ).val(),$( nameId ).val(),$( fireValueId ).val(),$( descriptionId ).val());
    showParameters();
  }

  function showParameters() {

    if ($( nbrParam ).val() === "0") {
       $( showBlock ).hide();
       return;
    } 

    var parameterData = '';
    $.each(Parameters, function( index, value)
    {
      var requiredIcon = "";
      if (value.required == "true")
        requiredIcon = "<img border='0' src='resources/gfx/icon_alert_debug_16.png' alt='Required'/>";
      var requiredIndex = "required" + index;
      var nameIndex = "name" + index;
      var fireValueIndex = "fireValue" + index;
      var descriptionIndex = "description" + index;

      parameterData = parameterData +
        '<tr>' +

        '<td align="center"><input type="hidden" id="' + requiredIndex + '" name="' + requiredIndex + '" value="' +
             value.required + '">' + requiredIcon + '</td>' +

        '<td align="center"><input type="hidden" id="' + nameIndex + '" name="' + nameIndex + '" value="' +
             value.name + '">' + value.name + '</td>' +

        '<td align="center"><input onchange="javascript:goUpdateParameter(\'' + index +
             '\')" type="text" id="' + fireValueIndex + '" name="' + fireValueIndex + '" value="' +
             value.fireValue + '" size="30" maxlength="300"></td>' +

        '<td align="center"><input type="hidden" id="' + descriptionIndex + '" name="' + descriptionIndex + '" value="' +
             value.description + '">' + value.description + '</td>' +
        '</tr>';
    });
    $('#parameters').html(parameterData);
  }
</script>
</div>
</div>
