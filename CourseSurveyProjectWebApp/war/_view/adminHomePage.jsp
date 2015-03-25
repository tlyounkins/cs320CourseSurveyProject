<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Admin Home</title>
	</head>

	<body>
		Welcome, Admin!
		<p>
		<!--
		<a href="surveyCreation.jsp">Survey Creation Page</a>
		  -->
		<a href="${pageContext.servletContext.contextPath}/surveyCreation">Survey Creation Page</a>
	</body>
</html>
