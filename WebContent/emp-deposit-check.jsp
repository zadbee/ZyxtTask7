<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="databeans.Customer" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Carnegie Financial Services</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Add custom CSS here -->
    <link href="css/simple-sidebar.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet">

</head>
<body>

<div id="container">
    <jsp:include page="template-top-emp.jsp" />
    <jsp:include page="template-top-emp-customer-list.jsp" />
 
        
        <div class="content">
            <h2> Deposit Check </h2>
            
            <jsp:include page="error-list.jsp" />
<%
Customer customer = (Customer) session.getAttribute("cus");
if(customer == null){
    out.print("You need to select a customer first");
    return;
}
%>
			<form method="post" action="emp_depositCheck.do">
				<table>
					<tr>
						<td> UserName: </td>
						<td><input type="text" name="userName" readonly value="<%=customer.getUsername()%>"/></td>
					</tr>
					<tr>
						<td> Deposit Amount: </td>
						<td><input type="text" name="deposit" value=""/></td>
						<td>Should no less than 1 dollar and less than 10,000,000</td>
					</tr>
					<tr>  
						<td colspan="2" align="center">
							<input type="submit" name="button" value="Deposit Check"/>
						</td>
					</tr>
				</table>
			</form>
        </div>
        <jsp:include page="template-footer.jsp" />
    </div>
</div>
</body>
</html>