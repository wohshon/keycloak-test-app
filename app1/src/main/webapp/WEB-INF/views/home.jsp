<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
</head>
<body>
<a href="/api/service1">service1</a>
<div>
    ${REQ_ACTION}
</div>
--------------------------------------------------------------
<div>
    <p>Hello ${REQ_PRINCIPAL}</p>
    <a href="/logout.page">logout</a>
</div>
</body>
</html>