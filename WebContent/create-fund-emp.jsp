<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
        <div class="page-content-wrapper">
        	<div class="content-header">
                <h1>
                    <a id="menu-toggle" href="#" class="btn btn-default"><i class="icon-reorder"></i></a>
                   
                </h1>
            </div>
            
            <div class="page-content inset">
                <ol class="breadcrumb">
                    <li><a href="#">Home</a></li>
                    <li class="active">Create Fund</li>
                </ol>            
	            <jsp:include page="error-list.jsp" />
	            
	            <div class="row">
		            <div class="col-md-12">
					<form method="post" action="create-fund-emp.do">
						<table>
							<tr>
								<td> Fund Name </td>
								<td><input type="text" name="fundName" class="form-control" placeholder="Fund Name" value="${fund.name}"></td>
							</tr>
							<tr>
								<td> Ticker </td>
								<td><input type="text" name="fundSymbol" class="form-control" placeholder="Ticker" value="${fund.symbol}"></td>
							</tr>
							<tr>  
								<td colspan="2" align="center">
									<button type="submit" name="button" class="btn btn-default" value="submit">Create Fund</button>
								</td>
							</tr>
						</table>
					</form>
					</div>
				</div>
        	</div>
		</div>
</body>
</html>