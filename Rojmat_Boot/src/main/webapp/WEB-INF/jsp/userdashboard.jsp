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
		
		function openWin() {
    		window.open("",  "width=300, height=200");
    	}
		function showAll() {
			
			$.ajax({
				type : "GET",
				url : "/user/getCreditDebit",
				dataType : "application/json",
				success : function(data) {
					alert(data);
					var rows = '';
					if($.trim(data)==""){
						rows += '<tr><td colspan="10" style="text-align: center;">No data available</td></tr>';
						$('#tblcreditdebitAll').html(rows);
					}
					var sr = 1;
					$.each(data, function(index, item) {
						
						rows += '<tr><td >' + sr + '</td>';
						rows += '<td>' + item.cid + '</td>';
						rows += '<td>' + item.openingbalance + '</td>';
						rows += '<td>' + item.drawertotal + '</td>';
						rows += '<td>' + item.debittotalplusdrawertotal + '</td>';
						rows += '<td>' + item.todaybusiness + '</td>';
						rows += '<td>' + item.date + '</td>';
						rows += '<td>' + item.did + '</td>';
						rows += '<td>' + item.amount + '</td>';
						rows += '<td>' + item.description + '</td>';
						sr++;
						});
						$('#tblcreditdebitAll').html(rows);
						},
						error : function(xmlHttpRequest, textStatus, errorThrown) {
							alert("error");
						}
					});
		}
	</script>
</head>
<body> 
<!-- Navigation Bar -->	
	<jsp:include page="userheader.jsp"/>	
	<h1>Rojmat (Daily Account)</h1><br/>
	<strong>${email}</strong><br/><strong>${user_id}</strong><strong>${shopname}</strong>
	<p>${sucessLogin}</p>
	<div class="container">
		<div class="row">
			<form:form action="${pageContext.request.contextPath}/user/savecreditdebit" method="POST" id="myForm" modelAttribute="command">
			<div class="col-md-5">
				<h3>Credit Account</h3>
				<div class="form-group">
					<form:label path="openingbalance">Opening Balance :</form:label>
					<form:input path="openingbalance" id="openingBalance" class="form-control" autocomplete="off" placeholder="Opening Balance"/>
					<small id="openingBalancechk"></small>
				</div>
				<div class="form-group">
					<form:label path="debittotalplusdrawertotal">Debit Total + Drawer Total :</form:label>
					<form:input path="debittotalplusdrawertotal" id="debitplusdrawertotal" class="form-control" autocomplete="off"/>
					<small id="debitplusdrawertotalchk"></small>
				</div>
				<div class="form-group">
					<form:label path="todaybusiness">Today Business :</form:label>
					<form:input path="todaybusiness" id="todaybusiness" class="form-control" autocomplete="off"/>
					<small id="todaybusinesschk"></small>
				</div>
				<div class="form-group">
					<form:label path="date">Date :</form:label>
					<form:input path="date" type="date" id="date" value="" class="form-control" autocomplete="off"/>
					<small id="datechk"></small>
				</div>
				<button class="btn btn-primary" id="savebtn">Save</button>
				<button class="btn btn-warning" id="savebtn" type="reset">Reset</button>
			</div>
			<div class="col-md-6">
			<h3>Debit Account</h3>
			<input type="button" class="btn btn-primary" value="Add Row" id="addRow"/>&nbsp;&nbsp;
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
					<form:input path="debittotal" id="debittotal" class="form-control" autocomplete="off"/>
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
					<table id="tblcreditdebit" class="table table-striped display table-hover" style="width:100%;">
						<thead>
							<tr>
								<th>Cid</th>
								<th>Opening Balance</th>
								<th>Debit Total</th>
								<th>Drawer Total</th>
								<th>Debit + drawer</th>
								<th>Today Business</th>
								<th>Date</th>
								<th>Did</th>
								<th>Amount</th>
								<th>Description</th>
								<th>Actions</th>
							</tr>
						</thead>
						<c:forEach items="${creditdebitlist}" var="credit">
							<tbody>
							<tr>
								<td><c:out value="${credit.cid}"/></td>
								<td><c:out value="${credit.openingbalance}"/></td>
								<td><c:out value="${credit.debittotal}"/></td>
								<td><c:out value="${credit.drawertotal}"/></td>
								<td><c:out value="${credit.debittotalplusdrawertotal}"/></td>
								<td><c:out value="${credit.todaybusiness}"/></td>
								<td><c:out value="${credit.date}"/></td>
								<c:forEach items="${credit.debit}" var="debit">
								<tr>
									<td colspan="7"></td>
									<td><c:out value="${debit.did}"></c:out></td>
									<td><c:out value="${debit.amount}"></c:out></td>
									<td><c:out value="${debit.description}"></c:out></td>
								</tr>	
								</c:forEach>
							</tr>
							</tbody>
							<%-- <td><a href="#" class="btn btn-danger">Delete</a> | <a href="/user/getCreditDebit?cid=${credit.cid}" class="btn btn-warning" onclick="showAll();">More</a></td> --%>
						</c:forEach>	
					</table>
				</c:if>
			</div>
	</div>
   </section>
		</div>
		<!-- Log In Popup -->
   
		<%-- <jsp:include page="userfooter.jsp"/> --%>
	<!-- select distinct credit.cid, credit.openingbalance, credit.debittotal, credit.drawertotal, credit.debittotalplusdrawertotal, credit.todaybusiness, debit.did, debit.amount, debit.description from Credit credit Left Join Debit debit on credit.cid= debit.did; -->
	<%-- <button id="login" type="submit" value="Login" data-loading-text="<i class='fa fa-spinner fa-spin'></i> Checking login..." class="btn btn-lg btn-success btn-block">Login</button> --%>
	<script src="<c:url value="/resources/js/dashboard.js"/>"></script>
</body>
</html>