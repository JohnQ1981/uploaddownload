<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Document Upload</title>
</head>
<body>
<form action="upload" method="post" enctype="multipart/form-data">
<pre>
id: <input type="text" name ="id"/>
Document: <input type="file" name ="document"/>
<input type="submit" name="submit" value="Upload"/>
</pre>
</form>
<hr/>
<table>
<tr>
<th>id:</th>
<th>filename:</th>
<th>link:</th>
</tr>
<c:forEach items="${documents}" var ="document">

<tr>
<td>${document.id}</td>
<td>${document.name}</td>
<td><a href="download?id=${document.id}">download</a><hr/></td>
<td><a href="deleteDocument?id=${document.id}">Delete Document</a><hr/></td>
</tr>
</c:forEach>

</table>
${msg}<br>
<br>
</body>
</html>