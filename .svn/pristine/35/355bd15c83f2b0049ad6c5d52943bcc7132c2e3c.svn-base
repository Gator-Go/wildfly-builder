<%@ page import="ppp.ppp.ppp.report.ConstRpt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String rptRoot = System.getProperty("com.sw-builder.sync.app.http-root");
String alertUser = System.getProperty("com.sw-builder.sync.app.alert-user");
String alertPassword = System.getProperty("com.sw-builder.sync.app.alert-password");
String rptDir = System.getProperty("com.sw-builder.sync.app.report.dir");
%>

<style type="text/css">
.loadingWrapper {
  position:absolute;
  top:50%;
  left:40%;
}
</style>

<div class='pojo-section'>
<div class='pojo-section-header'>Generate ${reportName}</div>
<div class='pojo-section-body'>
    <form id="formGenReport" name="Report" action="do" method="POST">
        <div id="rptMsg" align='center'></div>
        <input type='hidden' id="rptFile" name="rptFile" value="">
        <input type='hidden' id="opGenReport" name='<%= ConstRpt.OP %>' value="">
        <input type='hidden' name='<%= ConstRpt.ID %>' value="${id}">
        <input type="hidden" name="reportJrxml" value="${reportFileName}" />

        <table>
            <tr>
               <th width="13%"></th>
               <th width="20%"></th>
               <th width="67%"></th>
            </tr>

            <tr>
              <td width="13%">
                <button class='btnBack' id="back" type="button" onclick='goBack("${opBack}");'>Back</button>
              </td>
              <td width="20%"></td>
              <td width="67%"></td>
            </tr>

            <tr>
              <td width="13%"></td>
              <td style="text-align: right;">
                <img border="0" src="resources/gfx/step_1.png" alt='Step 1' />
              </td>
              <td valign='center'>
                <strong>Specify how the report should be generated</strong>
              </td>
            </tr>

            <tr>
              <td width="13%"></td>
              <td style="text-align: right;"><label for="reportName">Name:</label></td>
              <td><input type="text" id="reportName" name="reportName" value="${reportName}" />
              <c:if test="${!empty reportNameError}">
                <brk/><label style="color: red; width: 100%;text-align: left;">${reportNameError}</label>
              </c:if>
              </td>
            </tr>

            <tr>
              <td width="13%"></td>
              <td style="text-align: right;">
                <label for="reportName">Output Format:</label></td>
              <td>
                <select id="mimeType" name="mimeType">
                <c:forEach items="${mimeTypes}" var="item">
                  <option value='<c:out value="${item.option}"/>' <c:out value="${item.selected}"/>><c:out value="${item.option}"/></option>
                </c:forEach>
                </select>
              </td>
            </tr>

            <tr><td></td></tr>
            <tr><td></td></tr>

            <tr>
              <td width="13%"></td>
              <td style="text-align: right;">
                <img border="0" src="resources/gfx/step_2.png" alt='Step 2' />
              </td>
              <td valign='center'>
                <strong>Specify the parameters and values to pass to the report.</strong>
              </td>
            </tr>

            <div id="imgLoader" class="loadingWrapper"></div>
            <% int y = 0; %>

            <c:forEach items="${rptParamOptions}" var="item">
              <% y++; %>
              <tr>
                <td width="13%"></td>
                <td style="text-align: right;">
                  <label for="reportName"><c:out value="${item.name}"/>:</label></td>
                <td>
                  ${item.instructStr}<br>
                  <input type="hidden" name="paramName.<%= y %>" value="${item.name}" />
                  <input type="hidden" name="paramDataType.<%= y %>" value="${item.dataType}" />
                  <input type="hidden" name="paramDateFormat.<%= y %>" value="${item.dateFormat}" />

                  <c:if test="${!empty item.valueFlag}">
                    <input type="text" id="paramName<%= y %>" name="paramValue.<%= y %>" value="" />
                  </c:if>

                  <c:if test="${!empty item.listFlag}">
                    <select name="paramValue.<%= y %>">
                    <c:forEach items="${item.listOptions}" var="item2">
                      <option value='<c:out value="${item2.option}"/>' <c:out value="${item2.selected}"/>><c:out value="${item2.option}"/></option>
                    </c:forEach>
                    </select>
                  </c:if>

                  <c:if test="${!empty item.selectFlag}">
                    <select name="paramValue.<%= y %>">
                    <c:forEach items="${item.listOptions}" var="item2">
                      <option value='<c:out value="${item2.option}"/>' <c:out value="${item2.selected}"/>><c:out value="${item2.option}"/></option>
                    </c:forEach>
                    </select>
                  </c:if>

                  <c:if test="${!empty item.dateFlag}">
                    <img onclick="goDatePicker('#paramName<%= y %>', '${item.dateFormat}');"border="0" src="resources/gfx/calendar.png" alt='Pick Date' />
                  </c:if>

                </td>
              </tr>
            </c:forEach>

            <input type="hidden" name="nbrParams" value="<%= y %>" />

            <tr><td></td></tr>
            <tr><td></td></tr>

            <tr>
              <td width="13%"></td>
              <td style="text-align: right;">
                <img border="0" src="resources/gfx/step_3.png" alt='Step 3' />
              </td>
              <td valign='center'>
                <strong>(Optional) Specify a sort option for the report</strong>
              </td>
            </tr>

            <tr>
              <td width="13%"></td>
              <td style="text-align: right;">
                <label for="sortOption">Sort Options:</label></td>
              <td>
                <select name="sortOption">
                <c:forEach items="${sortOptions}" var="item">
                  <option value='<c:out value="${item.option}"/>' <c:out value="${item.selected}"/>><c:out value="${item.option}"/></option>
                </c:forEach>
                </select>
              </td>
            </tr>

            <tr><td></td></tr>
            <tr><td></td></tr>

            <tr>
              <td width="13%"></td>
              <td style="text-align: right;">
                <img border="0" src="resources/gfx/step_4.png" alt='Step 4' />
              </td>
              <td valign='center'>
                <strong>Generate the report</strong>
              </td>
            </tr>

            <tr>
              <td width="13%"></td>
              <td></td>
              <td>
                <button class='btnGenerate' id="generate" type="button" onclick='goGenReport("${opAction}");'>Generate Report</button>
              </td>
            </tr>

            <tr><td></td></tr>
            <tr><td></td></tr>

            <tr>
              <td width="13%"></td>
              <td style="text-align: right;">
                <img border="0" src="resources/gfx/step_5.png" alt='Step 5' />
              </td>
              <td valign='center'>
                <strong>Download the report</strong>
              </td>
            </tr>

            <tr>
              <td width="13%"></td>
              <td></td>
              <td>
                <button class='btnDownload' id="download" type="button" onclick='goDownloadReport();'>Download Report</button>
              </td>
            </tr>
        </table>
    </form>

    <form id="formDownloadReport" action="do" method="POST">

        <input type='hidden' id="opDownloadReport" name='<%= ConstRpt.OP %>' value="FileDownload">
        <input type='hidden' id="opFileName" name='FileName' value="">
        <input type="hidden" id="opFilePath" name='FilePath' value="" />
    </form>

</div>
</div>

<script>
  function goBack(op)
  {
    $('#opGenReport').val(op);
    $('#formGenReport').submit();
  }

  function goGenReport(op)
  {
    var reportName = $('#reportName').val() + "." + $('#mimeType').val();

    $('#imgLoader').html('<img "border="0" src="resources/gfx/loadingAnimation.gif" />');

    $('#opGenReport').val(op);
    var reportURL = "<%= rptRoot %>/xxxxx/do";

    $.ajax({
      type: 'POST',
      url: reportURL,
      data: $("#formGenReport").serialize(),
      success: function (data) {
        $('#imgLoader').html('');
        $('#rptFile').val(reportName);
        $('#rptMsg').html("<font class='pojo-msg-alert'><b>" + reportName + " Generated Successfully</b></font>");
      },
      error: function(XMLHttpRequest, textStatus, errorThrown) {
        $('#rptFile').val('');
        $('#rptMsg').html("<font class='pojo-msg-alert'><b>Error Generating Report </b></font>");
      }
    }).always(function () {
      $('#imgLoader').html('');
    });

  }

  function goDownloadReport()
  {
    var reportName = $('#reportName').val() + "." + $('#mimeType').val();
    if ( !($('#rptFile').val() === reportName)) {
      $('#rptMsg').html("<font class='pojo-msg-alert'><b>Generate Report First</b></font>");
      return;
    }
    var filePath = "<%= rptDir %>" + reportName;

    $('#opFileName').val(reportName);
    $('#opFilePath').val(filePath);
    $('#formDownloadReport').submit();

  }

  function goDatePicker(y, fmt)
  {
    $(y).datepicker({dateFormat:fmt});
    $(y).focus();
  }
</script>
