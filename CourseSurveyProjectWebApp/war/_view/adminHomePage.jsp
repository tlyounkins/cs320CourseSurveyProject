<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Admin Home</title>
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
              <h2 class = "label" > Welcome, ${user}</h2>
              <c:if test="${! empty errorMessage}">
				      <div class="error">${errorMessage}</div>
				    </c:if>
  				 <form action="${pageContext.servletContext.contextPath}/adminHomePage" method="post">
            		    <table>
            		        <tr>
            		            <td class="label">Add Course:</td>
            		            <td><input type="text" name="courseID" size="16" value="${courseID}" /></td>
            		            <td class="label">Add Section:</td>
            		            <td><input type="text" name="sectionID" size="16" value="${sectionID}" /></td>
            		        </tr>
            		        <tr>
            		            <td class="label">Add User:</td>
            		            <td><input type="text" name="accountName" size="16" value="${accountName}" /></td>
    		        			<td class = "label">Permissions:</td>
								<td> <select name="permissions"> 
    							<option value="professor">Professor</option>
    							<option value="student">Student</option>
								</select> </td>
							</tr>
            		    </table>
            	        <input name="Submit" type="submit" value="Update">
            	    </form>
		
		<a href="${pageContext.servletContext.contextPath}/surveyCreation">Survey Creation Page</a>
		</p>
	</body>
</html>
