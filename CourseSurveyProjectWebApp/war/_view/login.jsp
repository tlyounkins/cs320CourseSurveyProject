<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
  <head>
    <title>Login</title>
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
    <c:if test="${! empty errorMessage}">
      <div class="error">${errorMessage}</div>
    </c:if>
    
	<c:if test="${empty create}">
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
		        <c:if test="${create.passwordMatching}">
		    		<div>
		    			*Password mismatch - please make sure your password is typed correctly
		    		</div>
		    	</c:if>
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
	   </c:if>
	   <c:if test="${! empty create}">
	    	<div>
	    		Your account has been created
	    	</div>
	   </c:if>
  </body>
</html>