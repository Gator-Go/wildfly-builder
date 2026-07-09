
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="do" var="addURL">
  <c:param name="op" value="AddEvent" />
</c:url>
<c:url value="do" var="previousURL">
  <c:param name="op" value="AdminEvents" />
  <c:param name="subop" value="previousPage" />
</c:url>
<c:url value="do" var="nextURL">
  <c:param name="op" value="AdminEvents" />
  <c:param name="subop" value="nextPage" />
</c:url>

<div class='pojo-section'>
<div class='pojo-section-header'>Event List</div>
<div class='pojo-section-body'>
<table border ='0' cellpadding="2" cellspacing="2" width="98%">
  <tr>
    <td valign='top' width='3%'>
      <a href='<c:out value="${addURL}" />'>
        <button class='btnAdd'>Add</button>
      </a>
    </td>

    <c:if test="${!empty previousFlag}">
    <td valign='top' width='3%'>
      <a href='<c:out value="${previousURL}" />'>
        <button class='btnPrevious'>Prev</button>
      </a>
    </td>
    </c:if>

    <c:if test="${!empty nextFlag}">
    <td valign='top' width='3%'>
      <a href='<c:out value="${nextURL}" />'>
        <button class='btnNext'>Next</button>
      </a>
    </td>
    </c:if>

    <td valign='top' width='40%'></td>
  </tr>
</table>

<table border ='0' cellpadding="2" cellspacing="2" width="98%">
  <tr>
    <td valign='top' width='30%'>
      <font class="Verdana"><b>${pageInfo}</b></font>
    </td>

    <td valign='top' width='60%'>
      <c:if test="${!empty msg}">
      <font class="pojo-msg-alert"><b>${msg}</b></font><br><br>
      </c:if>
    </td>
  </tr>
</table>

<table id="listtable" class="pojo_tablestyle">
  <tr>
    <th valign='top' width='23%'>Action</th>
    <th valign='top' width='7%'>Sched</th>
    <th valign='top' width='20%'>Name</th>
    <th valign='top' width='50%'>Description</th>
  </tr>

  <c:forEach items="${adminEvents}" var="item">
    <tr>
      <c:url value="do" var="editURL">
        <c:param name="op" value="EditEvent" />
        <c:param name="id" value="${item.id}" />
      </c:url>
      <c:url value="do" var="deleteURL">
        <c:param name="op" value="DeleteEvent" />
        <c:param name="id" value="${item.id}" />
      </c:url>
      <c:url value="do" var="fireURL">
        <c:param name="op" value="FireEvent" />
        <c:param name="id" value="${item.id}" />
      </c:url>
      <c:url value="do" var="scheduleURL">
        <c:param name="op" value="ScheduleEvent" />
        <c:param name="id" value="${item.id}" />
      </c:url>

      <td>
        <a href='<c:out value="${editURL}" />'><button class='btnEditIcon'>Edit Event</button></a>
        <a href='<c:out value="${deleteURL}" />'><button class='btnDeleteIcon'>Delete Event</button></a>
        <a href='<c:out value="${fireURL}" />'><button class='btnFireIcon'>Fire Event</button></a>
        <a href='<c:out value="${scheduleURL}" />'><button class='btnScheduleIcon'>Schedule Event</button></a>
      </td>

      <td align='center' valign='center'>
        <c:if test="${!empty item.enabledFlag}">
          <img border="0" src="resources/gfx/icon_alert_debug_16.png" alt='Next' />
        </c:if>
      </td>

    <td>${item.name}</td>
    <td>${item.description}</td>

    </tr>
  </c:forEach>
</table>
</div>
</div>
