
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='pojo-section'>
<div class='pojo-section-header'>Schedule Event</div>
<div class='pojo-section-body'>
<div align="center">
<ul>
    <li><span class="strong">Comma (',')</span> can be used for a list of values, for example: '1,3,5'</li>
    <li><span class="strong">Dash ('-')</span> can be used for a range of values, for example: '1-5', which is equivalent to '1,2,3,4,5'</li>
    <li><span class="strong">Asterisk ('*')</span> will match all possible values.  An asterisk, for example, in the Minute field would match every minute.</li>
    <li><span class="strong">Slash ('/')</span> can be used for a 'step'.  For example, '*/4' in the Hour field would match every 4 hours.</li>
    <li>One value must be changed for a value schedule.  Example use '*/1' in the Second field for an every-second-schedule.</li>
</ul>
  <form id="formScheduleEvent" method="POST" action="do">
    <input type='hidden' id="opScheduleEvent" name="op" value="">
    <input type='hidden' name="id" value="${id}">

    <c:if test="${!empty msg}">
       <font class="pojo-msg-alert"><b>${msg}</b></font><br /><br />
    </c:if>

    <div class="pojo-section-body">
    <c:if test="${!empty secondError}">
       <font class="pojo-msg-alert"><b>${secondError}</b></font><br /><br />
    </c:if>
    Second: (0 - 59) <input type='text' name='second' value='${second}' size='30' maxlength='30'>
      <br /><br />
    </div>

    <div class="pojo-section-alternate">
    <c:if test="${!empty minuteError}">
       <font class="pojo-msg-alert"><b>${minuteError}</b></font><br /><br />
    </c:if>
    Minute: (0 - 59) <input type='text' name='minute' value='${minute}' size='30' maxlength='30'>
      <br /><br />
    </div>

    <div class="pojo-section-body">
    <c:if test="${!empty hourError}">
       <font class="pojo-msg-alert"><b>${hourError}</b></font><br /><br />
    </c:if>
    Hour: (0 - 23) <input type='text' name='hour' value='${hour}' size='30' maxlength='30'>
      <br /><br />
    </div>

    <div class="pojo-section-alternate">
    <c:if test="${!empty domError}">
       <font class="pojo-msg-alert"><b>${domError}</b></font><br /><br />
    </c:if>
    Day of Month: (1 - 31) <input type='text' name='dom' value='${dom}' size='30' maxlength='30'>
      <br /><br />
    </div>

    <div class="pojo-section-body">
    <c:if test="${!empty monthError}">
       <font class="pojo-msg-alert"><b>${monthError}</b></font><br /><br />
    </c:if>
    Month: (1 - 12) <input type='text' name='month' value='${month}' size='30' maxlength='30'>
      <br /><br />
    </div>

    <div class="pojo-section-alternate">
    <c:if test="${!empty dowError}">
       <font class="pojo-msg-alert"><b>${dowError}</b></font><br /><br />
    </c:if>
    Day of Week: (0 - 6, 0=Sunday) <input type='text' name='dow' value='${dow}' size='30' maxlength='30'>
      <br /><br />
    </div>

    <div class="pojo-section-body">
    <c:if test="${!empty yearError}">
       <font class="pojo-msg-alert"><b>${dowError}</b></font><br /><br />
    </c:if>
    Year: (four-digit) <input type='text' name='year' value='${year}' size='30' maxlength='30'>
      <br /><br />
    </div>


    <div class="pojo-section-alternate">
    <c:if test="${!empty enabledError}">
       <font class="pojo-msg-alert"><b>${enabledError}</b></font><br /><br />
    </c:if>
    Enabled: (Check to start schedule, Uncheck to stop) <input type='checkbox' name='enabled' value='true' ${enabledBox}>
      <br /><br />
    </div>



    <button class='btnSchedule' id="add" type="button" onclick='goScheduleEvent("${opAction}");'>Schedule</button>
    &nbsp;&nbsp;&nbsp;
    <button class='btnBack' id="back" type="button" onclick='goScheduleEvent("${opBack}");'>Back</button>

  </form>
</div>

<script>
  function goScheduleEvent(op)
  {
    $('#opScheduleEvent').val(op);
    $('#formScheduleEvent').submit();
  }
</script>
</div>
</div>
