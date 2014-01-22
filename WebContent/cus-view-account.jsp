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
                
                <div class="row">
                    <div class="col-md-12">
                        <p class="lead">Personal Profile</p>
                    </div>
					
					<div class="col-md-12">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <td>John Doe</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th>Address</th>
                                    <td>1111 Forbes Avenue, Pittsburgh, PA</td>
                                </tr>
                                <tr>
                                    <th>Last Trading Day</td>
                                    <td>01/15/2014</td>
                                </tr>
                                <tr>
                                    <th>Cash Balance</th>
									<td>1000.00</td>
                                </tr>
                            </tbody>
                        </table>
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
                                    <th>Value</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Google</td>
                                    <td>1000.001</td>
                                    <td>15213.000</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Yahoo</td>
                                    <td>235.837</td>
                                    <td>15640.000</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    
                                    <td>LinkedIn</td>
                                    <td>8871.134</td>
                                    <td>15615.000</td>
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
