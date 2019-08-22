<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

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

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../css/entrantRegistration.css">
<style>
<
style
>
@media screen and (max-width: 455px) {
	.h3 {
		font-size: 16px;
	}
}
</style>

</head>
<body style="background: #e6e6e6; color: black;">

	<div class="w3-sidebar w3-light-grey w3-card-4 w3-animate-left"
		style="width: 200px" id="mySidebar">
		<div class="w3-bar w3-dark-grey">
			<span class="w3-bar-item w3-padding-16"><spring:message
					code='home.content' /><br>${pageContext.request.userPrincipal.name}</span>
			<button onclick="w3_close()"
				class="w3-bar-item w3-button w3-right w3-padding-16"
				title="close Sidebar">&times;</button>
		</div>
		<div class="w3-bar-block">
			<a class="w3-bar-item w3-button w3-green" href="/home"><spring:message
					code='home.home' /></a> <a class="w3-bar-item w3-button"
				href="/statement"><spring:message code='home.statement' /></a>

			<security:authorize access="hasRole('ROLE_ADMIN')">
				<a class="w3-bar-item w3-button" href="/registeredEntrants"><spring:message
						code='home.entrant_register' /></a>
			</security:authorize>

			<div class="w3-dropdown-hover">
				<a class="w3-button" href="javascript:void(0)"><spring:message
						code='home.select_faculty' /><i class="fa fa-caret-down"></i></a>
				<div class="w3-dropdown-content w3-bar-block w3-card-4">
					<a class="w3-bar-item w3-button"
						href="${contextPath}/enrolledEntrants?facId=1"><spring:message
							code='home.economical' /></a> <a class="w3-bar-item w3-button"
						href="${contextPath}/enrolledEntrants?facId=2"><spring:message
							code='home.biological' /></a> <a class="w3-bar-item w3-button"
						href="${contextPath}/enrolledEntrants?facId=3"><spring:message
							code='home.languages' /></a> <a class="w3-bar-item w3-button"
						href="${contextPath}/enrolledEntrants?facId=4"><spring:message
							code='home.geographical' /></a>
				</div>
			</div>
		</div>
	</div>

	<div id="main" style="margin-left: 200px">

		<div class="w3-container w3-display-container">
			<span title="open Sidebar" style="display: none" id="openNav"
				class="w3-button w3-transparent w3-display-topleft w3-xlarge"
				onclick="w3_open()">&#9776;</span>

		</div>

		<div class="container" style="margin-left: 100px">

			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<form id="logoutForm" method="POST" action="${contextPath}/logout">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>

				<button type="button" class="btn btn-success"
					onclick="document.forms['logoutForm'].submit()"
					style="position: absolute; right: 20px; top: 10px; font-size: 15px">
					<spring:message code='home.logout' />
				</button>

			</c:if>

			<div
				style="width: 60%; background-color: #4CAF50; color: #fff; margin-top: 10%; padding: 5px; margin-left: 150px">
				<h2><spring:message code='er.marks' /></h2>
			</div>
			<div class="form-container">
				<form:form method="POST" action="${contextPath}/entrantRegistration"
					enctype="multipart/form-data">
					<div class="row">
						<div class="col-25">
							<label for="image"><spring:message code='er.photo' /></label>
						</div>
						<div class="col-75">
							<input type="file" id="image" name="image"
								placeholder="<spring:message code='er.select' />">
						</div>
					</div>

					<c:forEach items="${entrant.faculty.requiredSubjects}"
						var="currentSubject">

						<c:if test="${currentSubject == 'MATH'}">

							<div class="row">
								<div class="col-25">
									<label for="marks"><spring:message code='er.math' /></label>
								</div>
								<div class="col-75">
									<input type="number" id="marks" name="marks"
										placeholder="<spring:message code='er.yourMark' />">
								</div>
							</div>
						</c:if>



						<c:if test="${currentSubject == 'UKRAINIAN_LANGUAGE'}">
							<div class="row">
								<div class="col-25">
									<label for="marks"><spring:message code='er.ukr' /></label>
								</div>
								<div class="col-75">
									<input type="number" id="marks" name="marks"
										placeholder="<spring:message code='er.yourMark' />">
								</div>
							</div>
						</c:if>

						<c:if test="${currentSubject == 'FOREIGN_LANGUAGE'}">
							<div class="row">
								<div class="col-25">
									<label for="marks"><spring:message code='er.foreign' /></label>
								</div>
								<div class="col-75">
									<input type="number" id="marks" name="marks"
										placeholder="<spring:message code='er.yourMark' />">
								</div>
							</div>
						</c:if>

						<c:if test="${currentSubject == 'HISTORY'}">
							<div class="row">
								<div class="col-25">
									<label for="marks"><spring:message code='er.history' /></label>
								</div>
								<div class="col-75">
									<input type="number" id="marks" name="marks"
										placeholder="<spring:message code='er.yourMark' />">
								</div>
							</div>
						</c:if>

						<c:if test="${currentSubject == 'GEOGRAPHY'}">
							<div class="row">
								<div class="col-25">
									<label for="marks"><spring:message code='er.geography' /></label>
								</div>
								<div class="col-75">
									<input type="number" id="marks" name="marks"
										placeholder="<spring:message code='er.yourMark' />">
								</div>
							</div>
						</c:if>

						<c:if test="${currentSubject == 'CHEMISTRY'}">
							<div class="row">
								<div class="col-25">
									<label for="marks"><spring:message code='er.chemistry' /></label>
								</div>
								<div class="col-75">
									<input type="number" id="marks" name="marks"
										placeholder="<spring:message code='er.yourMark' />">
								</div>
							</div>
						</c:if>
						<c:if test="${currentSubject == 'PHYSICS'}">
							<div class="row">
								<div class="col-25">
									<label for="marks"><spring:message code='er.physics' /></label>
								</div>
								<div class="col-75">
									<input type="number" id="marks" name="marks"
										placeholder="<spring:message code='er.yourMark' />">
								</div>
							</div>
						</c:if>
					</c:forEach>
					<input type="hidden" name="facultyId" value="${entrant.faculty.id}">
					<input type="hidden" name="email"
						value="${entrant.user.email}">

						<div class="row">
							<input type="submit" value="<spring:message code='er.submit'/>" />
						</div> 
						<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form:form>
				</div>

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