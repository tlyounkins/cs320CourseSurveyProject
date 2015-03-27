<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
    <head>
        <title>Survey :: Login</title>
        <style type="text/css">
            .error {color: red;}
        
            td.label {
              text-align: right;
            }
        </style>
        <meta charset="utf-8">
        <link href="<c:url value='/survey/web_survey.css'/>"  rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Web forum login">
    </head>

    <body>
        <div id="wrapper">
            <header><h1>Web Survey</h1></header>
                <nav>
                     <ul>
                        <li><a href="login.jsp">Login</a></li>
                     </ul>
                </nav>
            <c:if test="${! empty errorMessage}">
              <div class="error">${errorMessage}</div>
            </c:if>
            
            <div id="content">
                <h2>Login</h2>
            	
            	    <form action="${pageContext.servletContext.contextPath}/accountCreation" method="post">
            	    	
            		    <table>
            		        <tr>
            		            <td class="label">User Name:</td>
            		            <td><input type="text" name="user" size="16" value="${userName}" /></td>
            		        </tr>
            		        <tr>
            		            <td class="label">Password:</td>
            		            <td><input type="text" name="password" size="16" value="${password}" /></td>
            		        </tr>
            		   
            		        <tr>
            		            <td class="label">Password:</td>
            		            <td><input type="text" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="password" onchange="form.passwordConfirm.pattern = this.value;" size="16" value="${password}" /></td>
            		        </tr>
            		        <tr>
            		            <td class="label">Confirm Password:</td>
            		            <td><input type="text" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="passwordConfirm" size="16" value="${passwordConfirm}" /></td>
            		        </tr>
            		    </table>
            		    
            	        <input name="Submit" type="submit" value="Create Institution">
            	    </form>
            
        
            </div>
        </div>
    </body>
</html>