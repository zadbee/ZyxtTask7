<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="error" items="${errors}">
	<div style="font-size:Large; color:Red; "> ${error} </div>
</c:forEach>

