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
                    <li class="active">Sell Fund</li>
                </ol>
                
                <div class="row">
                    <div class="col-md-12">
                        <p class="lead">Sell Fund</p>
                    </div>
                    <jsp:include page="error-list.jsp" />
                    <form method="post" action="cus_sellFund.do" class="form-horizontal">
	                    <div class="col-md-12">
	                        <div class="input-group">
	                            <span class="input-group-addon">#</span>
	                            <input type="text" class="form-control" placeholder="Fund Symbol" name="fundSymbol">
	                        </div>
	                    </div>
	                    <div class="col-md-12">
	                        <p class="lead"> </p>
	                    </div>
	                    <div class="col-md-12">
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
	                    <div class="col-md-2">
	                        <p class="lead"> </p>
	                    </div>
	                   
	                   <div class="col-md-12" align="right">
                        <div class="btn-group">
                            <button type="submit" class="btn btn-default btn-lg">Buy Now</button>
                        </div>
                    </div>
                    </form>
                    <div class="col-md-4">
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
	                                    <th>Fund ID</th>
	                                    <th>Fund Name</th>
	                                    <th>Fund Symbol</th>            
	                                    <th>Shares</th>
	                                    <th>Latest Price</th>
	                                </tr>
	                            </thead>
	                            <tbody>
	                                <c:forEach var="fund" items="${funds}" varStatus="Status">
	                                	<tr>
	                                		<td>${fund.fund_id}</td>
	                                		<td>${fund.name}</td>
	                                		<td>${fund.symbol}</td>
	                                		<td>${pos[Status.index].shares / 1000.0}</td>
	                                		<td>${prices[Status.index] / 100.0}</td>
	                                	</tr>
	                                </c:forEach>
	                            </tbody>
	                        </table>
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
