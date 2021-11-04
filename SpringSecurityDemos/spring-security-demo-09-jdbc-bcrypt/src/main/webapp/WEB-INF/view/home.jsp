<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
	<title>luv2code Company Home Page</title>
</head>

<body>
	<h2>luv2code Company Home Page</h2>
	<hr> 
	
	<p>
	Welcome to the Luv2cvode Company Homepage, Woot!!
	</p>

	<hr>
	
	<!-- display user name and role -->
	
	<p>
		User: <security:authentication property="principal.username" />
		<br><br>
		Role(s): <security:authentication property="principal.authorities"/>
	</p>
		
	<security:authorize access="hasRole('MANAGER')">
	
	<!--  Add a lionk to point to /leaders .. for Managers -->
	<p>
		<a href="${pageContext.request.contextPath}/leaders">LeaderShip Meeting</a>
		(Only For Managers)
	</p>
	
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
	
	<!-- Add a link to point to /admin -->
	<p>
		<a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
		(Only For Admins)
	</p>
	</security:authorize>
	
	<hr>

	<!--  add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout"/>
	</form:form>
</body>

</html>