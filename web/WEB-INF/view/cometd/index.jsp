<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    
    <%--
    The reason to use a JSP is that it is very easy to obtain server-side configuration
    information (such as the contextPath) and pass it to the JavaScript environment on the client.
    --%>
    <script type="text/javascript">
        var config = {
            contextPath: '/prototype'
        };
    </script>
</head>
<body>

    <div id="body"></div>
	
	<script type="text/javascript" data-main="<c:url value="/js/application"/>" src="<c:url value="/js/require-jquery.js"/>"></script>
</body>
</html>
