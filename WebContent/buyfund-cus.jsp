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
                
                <div class="row">
                    <div class="col-md-12">
                        <p class="lead">Buy Fund</p>
                    </div>
                    <div class="col-md-5">
                        <div class="input-group">
                            <span class="input-group-addon">#</span>
                            <input type="text" class="form-control" placeholder="Fund Name">
                        </div>
                    </div>
                    <div class="col-md-12">
                        <p class="lead"> </p>
                    </div>

                    <div class="col-md-5">
                        <div class="input-group">
                            <span class="input-group-addon">$</span>
                            <input type="text" class="form-control" placeholder="Amount (No more than your balance)">
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
                            <span>$ 102234.232</span>
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
                            <%
								ArrayList<Fund> fundList = new ArrayList<Fund>();
                            	fundList = request.getAttribute("funds");
                            	int i=1;
                            	for(Fund x: fundList){
                            %>
                                <tr>
                                    <td><%i++;%></td>
                                    <td><%x.getName();%></td>
                                    <td><%x.getShares();%></td>
                                    <td><%x.getPrice();%></td>
                                    <td><%x.getDate(); %></td>
                                    
                                </tr>
                              <%
                            	}
                              %>
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
