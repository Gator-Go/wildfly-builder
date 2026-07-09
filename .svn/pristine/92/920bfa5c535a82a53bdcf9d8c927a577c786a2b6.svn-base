
<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
String httpRoot = System.getProperty("com.sw-builder.sync.app.http-root");
String role = (String)request.getAttribute("role");
if (role == null)
    role = "GUEST";
%>

<c:url value="do" var="backURL">
  <c:param name="op"   value="${opBack}" />
  <c:param name="id"   value="${id}" />
</c:url>

<c:if test="${ role != 'GUEST' }">
  <c:url value="do" var="addURL">
    <c:param name="op"   value="YyyyyAdd" />
  </c:url>
</c:if>

<div class='pojo-section'>
<div class='pojo-section-header'>${displayString} DDDDD List</div>
<div class='pojo-section-body'>

<p>
   <label style="color: green;width: 100%;text-align: left;">${msg}</label> 
</p>

<a href='<c:out value="${backURL}" />'>
  <button class='btnBack'>Back</button>
</a>

&nbsp;&nbsp;

<c:if test="${ role != 'GUEST' }">
  <a href='<c:out value="${addURL}" />'>
    <button class='btnAdd'>Add</button>
  </a>
</c:if>

<table class="pojo_tablestyle">

<thead>
<tr>
  <th valign="top" width="10%">Action</th>
___JSP_LIST_HEADING___
</tr>

</thead>

<tbody>

<c:forEach items="${yyyyys}" var="item">
        
      <tr>
        <c:url value="do" var="viewURL">
           <c:param name="op"   value="YyyyyView" />
           <c:param name="id"   value="${item.id}" />
        </c:url>
	<c:if test="${ role != 'GUEST' }">
          <c:url value="do" var="editURL">
            <c:param name="op"   value="YyyyyEdit" />
            <c:param name="id"   value="${item.id}" />
          </c:url>
          <c:url value="do" var="deleteURL">
            <c:param name="op"   value="YyyyyDelete" />
            <c:param name="id"   value="${item.id}" />
          </c:url>
	</c:if>
      <td>
          <a href='<c:out value="${viewURL}" />'><button class='btnViewIcon'>View</button></a>
	  <c:if test="${ role != 'GUEST' }">
            <a href='<c:out value="${editURL}" />'><button class='btnEditIcon'>Edit</button></a>
            <c:if test="${!empty admin}">
              <a href='<c:out value="${deleteURL}" />'><button class='btnDeleteIcon'>Delete</button></a>
            </c:if>
	  </c:if>
      </td>
___JSP_LIST_BODY___
	</tr>

</c:forEach>

</tbody>

</table>

</div>
</div>
