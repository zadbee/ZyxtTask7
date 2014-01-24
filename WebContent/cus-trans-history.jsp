<%@page import="databeans.Customer"%>
<%@page import="databeans.Fund"%>
<%@page import="databeans.Transaction"%>
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
                    <li class="active">Transaction History</li>
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
                        <p class="lead">Your Transaction History</p>
                    </div>
                    <jsp:include page="error-list.jsp" />

                    
                    <div class="col-md-12">
                        <p class="lead"></p>
                    </div>

                    <% 	Transaction[] trans = (Transaction[])request.getSession().getAttribute("trans");
                    	String[] funds = (String[])request.getSession().getAttribute("funds");
                    	if(trans!=null && trans.length!=0){
                    %>
                    
                    
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Date</th>
                                    <th>Action</th>
                                    <th>Fund Name</th>
                                    <th>Shares</th>
                                    <th>Amount</th>
                                    <th>Status</th>
  
                                </tr>
                            </thead>
                            <tbody>
                            <% 
                            	for(int i=0; i<trans.length;i++){ 
                            %>
                                <tr>
		                            <%if(trans[i].getExecute_date()!=null) {%>
                                    <td><%=trans[i].getExecute_date()%></td>
                                    <%}else{ %>
                                    <td>N/A</td>
                                    <%} %>
                                    
                                    <td><%=trans[i].getTransaction_type()%></td>
                                    
                                    <%if(funds[i]!=null){%>
                                    <td><%=funds[i]%></td>
                                    <%}else{%>
                                    <td>N/A</td>
                                    <%} %>
                                    
                                    <%if(trans[i].getShares()!=0){%>
                                    <td><%=trans[i].getShares()%></td>
                                    <%}else{%>
                                    <td>N/A</td>
                                    <%} %>
                                    
                                    <%if(trans[i].getAmount()!=0){%>
                                    <td><%=trans[i].getAmount()%></td>
                                    <%}else{%>
                                    <td>N/A</td>
                                    <%} %>
                                    
                                    <td><%=trans[i].getStatus() %></td>                                 
                                </tr>
                             <%
                             	}
                            }else{
                            %>
                           </tbody>
                        </table>
              		</div>
              		
	                  <div class="col-md-12">
	                  No transaction records found. Make your first transaction today! 
	                  </div>   
                             
                            <%}
                    	%>
                   
                    
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