
<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
String httpRoot = System.getProperty("com.sw-builder.sync.app.http-root");
%>

___LOCS_DEFINITION_LOC___
___LOCS_DEFINITION_CURRENT_LOC___

<c:url value="do" var="listURL">
  <c:param name="op"   value="YyyyyList" />
</c:url>
<c:url value="do" var="searchURL">
  <c:param name="op"   value="YyyyySearch" />
</c:url>

<div class='pojo-section'>
<div class='pojo-section-header'>DDDDD Search Result List</div>
<div class='pojo-section-body'>

<p>
   <label style="color: green;width: 100%;text-align: left;">${msg}</label> 
</p>

<a href='<c:out value="${listURL}" />'>
  <button class='btnBack'>Back</button>
</a>

&nbsp;&nbsp;

<a href='<c:out value="${searchURL}" />'>
  <button class='btnSearch'>Search</button>
</a>

<table class="pojo_tablestyle">

<thead>
<tr>
  <th valign="top" width="10%">Action</th>
___JSP_LIST_HEADING___
</tr>

</thead>

<tbody>

<c:forEach items="${yyyyys}" var="item">

___ITEM_PUSH_LOC___
___ITEM_PUSH_CURRENT_LOC___

      <tr>
        <c:url value="do" var="viewURL">
           <c:param name="op"   value="YyyyyView" />
           <c:param name="id"   value="${item.id}" />
        </c:url>
        <c:url value="do" var="editURL">
           <c:param name="op"   value="YyyyyEdit" />
           <c:param name="id"   value="${item.id}" />
        </c:url>
        <c:url value="do" var="deleteURL">
           <c:param name="op"   value="YyyyyDelete" />
           <c:param name="id"   value="${item.id}" />
        </c:url>
___JSP_LIST_URL_ONE2MANY_PARENT_ALERT___
      <td>
          <a href='<c:out value="${viewURL}" />'><button class='btnViewIcon'>View</button></a>
          <a href='<c:out value="${editURL}" />'><button class='btnEditIcon'>Edit</button></a>
          <c:if test="${!empty admin and item.deleteFlag}">
              <a href='<c:out value="${deleteURL}" />'><button class='btnDeleteIcon'>Un-Delete</button></a>
          </c:if>
          <c:if test="${!empty admin and !item.deleteFlag}">
              <a href='<c:out value="${deleteURL}" />'><button class='btnDeleteIcon'>Delete</button></a>
          </c:if>
      </td>
___JSP_LIST_BODY___
	</tr>

</c:forEach>

</tbody>

</table>

<script>
___GMAP_SCRIPT_LOC___
___GMAP_SCRIPT_CURRENT_LOC___
</script>

</div>
</div>
