<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Entrant Registration</title>

    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
@media screen and (max-width: 455px) {
    .h3 {
        font-size:16px;
    }
}
</style>
    
</head>
<body style="background:#e6e6e6;color:black;">

<div class="w3-sidebar w3-light-grey w3-card-4 w3-animate-left" style="width:200px" id="mySidebar">
  <div class="w3-bar w3-dark-grey">
  <span class="w3-bar-item w3-padding-16"><spring:message code='home.content'/><br>${pageContext.request.userPrincipal.name}</span>
  <button onclick="w3_close()"
  class="w3-bar-item w3-button w3-right w3-padding-16" title="close Sidebar">&times;</button>
  </div>
  <div class="w3-bar-block">
  <a class="w3-bar-item w3-button w3-green" href="/home"><spring:message code='home.home'/></a>  
  <a class="w3-bar-item w3-button" href="/statement"><spring:message code='home.statement'/></a>
 
  <security:authorize access="hasRole('ROLE_ADMIN')">
  <a class="w3-bar-item w3-button" href="/registeredEntrants"><spring:message code='home.entrant_register'/></a>
   </security:authorize>
   
  <div class="w3-dropdown-hover">
    <a class="w3-button" href="javascript:void(0)"><spring:message code='home.select_faculty'/><i class="fa fa-caret-down"></i></a>
    <div class="w3-dropdown-content w3-bar-block w3-card-4">
      <a class="w3-bar-item w3-button" href="${contextPath}/enrolledEntrants?facId=1"><spring:message code='home.economical'/></a>
      <a class="w3-bar-item w3-button" href="${contextPath}/enrolledEntrants?facId=2"><spring:message code='home.biological'/></a>
      <a class="w3-bar-item w3-button" href="${contextPath}/enrolledEntrants?facId=3"><spring:message code='home.languages'/></a>
      <a class="w3-bar-item w3-button" href="${contextPath}/enrolledEntrants?facId=4"><spring:message code='home.geographical'/></a>
    </div>
  </div>
  </div>
</div>

<div id="main" style="margin-left:200px">

<div class="w3-container w3-display-container">
  <span title="open Sidebar" style="display:none" id="openNav" class="w3-button w3-transparent w3-display-topleft w3-xlarge" onclick="w3_open()">&#9776;</span>
  
</div>

<div class="container" style="margin-left: 100px">

	 
<div class="navigation">
 <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
  
	<a class="button" onclick="document.forms['logoutForm'].submit()" style="cursor: pointer">
  	
  
  <div class="logout"><spring:message code='home.logout'/></div>

	</a>
	</c:if>
  
</div>
    
   <form:form method="POST" action="${contextPath}/entrantRegistration" enctype="multipart/form-data">
					<table>
						<tr>
							<th><spring:message code='er.marks'/></th>
						</tr>
						
						<tr>
							<td><spring:message code='er.photo'/></td>
							<td><input type="file" name="image" /></td>
						</tr>
						
						<c:forEach items="${entrant.faculty.requiredSubjects}" var="currentSubject">
						<c:if test="${currentSubject == 'MATH'}">
							<tr>
							
								<td><spring:message code='er.math'/></td>
								<td><input type="number" name="marks"></td>
							
							</tr>
						</c:if>
						
						<c:if test="${currentSubject == 'UKRAINIAN_LANGUAGE'}">
							<tr>
							
								<td><spring:message code='er.ukr'/></td>
								<td><input type="number" name="marks"></td>
							
							</tr>
						</c:if>
						
						<c:if test="${currentSubject == 'FOREIGN_LANGUAGE'}">
							<tr>
							
								<td><spring:message code='er.foreign'/></td>
								<td><input type="number" name="marks"></td>
							
							</tr>
						</c:if>
						
						<c:if test="${currentSubject == 'HISTORY'}">
							<tr>
							
								<td><spring:message code='er.history'/></td>
								<td><input type="number" name="marks"></td>
							
							</tr>
						</c:if>
						
						<c:if test="${currentSubject == 'GEOGRAPHY'}">
							<tr>
							
								<td><spring:message code='er.geography'/></td>
								<td><input type="number" name="marks"></td>
							
							</tr>
						</c:if>
						
						<c:if test="${currentSubject == 'CHEMISTRY'}">
							<tr>
							
								<td><spring:message code='er.chemistry'/></td>
								<td><input type="number" name="marks"></td>
							
							</tr>
						</c:if>
						<c:if test="${currentSubject == 'PHYSICS'}">
							<tr>
							
								<td><spring:message code='er.physics'/></td>
								<td><input type="number" name="marks"></td>
							
							</tr>
						</c:if>
						</c:forEach>
						<tr>
							<td><input type = "hidden" name="facultyId" value="${entrant.faculty.id}"></td>
							<td><input type="hidden" name="email" value="${entrant.user.email}"></td>
						</tr>
						
						<tr>
							<td><input type="submit" value="<spring:message code='er.submit'/>" /></td>
						</tr>
					</table>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form:form>
	
</div>

</div>

<script>
function w3_open() {
  document.getElementById("main").style.marginLeft = "180px";
  document.getElementById("mySidebar").style.width = "180px";
  document.getElementById("mySidebar").style.display = "block";
  document.getElementById("openNav").style.display = 'none';
}
function w3_close() {
  document.getElementById("main").style.marginLeft = "0";
  document.getElementById("mySidebar").style.display = "none";
  document.getElementById("openNav").style.display = "inline-block";
}
</script>


  
<!-- /container -->
</body>
</html>