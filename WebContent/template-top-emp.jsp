<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="databeans.Customer" %>
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
    <!-- Topbar -->
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="http://startbootstrap.com">Carnegie Financial Services</a>
            </div>
            
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-right navbar-ex1-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="#about">About</a>
                    </li>
                    <li><a href="#services">Services</a>
                    </li>
                    <li><a href="#contact">Contact</a>
                    </li>
                    </li>
                    <li><a href="logout-cus.do">Logout</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <div id="wrapper">
<%--<% 
if (session.getAttribute("employee") == null) {
%>
        <li><span class="menu-item"><a href="employee-login.do">Login</a></span></li>
<%   
} else {
%> --%>	

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand"><a href="#">ZYXT Mutual Fund</a>
                </li>
                <li><a href="emp_changePwd.do">Change Password</a>
                </li>
                <li><a href="create-employee-acct.do">Create Employee Account</a>
                </li>
                <li><a href="create-customer-acct.do">Create Customer Account</a>
                </li>
                <li><a href="createfund.do">Create Fund</a>
                </li>
                <li><a href="ransitionday.do">Transition Day</a>
                </li>
                <li><a href="manage-customers-emp.do">Search Customer</a>
                </li>
<%--
<%
Customer customer = (Customer)session.getAttribute("cus");
	if (customer != null) {
%>
       			<li>Actions for <%=customer.getUsername() %></li>
        		<li><a href="emp_resetPwd.do">Reset Password</a></li>
        		<li><a href="viewcustomeraccount.do">View Account</a></li>
        		<li><a href="empviewhistory.do">Transaction History</a></li>
        		<li><a href="depositcheck.do">Deposit Check</a></li>        
<%
    }
}
%>
 --%>

            </ul>
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