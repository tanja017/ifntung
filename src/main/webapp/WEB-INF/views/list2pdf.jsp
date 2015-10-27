<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

Wares list
<table border=1 width="100%">
<tr>
<th>Id</th><th>Name</th>
</tr>
<c:forEach var="contact" items="${ResultMap}" varStatus="status">
	<tr>
		<td>${contact.value.id}</td>
		<td>${contact.value.name}</td>
		<!-- td>${contact.value.Country}</td>
		<td>${contact.value.City}</td>
		<td>${contact.value.Email}</td>
		<td>${contact.value.Phone}</td-->
	</tr>
</c:forEach>
</table>