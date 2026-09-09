<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String httpRoot = System.getProperty("com.sw-builder.sync.app.http-root");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class='pojo-section'>
<div class='pojo-section-header'>Post Picture</div>
<div class='pojo-section-body'>
<div align="left">

<img src="<%= httpRoot %>/picture/${postPath}" alt="${postTitle}" >

</div>
</div>
</div>

<div class='pojo-section'>
<div class='pojo-section-header'>Post Comment</div>
<div class='pojo-section-body'>
<div align="left">

${postComment}

</div>
</div>
</div>