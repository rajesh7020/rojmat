<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("user_id") == null) {
		response.sendRedirect("/");
		return;
	}
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Accounting</title>	
	<link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="<c:url value="/resources/css/dashboard.css"/>" rel="stylesheet" type="text/css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.5/validator.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
		$('#tblcreditdebit').DataTable({
			"paging": true, 
		    "order": [0, 'desc'],
		});
	</script>
</head>
<body> 
	<div class="container">
		<div class="row">
			<div class="col-md-5">
				<h3>Credit Account</h3>
				<div class="form-group">
					<form:label path="openingbalance">Opening Balance :</form:label>
					<form:input path="openingbalance" id="openingBalance" class="form-control" readonly="true"/>
					<small id="openingBalancechk"></small>
				</div>
				<div class="form-group">
					<form:label path="debittotalplusdrawertotal">Debit Total + Drawer Total :</form:label>
					<form:input path="debittotalplusdrawertotal" id="debitplusdrawertotal" class="form-control" readonly="true"/>
					<small id="debitplusdrawertotalchk"></small>
				</div>
				<div class="form-group">
					<form:label path="todaybusiness">Today Business :</form:label>
					<form:input path="todaybusiness" id="todaybusiness" class="form-control" readonly="true"/>
					<small id="todaybusinesschk"></small>
				</div>
				<div class="form-group">
					<form:label path="date">Date :</form:label>
					<form:input path="date" type="date" id="date" value="" class="form-control" readonly="true"/>
					<small id="datechk"></small>
				</div>
			</div>
			<div class="col-md-6">
			<h3>Debit Account</h3>
				<table class="table table-hover table-bordered" id="tableid">
					<thead>
						<tr>
							<th>Select</th>
							<th>Amount</th>
							<th>Description</th>
						</tr>
					</thead>
					<tbody>
					</tbody> 
				</table>
				<div class="form-group">
					<form:label path="debittotal">Debit Total:</form:label>
					<form:input path="debittotal" id="debittotal" class="form-control" autocomplete="off"/>
					<small id="debittotalchk"></small>
				</div>
				<div class="form-group">
					<form:label path="drawertotal">Drawer Total :</form:label>
					<form:input path="drawertotal" id="drawertotal" class="form-control" autocomplete="off"/>
					<small id="drawertotalchk"></small>
				</div>
			</div>
		</div>
	</div>
		<!-- Log In Popup -->
   
		<%-- <jsp:include page="userfooter.jsp"/> --%>
	<!-- select distinct credit.cid, credit.openingbalance, credit.debittotal, credit.drawertotal, credit.debittotalplusdrawertotal, credit.todaybusiness, debit.did, debit.amount, debit.description from Credit credit Left Join Debit debit on credit.cid= debit.did; -->
	<%-- <button id="login" type="submit" value="Login" data-loading-text="<i class='fa fa-spinner fa-spin'></i> Checking login..." class="btn btn-lg btn-success btn-block">Login</button> --%>
	<script src="<c:url value="/resources/js/dashboard.js"/>"></script>
</body>
</html>