<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String httpRoot = System.getProperty("com.sw-builder.sync.app.http-root");
%>

<div class='pojo-section'>
      <div class='pojo-section-header'>Home Page</div>
      <div class='pojo-section-body'>
	<H3>Your Apps</H3><br>

	<table class="pojo_tablestyle">
        <tr>
           <th width="33%"></th>
           <th width="33%"></th>
           <th width="33%"></th>
        </tr>

	<tr>

___JSP_HOME_URLS___

	</tr>
	</table>

<H3>Link To App Store And Setup Guides</H3><br>

<style>
  .swb-header-link {
    display: block;
    background: #1e3a8a;
    color: white;
    text-decoration: none;
    border-radius: 8px;
    padding: 1.2rem 1.5rem;
    max-width: 520px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.15);
    transition: background 0.2s ease;
  }
  .swb-header-link:hover {
    background: #1e40af;
  }
  .swb-header-content {
    display: flex;
    align-items: center;
    gap: 1.4rem;
    flex-wrap: wrap;
  }
  .swb-header-content img {
    height: 72px;
    width: auto;
    flex-shrink: 0;
  }
  .swb-header-text h1 {
    margin: 0 0 0.15rem 0;
    font-size: 1.7rem;
    font-weight: bold;
    color: white;
    line-height: 1.2;
  }
  .swb-header-text p {
    margin: 0;
    font-size: 1.05rem;
    color: #e0e7ff;
  }
</style>

<a href="https://sw-builder.com" target="_blank" class="swb-header-link">
  <div class="swb-header-content">
    <img src="https://sw-builder.com/logo.png" alt="SW Builder Logo">
    <div class="swb-header-text">
      <h1>sw-builder.com</h1>
      <p>Practical Software Factories</p>
    </div>
  </div>
</a>

      </div>
</div>
