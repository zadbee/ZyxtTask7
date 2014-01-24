<%@page import="databeans.Customer"%>
<%@page import= "java.util.ArrayList" %>
<%@page import= "databeans.Position" %>
<%@page import= "databeans.Fund" %>
<%@page import= "java.util.Date" %>
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
                    <li class="active">View Account</li>
                </ol>
                <jsp:include page="error-list.jsp" />
                <div class="row">
                    <div class="col-md-12">
                        <p class="lead">Personal Profile</p>
                    </div>
					<%Customer customer = (Customer) session.getAttribute("customer");
						Date lastDate = (Date)request.getAttribute("lastDate");
					if(customer == null) {
					    out.print("There is no customer information found in session!");
					    return;
					}
					%>
					<div class="col-md-12">
                        <table class="table">
                             <tbody>
                                <tr>
                                    <th>Name</th>
                                    <td><%=customer.getFirstname()+" "+customer.getLastname()%></td>
                                </tr>
                                <tr>
                                    <th>Address</th>
                                    <td><%=customer.getAddr_line1()+", "+customer.getAddr_line2()+", "+customer.getCity()+", "
                                    		+customer.getState()+", "+customer.getZip()
                                    %></td>
                                </tr>
                                <tr>
                                    <th>Cash Balance</th>
									<td><%="$"+ " "+customer.getCash()%></td>
                                </tr>
                                <tr>
                                    <th>Last Trading Day</th>
                                    <%if(lastDate != null) {%>
									<td><%=lastDate%></td>
									<% }else{%>
									<td>N/A</td>
									<%} %>
                                </tr>                               
                                
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-12">
                        <p class="lead">Your Fund List</p>
                    </div>
                    <%  ArrayList<Long> prices = (ArrayList<Long>)request.getAttribute("prices");
                    	ArrayList<Fund> funds = (ArrayList<Fund>)request.getAttribute("funds");
                    	Position[] pos = (Position[])request.getAttribute("pos");
                    	if(funds!=null && funds.size()!=0){
                    %>
                    
                    
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Fund ID</th>
                                    <th>Fund Name</th>
                                    <th>Fund Symbol</th>
                                    <th>Shares</th>
                                    <th>Latest Prices</th>
                                </tr>
                            </thead>
                            <tbody>
                            <% 
                            	for(int i=0; i<funds.size();i++){ 
                            %>
                                <tr>
                                    <td><%=pos[i].getFund_id()%></td>
                                    <td><%=funds.get(i).getName()%></td>
                                    <td><%=funds.get(i).getSymbol()%></td>
                                    <td><%=pos[i].getShares()%></td>
                                    <%if(prices.get(i)!=-1){%>
                                    <td><%=prices.get(i)%></td>
                                    <%}else{ %>
                                    <td><%="Not Initialized"%></td>
                                    <%} %>
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
