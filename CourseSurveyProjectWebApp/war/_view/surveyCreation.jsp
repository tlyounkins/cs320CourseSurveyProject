<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
	<head>
		<title>Survey Creation</title>
	</head>

	<body>
		<header><h1>Create a survey</h1></header>
	 	<c:if test="${! empty errorMessage}">
     	<div class="error">${errorMessage}</div>
   		</c:if>
		 <form action="${pageContext.servletContext.contextPath}/surveyCreation" method="post">
		        <table>
    		        <tr>
    		          <td class="label">Course Id:</td>
    		          <td><input type="text" name="CourseId" size="16" value="${courseId}" /></td>
    		        </tr>
    		        <tr>
    		          <td class="label">Section Id:</td>
    		          <td><input type="text" name="SectionId" size="16" value="${sectionId}" /></td>
    		        </tr>
    		        <tr>
    		          <td class="label">Survey Name:</td>
    		          <td><input type="text" name="SurveyName" size="16" value="${surveyName}" /></td>
    		        </tr>
    		    </table>
	     </form>
	     <p>
		<button type= "button" onclick= "alert('SurveyCreated')"><a href="${pageContext.servletContext.contextPath}/adminHomePage">Submit Survey</a></button>
		</p>
	</body>
</html>