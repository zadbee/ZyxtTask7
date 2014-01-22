<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.text.DecimalFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mutual Fund Management</title>
<link rel="stylesheet" type="text/css" href="style/main.css">
</head>
<body>

<div id="container">
    <jsp:include page="template-header-navigation.jsp" />
    <div id="content-container">
        <jsp:include page="template-section-navigation-cus.jsp" />
        
        <div class="content">
            <h2> Request Check</h2>
            
            <jsp:include page="error-list.jsp" />
            
            <form method="post" action="requestcheck.do">
                <table>
                    <tr>
                        <td> Available Balance: $</td>
                        <td>    
                        <%
                            DecimalFormat nf = new DecimalFormat("#,##0.00");
                            nf.setMaximumFractionDigits(2);
                            nf.setMinimumFractionDigits(2);
	                        Double cash = (Double) request.getAttribute("cash");
	                        if (cash!=null)
	                            out.println(nf.format(cash));
                        %>
                        </td>
                    </tr>
                    <tr>
                        <td> Withdraw Amount: </td>
                        <td><input type="text" name="withdraw" value=""/></td>
                        <td> Should no less than 1 dollar and less than 10,000,000</td>                       
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" name="button" value="Submit"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <jsp:include page="template-footer.jsp" />
    </div>
</div>
</body>
</html>