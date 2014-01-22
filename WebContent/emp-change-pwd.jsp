<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
                    <li class="active">Change Password</li>
                </ol>
                <form method="post" action="emp_changePwd.do">
                <div class="row">
                    <div class="col-md-12">
                        <p class="lead">Change Password</p>
                    </div>
					<div class="col-md-5">
                        <div class="input-group">
                            <span class="input-group-addon">New</span>
                            <input type="password" name="newPassword" class="form-control" placeholder="New password" value="">
                        </div>
                    </div>
					<div class="col-md-12">
                        <p class="lead"> </p>
                    </div>
					<div class="col-md-5">
                        <div class="input-group">
                            <span class="input-group-addon">Re-enter</span>
                            <input type="password" name="confirmPassword" class="form-control" placeholder="Re-enter the new password" value="">
                                </div>
                    </div>
					<div class="col-md-12">
                        <p class="lead"> </p>
                    </div>
                    <div class="col-md-4">
                        <div class="btn-group">
                            <button type="submit" name="button" class="btn btn-default" value="submit">Submit Change</button>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <p class="lead"></p>
                    </div>
                </div>
				</form>
				<jsp:include page="template-footer.jsp" />
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
