<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<section id="nav-bar">
	<nav class="navbar navbar-expand-lg navbar-light">
 	<a class="navbar-brand" href="#"><img alt="" src=""></a>
  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    	<span class="navbar-toggler-icon"></span>
  	</button>
  	<div class="collapse navbar-collapse" id="navbarNav">
    	<ul class="navbar-nav ml-auto">
      	<li class="nav-item">
        	<a class="nav-link" href="${pageContext.request.contextPath}/user/userdashboard">Rojmat</a>
      	</li>
      	<li class="dropdown">
        	<a href="#" data-toggle="dropdown" class="dropdown-toggle nav-link">Products <b class="caret"></b></a>
            <ul class="dropdown-menu">
	            <li><a href="${pageContext.request.contextPath}/user/viewCategory">Category</a></li>
	            <li><a href="${pageContext.request.contextPath}/user/viewProduct">Product</a></li>
	            <li class="divider"></li>
	            <li><a href="${pageContext.request.contextPath}/user/viewUnit">Unit</a></li>
	            <li><a href="${pageContext.request.contextPath}/user/viewGst">GST</a></li>
            </ul>
        </li>
      	<li class="nav-item dropdown">
        	<a href="#" data-toggle="dropdown" class="dropdown-toggle nav-link">Payment <b class="caret"></b></a>
            <ul class="dropdown-menu">
	            <li><a href="${pageContext.request.contextPath}/user/viewPaymentType">Payment Mode</a></li>
	            <li class="divider"></li>
	            <li><a href="${pageContext.request.contextPath}/user/viewSalesPayment">Sales Payment</a></li>
	            <li><a href="${pageContext.request.contextPath}/user/viewPurchasePayment">Purchase Payment</a></li>
            </ul>
        </li>
        <li class="nav-item dropdown">
        	<a href="#" data-toggle="dropdown" class="dropdown-toggle nav-link">Ledger<b class="caret"></b></a>
            <ul class="dropdown-menu">
	           <li><a href="${pageContext.request.contextPath}/user/viewCustomer">Customer</a></li>
	            <li><a href="${pageContext.request.contextPath}/user/viewSupplier">Supplier</a></li>
	            <li class="divider"></li>
	            <li><a href="${pageContext.request.contextPath}/user/viewState">States</a></li>
            </ul>
        </li>
      	<li class="nav-item">
        	<a class="nav-link" href="${pageContext.request.contextPath}/user/invoicehome">Invoice</a>
      	</li>
      	<li class="nav-item">
        	<a class="nav-link" href="${pageContext.request.contextPath}/user/userprofilehome">User Profile</a>
      	</li>
       	<li class="nav-item">
        	<a class="nav-link" href="${pageContext.request.contextPath}/">LogOut</a>
      	</li>
    </ul>
  </div>
</nav>
</section>
</body>
</html>