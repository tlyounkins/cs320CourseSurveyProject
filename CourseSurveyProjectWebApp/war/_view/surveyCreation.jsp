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
      text-align: right;
    }
    </style>
  </head>
  	<body>
        <div id="wrapper">
            <header><h1>Web Survey</h1></header>
             <div id="content">
   		<h2>Create a Survey</h2>
	 	<c:if test="${! empty errorMessage}">
     	<div class="error">${errorMessage}</div>
   		</c:if>
		 <form method="post">
		        <table>
    		        <tr>
    		          <td class="label">Course Id:</td>
    		          <td><input type="text" name="CourseId" required size="16" value="${courseID}" /></td>
    		        </tr>
    		        <tr>
    		          <td class="label">Section Id:</td>
    		          <td><input type="text" name="SectionId" required size="16" value="${sectionID}" /></td>
    		        </tr>
    		        <tr>
    		          <td class="label">Survey Name:</td>
    		          <td><input type="text" name="SurveyName" required size="16" value="${surveyName}" /></td>
    		        </tr>
    		        <tr>
    		        	<td class = "label">Question 1 Type:</td>
						<td> <select name="Question1_type"> 
    					<option value="Freeform">FreeForm</option>
    					<option value="Multiple_Choice">Multiple Choice</option>
						</select> </td>
					</tr>
					<tr> 
					<td class = "label" > Question 1</td>
					<td> <textarea rows="5" cols="30" name="Question1"></textarea> </td>
					</tr>
					<tr>
    		        	<td class = "label">Question 2 Type:</td>
						<td> <select name="Question2_type"> 
    					<option value="Freeform">FreeForm</option>
    					<option value="Multiple_Choice">Multiple Choice</option>
						</select> </td>
					</tr>
					<tr> 
					<td class = "label" > Question 2</td>
					<td> <textarea rows="5" cols="30" name="Question2"></textarea> </td>
					</tr>
					<tr>
    		        	<td class = "label">Question 3 Type:</td>
						<td> <select name="Question3_type"> 
    					<option value="Freeform">FreeForm</option>
    					<option value="Multiple_Choice">Multiple Choice</option>
						</select> </td>
					</tr>
					<tr> 
					<td class = "label" > Question 3</td>
					<td> <textarea rows="5" cols="30" name="Question3"></textarea> </td>
					</tr>
    		    </table>
    		    <input name="Submit" type="submit" value="Submit Survey">
	     </form>
				<!--  <button type= "button" onclick= "alert('SurveyCreated')"><a href="${pageContext.servletContext.contextPath}/adminHomePage">Submit Survey</a></button> -->
			</p>
</body>
</html>
