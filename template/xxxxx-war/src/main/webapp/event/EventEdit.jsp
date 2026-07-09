
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
var Parameters = [];

function Parameter (deleteFlag, id, required, name, description) {
    this.deleteFlag = deleteFlag;
    this.id = id;
    this.required = required;
    this.name = name;
    this.description = description;
}
Parameter.prototype.updateTo = function (deleteFlag, id, required, name, description) {
    this.deleteFlag = deleteFlag;
    this.id = id;
    this.required = required;
    this.name = name;
    this.description = description;
}
</script>

<div class='pojo-section'>
<div class='pojo-section-header'>Edit Event Item</div>
<div class='pojo-section-body'>
<div align="center">
  <form id="formEditEvent" method="POST" action="do">
    <input type='hidden' id="opEditEvent" name="op" value="">
    <input type='hidden' name="id" value="${id}">
    <input type='hidden' id="nbrParam" name="nbrParam" value="${nbrParam}">

    <c:if test="${!empty msg}">
       <font class="pojo-msg-alert"><b>${msg}</b></font><br /><br />
    </c:if>

    <div class="pojo-section-body">
    Event ID: ${id}
      <br /><br />
    </div>

    <div class="pojo-section-alternate">
    <c:if test="${!empty nameError}">
       <font class="pojo-msg-alert"><b>${nameError}</b></font><br /><br />
    </c:if>
    Name: <input type='text' name='name' value='${name}' size='30' maxlength='30'>
      <br /><br />
    </div>

    <div class="pojo-section-body">
    <c:if test="${!empty descriptionError}">
       <font class="pojo-msg-alert"><b>${descriptionError}</b></font><br /><br />
    </c:if>
    Description: <input type='text' name='description' value='${description}' size='100' maxlength='200'>
      <br /><br />
    </div>

    <c:forEach items="${parameterFmts}" var="item">
       <script>Parameters.push(new Parameter("${item.deleteFlag}","${item.id}","${item.required}",
                                             "${item.name}","${item.description}"));</script>
    </c:forEach>

    <font class="Verdana" size="+1">Parameters</font>
    <br />

    <table id="listtable" cellpadding="2" cellspacing="2" width="98%">
    <thead>
      <tr class="pojo-section-alternate">
      <th valign='top' width='5%'>Delete</th>
      <th valign='top' width='5%'>ID</th>
      <th valign='top' width='5%'>Required</th>
      <th valign='top' width='20%'>Name</th>
      <th valign='top' width='50%'>Description</th>
      </tr>
    </thead>
    <tbody id="parameters"></tbody>
    </table>

    <br />
    <strong>Parameters with IDs will be deleted during the Edit.</strong>
    <br /><br />

    <button class='btnEdit' id="edit" type="button" onclick='goEditEvent("${opAction}");'>Edit</button>
    &nbsp;&nbsp;&nbsp;
    <button class='btnBack' id="back" type="button" onclick='goEditEvent("${opBack}");'>Back</button>
    &nbsp;&nbsp;&nbsp;
    <button class='btnAdd' id="addParameter" type="button" onclick='goAddParameter();'>Add Parameter</button>

  </form>
</div>

<script>
  showParameters();

  function goEditEvent(op)
  {
    $('#opEditEvent').val(op);
    $('#formEditEvent').submit();
  }

  function goAddParameter()
  {
    var nbrParm = $( '#nbrParam' ).val();
    var aNbrParm = parseInt(nbrParm) + 1;
    $( '#nbrParam' ).val(aNbrParm.toString());
    Parameters.push(new Parameter("false","","","",""));
    showParameters();
  }

  function goDeleteParameter(idx)
  {
    var idId = "#id" + idx;
    if ( !($( idId ).val().trim() === "")) {
      goUpdateParameter(idx);
      return;
    }

    var nbrParm = $( '#nbrParam' ).val();
    var aNbrParm = parseInt(nbrParm) - 1;
    $( '#nbrParam' ).val(aNbrParm.toString());
    Parameters.splice(idx, 1);
    showParameters();
  }

  function goUpdateParameter(idx)
  {
    requiredFlag = "false";
    var requiredId = "#required" + idx;
    if ($( requiredId ).is(':checked'))
      requiredFlag = "true";

    deleteFlag = "false";
    var deleteId = "#deleteFlag" + idx;
    if ($( deleteId ).is(':checked'))
      deleteFlag = "true";

    var idId = "#id" + idx;
    var nameId = "#name" + idx;
    var descriptionId = "#description" + idx;

    Parameters[idx].updateTo(deleteFlag,$( idId ).val(),requiredFlag,$( nameId ).val(),$( descriptionId ).val());
    showParameters();
  }

  function showParameters() {
    var parameterData = '';
    $.each(Parameters, function( index, value)
    {
      var deleteFlagIndex = "deleteFlag" + index;
      var idIndex = "id" + index;
      var requiredIndex = "required" + index;
      var nameIndex = "name" + index;
      var descriptionIndex = "description" + index;

      var requiredFlag = "";
      if (value.required == "true")
        requiredFlag = "checked";

      var deleteFlag = "";
      if (value.deleteFlag == "true")
        deleteFlag = "checked";

      parameterData = parameterData +
        '<tr>' +

        '<td align="center"><input onchange="javascript:goDeleteParameter(\'' + index +
             '\')" type="checkbox" id="' + deleteFlagIndex + '" name="' + deleteFlagIndex + '" value="true" ' +
             deleteFlag + '></td>' +

        '<td align="center"><input type="hidden" id="' + idIndex + '" name="' + idIndex + '" value="' +
             value.id + '">' + value.id + '</td>' +

        '<td align="center"><input onchange="javascript:goUpdateParameter(\'' + index +
             '\')" type="checkbox" id="' + requiredIndex + '" name="' + requiredIndex + '" value="true" ' +
             requiredFlag + '></td>' +

        '<td align="center"><input onchange="javascript:goUpdateParameter(\'' + index +
             '\')" type="text" id="' + nameIndex + '" name="' + nameIndex + '" value="' +
             value.name + '" size="20" maxlength="30"></td>' +

        '<td align="center"><input onchange="javascript:goUpdateParameter(\'' + index +
             '\')" type="text" id="' + descriptionIndex + '"  name="' + descriptionIndex + '" value="' +
             value.description + '" size="100" maxlength="200"></td>' +
        '</tr>';
    });
    $('#parameters').html(parameterData);
  }

</script>
</div>
</div>
