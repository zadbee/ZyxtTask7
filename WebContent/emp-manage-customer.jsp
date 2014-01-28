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
                    <li><a href="#">Home</a></li>
                    <li class="active">Transition Day</li>
                </ol>
                
                <jsp:include page="error-list.jsp"/>
                
                <div class="row">
                    <div class="col-md-12">
                    	<form method="post" action="emp-customerlist.do">
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
							<input type="submit" name="reset_custPwd_${customer.customer_id}" value="Reset Password"/>
							<input type="submit" name="transHistory_cust_${customer.customer_id}" value="View Transaction History"/>
							<input type="submit" name="depositCheck_cust_${cust.customer_id}" value="Deposit Check"/>
							</form>
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
