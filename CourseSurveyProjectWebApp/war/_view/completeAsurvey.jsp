<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
	<head>
		<title>Survey Creation</title>
		<link rel="stylesheet" type="text/css" href="/survey/_view/web_survey.css"  >
    <style type="text/css">
    .error {
      color: red;
    }

    td.label {
      text-align: left;
    }
    </style>
  </head>
  	<body>
        <div id="wrapper">
            <header><h1>Web Survey</h1></header>
             <div id="content">
   		<h2>Complete a Survey</h2>
	 	<c:if test="${! empty errorMessage}">
     	<div class="error">${errorMessage}</div>
   		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/generalUserHomePage" method="post">
			<table>
				<tr>
					<td class = "label" > Question 1: ${Question1}</td>
					<td> <textarea rows="5" cols="30" name="Question2"></textarea> </td>
				</tr>
			</table>
		</form>
   		
   		
   		<p>
   		<button type= "button" onclick= "alert('Thank you for your submission')"><a href="${pageContext.servletContext.contextPath}/generalUserHomePage">Submit Survey</a></button>
   		</p>
	</body>
</html>