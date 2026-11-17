<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String httpRoot = System.getProperty("com.sw-builder.sync.app.http-root");
%>

<section class="section">
  <h2 class="section-header">Home Page</h2>
  <div class="pojo-section-body">
    <h3>Your Apps</h3>
    <div class="app-grid">
___JSP_HOME_URLS___
    </div>

    <h3 style="margin-top:2rem;">Software Builder Home</h3>
    <a href="https://sw-builder.com" target="_blank" class="card" style="display:flex; align-items:center; gap:1rem; max-width:520px; text-decoration:none;">
      <img src="https://sw-builder.com/logo.png" alt="SW Builder Logo" style="height:64px; width:auto;">
      <div>
        <strong style="font-size:1.2rem;">Software Builder Factories</strong><br>
        <span style="color:#64748b;">One options set. Two factories. Apps that can sync.</span>
      </div>
    </a>
  </div>
</section>
