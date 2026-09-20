
<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
String httpRoot = System.getProperty("com.sw-builder.sync.app.http-root");
%>

<c:set var="posting" value="${posting}"/>

<c:url value="do" var="addURL">
  <c:param name="op"   value="CommentAdd" />
  <c:param name="PostingId"   value="${posting.id}" />
</c:url>

<div class='pojo-section'>
<div class='pojo-section-header'>Member Comments to Post</div>
<div class='pojo-section-body'>

<table class="pojo_tablestyle">

<thead>
<tr>

  <th valign="top" width="5%">Date</th>
  <th valign="top" width="85%">Comment</th>
  <th valign="top" width="10%">Member</th>

</tr>
</thead>

<tbody>

<c:forEach items="${posting.comment}" var="item">
        
  <tr>

    <c:set var="member" value="${item.member}"/>

    <td> <fmt:formatDate pattern='${formatDateStr}' value='${item.commentDate}'/> </td>

    <td> <strong style="font-size: 17px;"> ${item.postingComment} </strong> </td>

    <td>
      <a href='<c:out value="${viewURL}" />'>
        <img src="<%= httpRoot %>/thumbnail/Member/Picture/${item.member.cloudId}-${item.member.pictureVersion}.jpg" alt="Member Picture">
      </a>
      </br>
      <strong style="font-size: 13px;"> ${item.member.name} </strong>
    </td>

  </tr>

</c:forEach>

</tbody>

</table>

        <p>
            <button class='btnBack' id="back" type="button" onclick='goPostingView("${opBack}");'>Back</button>
            &nbsp;&nbsp;&nbsp;
<a href='<c:out value="${addURL}" />'>
  <button class='btnAdd'>Add Comment</button>
</a>

        </p>
<script>

  function goPostingView(op)
  {
    $('#opPostingView').val(op);
    $('#idPostingView').submit();
  }

</script>

</div>
</div>
