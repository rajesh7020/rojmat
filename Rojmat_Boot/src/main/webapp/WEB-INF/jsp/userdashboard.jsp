<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	<link href="<c:url value="/resources/css/dashboard.css?v=1"/>" rel="stylesheet" type="text/css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.5/validator.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<script src="<c:url value="/resources/js/dashboard.js?v=ds"/>"></script>
</head>
<body> 
<!-- Navigation Bar -->	
	<jsp:include page="userheader.jsp"/>	
	<h1>Rojmat (Daily Account)</h1>
	<strong>${email}</strong><br/><strong>${user_id}</strong><strong>${shopname}</strong>
	<p>${sucessLogin}</p>
	<div class="container">
		<div class="row">
			<form:form action="${pageContext.request.contextPath}/user/savecreditdebit" method="POST" id="myForm" modelAttribute="command">
			<div class="col-md-5">
				<h3>Credit Account</h3>
				<div class="form-group">
					<form:label path="openingbalance">Opening Balance :</form:label>
					<form:input path="openingbalance" id="openingbalance" class="form-control"/>
					<small id="openingBalancechk"></small>
				</div>
				<div class="form-group">
					<form:label path="debittotalplusdrawertotal">Debit Total + Drawer Total :</form:label>
					<form:input path="debittotalplusdrawertotal" id="debittotalplusdrawertotal" class="form-control" readonly="true"/>
					<small id="debitplusdrawertotalchk"></small>
				</div>
				<div class="form-group">
					<form:label path="todaybusiness">Today Business :</form:label>
					<form:input path="todaybusiness" id="todaybusiness" class="form-control" readonly="true"/>
					<small id="todaybusinesschk"></small>
				</div>
				<div class="form-group">
					<form:label path="date">Date :</form:label>
					<form:input path="date" type="date" id="date" value="" class="form-control" autocomplete="off"/>
					<small id="datechk"></small>
				</div>
				<button class="btn btn-primary" id="savebtn" onclick="saveCreditDebit();">Save</button>
				<button class="btn btn-warning" id="resetbtn" type="reset">Reset</button>
			</div>
			<div class="col-md-6">
			<h3>Debit Account</h3>
			<input type="button" class="btn btn-primary" value="Add Row" id="addRow" tabindex="1"/>&nbsp;&nbsp;
			<input type="button" class="btn btn-danger" id="deleteRow" value="Delete"/><br>
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
					<form:input path="debittotal" id="debittotal" class="form-control" readonly="true"/>
					<small id="debittotalchk"></small>
				</div>
				<div class="form-group">
					<form:label path="drawertotal">Drawer Total :</form:label>
					<form:input path="drawertotal" id="drawertotal" class="form-control" autocomplete="off"/>
					<small id="drawertotalchk"></small>
				</div>

		</div>
			</form:form>
		</div>
			<div class="col-md-12">
				<h3>Credit Debit Account List</h3>
				<c:if test="${!empty creditdebitlist }">
				<div class="table-responsive">
					<table id="tblcreditdebit" class="table table-striped display table-hover">
						<thead>
							<tr>
								<th>Cid</th>
								<th>Opening Balance</th>
								<th>Debit Total</th>
								<th>Drawer Total</th>
								<th>Debit + drawer</th>
								<th>Today Business</th>
								<th>Date</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody id="tblProducts">
						</tbody>
					</table>
				</div>	
				</c:if>
			</div>
		</div>
		 <!-- The Modal -->
    <div class="modal" id="myModal">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Credit and Debit Record</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<div class="row">
			<div class="col-md-5">
				<h3>Credit Record</h3>
				<div class="form-group">
					<label>Opening Balance :</label>
					<input class="form-control" value="" id="openingBal" readonly="readonly"/>
				</div>
				<div class="form-group">
					<label>Debit Total + Drawer Total :</label>
					<input class="form-control" value="" id="debitplusDrawertotal" readonly="readonly"/>
				</div>
				<div class="form-group">
					<label>Today Business :</label>
					<input class="form-control" value="" id="todayBusiness" readonly="readonly"/>
				</div>
				<div class="form-group">
					<label>Date :</label>
					<input class="form-control" value="" id="Date" readonly="readonly"/>
				</div>
			</div>
			<div class="col-md-5">
			<h3>Debit Record</h3>
				<table class="table table-hover table-bordered" id="tableid1">
					<thead>
						<tr>
							<th>Sr.No</th>
							<th>Did</th>
							<th>Amount</th>
							<th>Description</th>
						</tr>
					</thead>
					<tbody id="tblCredit">
						
					</tbody> 
				</table>
				<div class="form-group">
					<label>Debit Total:</label>
					<input class="form-control" value="" id="debitTotal" readonly="readonly"/>
				</div>
				<div class="form-group">
					<label>Drawer Total :</label>
					<input class="form-control" value="" id="drawerTotal" readonly="readonly"/>
				</div>
				<div class="form-group">
					<label>Debit Total + Drawer Total :</label>
					<input class="form-control" value="" id="debitplusDrawertotal1" readonly="readonly"/>
				</div>
			</div>
		</div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
 </div>

		<%-- <jsp:include page="userfooter.jsp"/> --%>
	<!-- select distinct credit.cid, credit.openingbalance, credit.debittotal, credit.drawertotal, credit.debittotalplusdrawertotal, credit.todaybusiness, debit.did, debit.amount, debit.description from Credit credit Left Join Debit debit on credit.cid= debit.did; -->
	<%-- <button id="login" type="submit" value="Login" data-loading-text="<i class='fa fa-spinner fa-spin'></i> Checking login..." class="btn btn-lg btn-success btn-block">Login</button> --%>
	
</body>
</html>