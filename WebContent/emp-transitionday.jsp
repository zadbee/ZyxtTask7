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
            <!-- Keep all page content within the page-content inset div! -->
            <div class="page-content inset">
                <ol class="breadcrumb">
                    <li><a href="#">Home</a></li>
                    <li class="active">Transition Day</li>
                </ol>
                
                <jsp:include page="error-list.jsp"/>
                
                <div class="row">	
					<div class="col-md-12">
						<form method="post" action="emp-transitionday.do">
							<div class="col-md-12">
								<div class="col-md-6" align="center">
									Last Transition Day<br/>
									<c:choose>
										<c:when test="${lastday == null}">
											N/A
										</c:when>
										<c:otherwise>
											${lastday}
										</c:otherwise>
									</c:choose>
								</div>
								<div class="col-md-6" align="center">
									New Transition Day<br/> 
									<input type="text" style="width:20%;" name="iyear" placeholder="YYYY"> - 
    								<input type="text" style="width:20%;" name="imonth" placeholder="MM"> - 
    								<input type="text" style="width:20%;" name="iday" placeholder="DD">
								</div>
							</div>
                        	<p class="lead">Funds</p>
	                        <table class="table">
	                            <thead>
	                                <tr>
	                                    <th>Fund ID</th>
	                                    <th>Fund Name</th>
	                                    <th>Fund Symbol</th>
	                                    <th>Current Price</th>
	                                    <th>New Price</th>
	                                </tr>
	                            </thead>
	                            <tbody>
	                                <c:forEach var="fund" items="${prices}" varStatus="Status">
	                                	<tr>
	                                		<td>${fund.fund_id}</td>
	                                		<td>${names[Status.index]}</td>
	                                		<td>${symbols[Status.index]}</td>
	                                		<td>
	                                		<c:choose>
	                                		<c:when test="${fund.price > 0}">
	                                			${fund.price / 100.0}
	                                		</c:when>
	                               			<c:otherwise>
	                               				Not initialized
	                               			</c:otherwise>
	                               			</c:choose>
	                                		</td>
	                                		<td><input type="text" name="price_${fund.fund_id}"/></td>
	                                	</tr>
	                                </c:forEach>
	                                <tr>
                        				<td colspan="6" align="center"><input type="submit" name="transbutton" value="Submit"/></td>
                    				</tr>
	                            </tbody>
	                        </table>
                        </form>
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
