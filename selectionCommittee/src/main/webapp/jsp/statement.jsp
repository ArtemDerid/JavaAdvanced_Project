<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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

    <title>Registered Entrants</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
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
  <span class="w3-bar-item w3-padding-16">Content</span>
  <button onclick="w3_close()"
  class="w3-bar-item w3-button w3-right w3-padding-16" title="close Sidebar">&times;</button>
  </div>
   <div class="w3-bar-block">
  <a class="w3-bar-item w3-button w3-green" href="/home">Home</a>
  <a class="w3-bar-item w3-button" href="/statement">Statement</a>
  <a class="w3-bar-item w3-button" href="/registeredEntrants">See All Entrants</a>
  <div class="w3-dropdown-hover">
    <a class="w3-button" href="javascript:void(0)">Select Faculty<i class="fa fa-caret-down"></i></a>
    <div class="w3-dropdown-content w3-bar-block w3-card-4">
      <a class="w3-bar-item w3-button" href="${contextPath}/enrolledEntrants?facId=1">Economical</a>
      <a class="w3-bar-item w3-button" href="${contextPath}/enrolledEntrants?facId=2">Biological</a>
      <a class="w3-bar-item w3-button" href="${contextPath}/enrolledEntrants?facId=3">Languages</a>
      <a class="w3-bar-item w3-button" href="${contextPath}/enrolledEntrants?facId=4">Geographical</a>
    </div>
  </div>
  </div>
</div>

<div id="main" style="margin-left:200px">

<div class="w3-container w3-display-container">
  <span title="open Sidebar" style="display:none" id="openNav" class="w3-button w3-transparent w3-display-topleft w3-xlarge" onclick="w3_open()">&#9776;</span>
  
</div>

<div class="container" style="margin-left: 100px">

	  <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>

    </c:if>
    
				<h1 style="text-align:center">Statement</h1>
					<table class="w3-table-all w3-large">
					
						<tr>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Faculty</th>
							<th>Total mark</th>
						</tr>
						
						
						
						
						<c:forEach items="${entrants}" var="currentEntrant">
							<tr>
								<td>${currentEntrant.user.firstName}</td>
								<td>${currentEntrant.user.lastName}</td>
								<td>${currentEntrant.faculty.name}</td>
							    <td>${currentEntrant.totalMark}</>
							</tr>
						
						</c:forEach>
						
					</table>
	
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>