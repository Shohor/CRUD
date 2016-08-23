<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
	<title>User Page</title>
	<style>
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:5px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
		.error {color: #ff0000};
	</style>
</head>
<body>
<h1>
	Add a User
</h1>

<c:url var="addAction" value="/add" ></c:url>

<form:form method="POST" action="${addAction}" commandName="user">
<table>
	<c:if test="${!empty user.name}">
	<tr>
		<td>
			<form:label path="id">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="name">
				<spring:message text="Name"/>
			</form:label>
		</td>
		<td>
			<form:input path="name" />
			<form:errors path="name" cssClass="error"/>
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="age">
				<spring:message text="Age"/>
			</form:label>
		</td>
		<td>
			<form:input path="age" />
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="isAdmin">
				<spring:message text="Admin"/>
			</form:label>
		</td>
		<td>
			<form:checkbox path="isAdmin" />
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="createdDate">
				<spring:message text="Created Date" />
			</form:label>
		</td>
		<td>
			<form:input path="createdDate" disabled="true"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty user.name}">
				<input type="submit"
					value="<spring:message text="Edit User"/>" />
			</c:if>
			<c:if test="${empty user.name}">
				<input type="submit" 
					value="<spring:message text="Add User"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</form:form>
<c:if test="${empty user.name}">
	<form:form method="POST" action="find" commandName="user" >
		<table>
			<tr>
				<td>
					<form:label path="name">
						<spring:message text="Name"/>
					</form:label>
				</td>
				<td>
					<form:input path="name" />
				</td>
				<td>
					<input type="submit"
							value="<spring:message text="Find"/>" />
				</td> 
			</tr>
		</table>
	</form:form>	
</c:if>
	<h2>List of Users</h2>	
	<table class ="tg">
		<tr>
			<td>ID</td><td>Name</td><td>Age</td><td>Admin</td><td>Created date</td><td>Edited</td><td>Deleted</td>
		</tr>
		<c:forEach var="user" items="${users}" varStatus="iter" >
		<c:if test="${user ne null}">
			<tr>
			<td><c:out value ="${user.id}"/></td>
			<td><c:out value ="${user.name}"/></td>
			<td><c:out value ="${user.age}"/></td>
			<td><c:out value ="${user.isAdmin}"/></td>
			<td><c:out value ="${user.createdDate}"/></td>
			<td><a href="<c:url value='/edit/${user.id}'/>" >Edit</a></td>
			<td><a href="<c:url value='/remove/${user.id}' />" >Delete</a></td>
			</tr>
		</c:if>
		</c:forEach>
	</table>
	<c:if test="${pageCount gt 0}">
		<c:forEach var="i" begin="1" end="${pageCount}">
			<a href="<c:url value='/${i}' />" >${i}</a>
		</c:forEach>
	</c:if>
	<br>Go back to <a href="<c:url value='/' />">List of All Users</a></br>
</body>
</html>