<%@page import="databeans.Customer"%>
<%@page import="databeans.Fund"%>
<%@page import="java.util.*" %>
<%@page import="utility.Format" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

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
		<jsp:include page="template-top-cus.jsp" />
	
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
                    <li class="active">Buy Fund</li>
                </ol>
<%
Customer customer = (Customer) session.getAttribute("customer");
if(customer == null) {
    out.print("There is no customer information found in session!");
    return;
}
%>
<%
Fund fund = (Fund) session.getAttribute("fund");
if(fund == null){
    out.print("You need to select a fund first");
    return;
}
%>
                <div class="row">
                    <div class="col-md-12">
                        <p class="lead">Buy Fund</p>
                    </div>
                    <div class="col-md-5">
                        <div class="input-group">
                            <span class="input-group-addon">#</span>
                            <input type="text" class="form-control" placeholder="Fund Symbol" name="fund">
                        </div>
                    </div>
                    <div class="col-md-12">
                        <p class="lead"> </p>
                    </div>

                    <div class="col-md-5">
                        <div class="input-group">
                            <span class="input-group-addon">$</span>
                            <input type="text" class="form-control" placeholder="Amount (No more than your balance)" name="amount">
                                </div>
                    </div>
                    <div class="col-md-12">
                        <p class="lead"> </p>
                    </div>
                    <div class="col-md-2">
                        <p class="lead"> </p>
                    </div>
                    <div class="col-md-4">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default">Buy Now</button>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <p class="lead"></p>
                    </div>
                    <div class="col-md-12">
                        <p class="lead"></p>
                    </div>
                    <div class="col-md-12">
                        <p class="lead">Your Current Balance</p>
                        <button type="button" class="btn btn-default">
                            <span><%customer.getCash();%></span>
                        </button>
                    </div>
                    <div class="col-md-12">
                        <p class="lead"></p>
                    </div>
                    <div class="col-md-12">
                        <p class="lead"></p>
                    </div>
                    <div class="col-md-12">
                        <p class="lead">Your Fund List</p>
                    </div>
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                                <tr>
                                
                                    <th>#</th>
                                    <th>Fund Name</th>
                                    <th>Shares</th>
                                    <th>Bought Price</th>
                                    <th>Bought Date</th>
                                    
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>2</td>
                                    <td>3</td>
                                    <td>4</td>
                                    <td>5</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    
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
