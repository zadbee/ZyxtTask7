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
                <div class="row">
                    <div class="col-md-12">
                        <p class="lead">Buy Fund</p>
                    </div>
                    <jsp:include page="error-list.jsp" />
                    
                    <form method="post" action="cus_buyFund.do">
	                    <div class="col-md-5">
	                        <div class="input-group">
	                            <span class="input-group-addon">#</span>
	                            <input type="text" class="form-control" placeholder="Fund Symbol" name="fundSymbol">
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
	                            <button type="submit" class="btn btn-default" name="button">Buy Now</button>
	                        </div>
	                    </div>
                    </form>
                    
                    <div class="col-md-12">
                        <p class="lead"></p>
                    </div>
                    <div class="col-md-12">
                        <p class="lead"></p>
                    </div>
                    <div class="col-md-12">
                        <p class="lead">Your Current Balance</p>
                        <button type="button" class="btn btn-default">
                            <span><%= "$" + " " + customer.getCash()%></span>
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
                    <% 	ArrayList<Integer> shares = (ArrayList<Integer>)request.getAttribute("shares");
                    	ArrayList<Long> prices = (ArrayList<Long>)request.getAttribute("prices");
                    	ArrayList<String> funds = (ArrayList<String>)request.getAttribute("funds");
                    	if(funds!=null && funds.size()!=0){
                    %>
                    
                    
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Fund Name</th>
                                    <th>Shares</th>
                                    <th>Current Prices</th>
                                </tr>
                            </thead>
                            <tbody>
                            <% 
                            	for(int i=0; i<funds.size();i++){ 
                            %>
                                <tr>
                                    <td><%=i%></td>
                                    <td><%=funds.get(i)%></td>
                                    <td><%=shares.get(i)%></td>
                                    <td><%=prices.get(i)%></td>
                                </tr>
                             <%
                             	}
                            }else{
                            %>
                            
                           <div class="col-md-12">
                            You own no funds, Buy some now!
                           </div>   
                             
                            <%}
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
