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
                    <li class="active">Research Fund</li>
                </ol>

				<jsp:include page="error-list.jsp" />

				
				<div class="col-md-5">
                        <div class="input-group">
                            <span class="input-group-addon">Search</span>
                            <input type="text" name="fundid" class="form-control" placeholder="Search by Fund_ID, Fund_Name or Fund_Symbol" value="">
                        </div>
                    </div>
				<br />
				
					<tbody>
						<tr>
							<td colspan="5">
								<div class="innerb">
									<table class="tabletwo" id="txtHint">

									</table>
								</div>
							</td>
						</tr>
					</tbody>

				</table>
				 <div class="row">
                  
                <div class="block-a highlight">
                
                 <div class="col-md-12">

<div class="alert alert-info"><h4>Fund Overview</h4></div>

<h5>Team-managed, diversified large-cap growth</h5>

<ul>
<li>Diversified large-cap growth portfolio reflecting the best ideas of the Janus research team</li>
<li>Sector specialists build high conviction portfolios that are combined to form a diversified fund</li>
<li>Seeks to add value through stock selection and minimize macro risks</li>



</ul>

</div>
                </div>
                    
                   
                    <div class="col-md-12">
                        <div class="alert alert-info"><h4>Performance </h4></div>
                    </div>
                    <div class="col-md-12">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Fund Name</th>
                                    <th>1 year</th>
                                    <th>3 year</th>
                                    <th>5 year</th>
                                    <th>10 year</th>
                                    <th>Since Fund Inception</th>
                               
                                    
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Google</td>
                                    <td>34.88%</td>
                                    <td>14.68%</td>
                                    <td>21.03%</td>
                                    <td>8.63%</td>
                                    <td>10.98%</td>
                                    
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Yahoo</td>
                                    <td>27.11%</td>
                                    <td>12.44%</td>
                                    <td>19.60%</td>
                                    <td>7.99%</td>
                                    <td>10.66%</td>
                                    
                                    
                                </tr>
                                <tr>
                                    <td>3</td>
                                    
                                    <td>LinkedIn</td>
                                    <td>33.48%</td>
                                    <td>16.45%</td>
                                    <td>20.39%</td>
                                    <td>7.83%</td>
                                    <td>8.59%</td>
                                    
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    
          <div class="col-md-12">  
          <div class="alert alert-info"><h4>Portfolio Analysis</h4></div>
          <h4>Top Holdings</h4>
<h6>% of Fund</h6>

<!--  getting the document for CMS for PDF Fact sheet link -->


					
					
	 					

	
	
					<p>
     	 				<a id="LINKID_pdf_ViewAllHoldings" href="/advisor_siteobjects/published/6EC6AC4B1B518F0DCC773BDACEFA6454/5ED2CC96761A87DD4A422AE36FD0612B/pdf/DisclosureResearch.pdf" onclick="trackPDF('View All Holdings')" target="_blank" >View All Holdings (PDF)</a>     
					</p>
	
			</div>
			<jsp:include page="template-footer.jsp" />
		</div>
</body>
</html>
