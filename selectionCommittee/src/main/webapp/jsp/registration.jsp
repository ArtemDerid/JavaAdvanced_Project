
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
<title>Insert title here</title>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<!------ Include the above in your HEAD tag ---------->

<!-- All the files that are required -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href='http://fonts.googleapis.com/css?family=Varela+Round'
	rel='stylesheet' type='text/css'>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
<link rel="stylesheet" href="../css/login.css">
</head>
<body>

	<!-- REGISTRATION FORM -->
	<div class="text-center" style="padding: 50px 0">
		<div class="logo">register</div>
		<!-- Main Form -->
		<div class="login-form-1">
			<form:form method="POST" modelAttribute="userForm" id="register-form"
				class="text-left">
				<div class="login-form-main-message"></div>
				<div class="main-login-form">
					<div class="login-group">
						<spring:bind path="firstName">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<label for="reg_firstname" class="sr-only">First Name</label> 
								<form:input type="text" path="firstName" class="form-control" id="firstName"
									name="firstName" placeholder="first name"></form:input>
								<form:errors path="firstName"></form:errors>
							</div>
						</spring:bind>
						
						<spring:bind path="lastName">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="reg_lastname" class="sr-only">Last Name</label> 
							<form:input type="text" path="lastName" class="form-control" id="lastName" name="lastName"
								placeholder="last name"></form:input>
							<form:errors path="lastName"></form:errors>
						</div>
						</spring:bind>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="reg_password" class="sr-only">Password</label> 
							<form:input type="password" path="password" class="form-control" id="reg_password"
								name="reg_password" placeholder="password"></form:input>
							<form:errors path="password"></form:errors>
						</div>
						<div class="form-group  ${status.error ? 'has-error' : ''}">
							<label for="reg_password_confirm" class="sr-only">Password
								Confirm</label> 
							<form:input type="password" path="confirmPassword" class="form-control"
								id="reg_password_confirm" name="reg_password_confirm"
								placeholder="confirm password"></form:input>
							<form:errors path="confirmPassword"></form:errors>
						</div>

						<spring:bind path="email">
						<div class="form-group  ${status.error ? 'has-error' : ''}">
							<label for="reg_email" class="sr-only">Email</label> 
							<form:input type="text"  path="email" class="form-control" id="reg_email" name="reg_email"
								placeholder="email"></form:input>
							<form:errors path="email"></form:errors>
						</div>
						</spring:bind>

					</div>
					<button type="submit" class="login-button">
						<i class="fa fa-chevron-right"></i>
					</button>
				</div>
				<div class="etc-login-form">
					<p>
						already have an account? <a href="${contextPath}/login">login
							here</a>
					</p>
				</div>
			</form:form>
		</div>
		<!-- end:Main Form -->
	</div>


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>