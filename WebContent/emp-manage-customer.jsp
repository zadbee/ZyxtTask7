<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

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

    <div id="wrapper">
    <!-- Template -->
		<jsp:include page="template-top-emp.jsp" />

        <!-- Page content -->
        <div id="page-content-wrapper">
            <div class="content-header">
                <h1>
                    <a id="menu-toggle" href="#" class="btn btn-default"><i class="icon-reorder"></i></a>
                   
                </h1>
            </div>
            <!-- Keep all page content within th
             e page-content inset div! -->
            <div class="page-content inset">
                <ol class="breadcrumb">
                    <li><a href="emp-customerlist.jsp">Home</a></li>
                    <li class="active">Transition Day</li>
                </ol>
                
                <jsp:include page="error-list.jsp"/>
                
                <div class="row">
                    <div class="col-md-12">
                        <p class="lead">Customer Account Details</p>
                        	<table class="table">
                        	<tbody>
                               	<tr>
                               		<td>Customer Id </td>
                               		<td>${customer.customer_id}</td>
                               	</tr>
                               	<tr>
                               		<td>Customer Name </td>
                               		<td>${customer.firstname}  ${customer.lastname }</td>
                               	</tr>
                               	<tr>
                               		<td>Customer Username </td>
									<td>${customer.username}</td>	
								</tr>
								<tr> 
									<td>Customer's Balance </td>
									<td>${customer.cash}</td>
								</tr>
							</tbody>
							</table> 
							
							<form method="post" action="emp_resetPwd.do">
							<div class="col-md-4">
							<button type="submit" class="btn btn-default btn-lg" name="reset-pwd" value="${customer.customer_id}" >Reset Password</button></form>
							</div>
							<form method="post" action="emp_depositCheck.do">
							<div class="col-md-4">
							<button type="submit" class="btn btn-default btn-lg" name="deposit-check" value="${customer.customer_id}" >Deposit Check</button></form>
							</div>
							<form method="post" action="emp_transHistory.do">
							<button type="submit" class="btn btn-default btn-lg"" name="transhistory" value="${customer.customer_id}" >Transaction History</button></form>
                    </div>
					
					<div class="col-md-12">
						
                    </div>
                    <!-- List of Funds -->
            
                </div>
            </div>
        </div>

    </div>

    <!-- JavaScript -->
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/bootstrap.js"></script>

    <!-- Custom JavaScript for the Menu Toggle -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("active");
    });
    </script>
</body>

</html>
