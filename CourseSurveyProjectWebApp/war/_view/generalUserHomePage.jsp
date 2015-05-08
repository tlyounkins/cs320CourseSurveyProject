<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
	<head>
		<title>Student Home</title>
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
             <!--   <h2 class = "label" > Welcome, ${user}</h2> -->
              
	</div>
	<p>
		<a href="${pageContext.servletContext.contextPath}/completeAsurvey">Complete a Survey</a>
	</div>
	</body>
</html>
