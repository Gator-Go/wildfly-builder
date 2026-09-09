<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String httpRoot = System.getProperty("com.sw-builder.sync.app.http-root");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class='pojo-section'>
<div class='pojo-section-header'>${postMemberName} Posted</div>
<div class='pojo-section-body'>
<div style="display: flex; align-items: center; gap: 12px;">
<img src="<%= httpRoot %>/thumbnail/Member/Picture/${postMemberThumb}" alt="${postMemberName}">
<strong style="font-size: 20px; line-height: 1.6;">
${postComment}
</strong>
</div>
</div>
</div>