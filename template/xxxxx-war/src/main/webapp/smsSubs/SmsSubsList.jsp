
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="do" var="addURL">
  <c:param name="op" value="AddSmsSubs" />
</c:url>

<div class='pojo-section'>
<div class='pojo-section-header'>SMS Subs List</div>
<div class='pojo-section-body'>

<table border ='0' cellpadding="2" cellspacing="2" width="98%">
  <tr>
    <td valign='top' width='3%'>
      <a href='<c:out value="${addURL}" />'>
        <button class='btnAdd'>Add</button>
      </a>
    </td>

    <td valign='top' width='40%'></td>
  </tr>
</table>

<table border ='0' cellpadding="2" cellspacing="2" width="98%">
  <tr>
    <td valign='top' width='30%'>

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
    <th valign='top' width='10%'>Action</th>
    <th valign='top' width='20%'>First Name</th>
    <th valign='top' width='20%'>Last Name</th>
    <th valign='top' width='30%'>Sms</th>
  </tr>

  <c:forEach items="${userSmss}" var="item" varStatus="loop">
    <tr>
      <c:url value="do" var="editURL">
        <c:param name="op" value="EditSmsSubs" />
        <c:param name="id" value="${item.id}" />
      </c:url>
      <c:url value="do" var="deleteURL">
        <c:param name="op" value="DeleteSmsSubs" />
        <c:param name="id" value="${item.id}" />
      </c:url>
      <c:url value="do" var="viewURL">
        <c:param name="op" value="ViewSmsSubs" />
        <c:param name="id" value="${item.id}" />
      </c:url>


      <td>
        <a href='<c:out value="${editURL}" />'><button class='btnEditIcon'>Edit SmsSubs</button></a>
        <a href='<c:out value="${deleteURL}" />'><button class='btnDeleteIcon'>Delete SmsSubs</button></a>
        <a href='<c:out value="${viewURL}" />'><button class='btnViewIcon'>View SmsSubs</button></a>
      </td>

    <td>${item.firstName}</td>
    <td>${item.lastName}</td>
    <td>${item.sms}</td>

    </tr>
  </c:forEach>
</table>

</div>
</div>

