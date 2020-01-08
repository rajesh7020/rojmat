<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Payment Mode</title>	
	<link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet"/>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="<c:url value="/resources/css/paymenttype.css"/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.5/validator.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<script src="<c:url value="/resources/js/paymenttype.js"/>"></script>
</head>
<body>
	<!-- Navigation Bar -->	
	<jsp:include page="userheader.jsp"/>	
	<h1 style="text-align:center;">Payment Mode</h1>
	<div class="container">
		<div class="row">
			<form:form action="${pageContext.request.contextPath}/user/savePaymentType" method="POST" id="myForm" modelAttribute="command">
			<div class="col-md-5">
				<h3>Add Payment Mode</h3>
					<form:input path="payid" type="hidden" id="payid" value="${paymenttype.payid}" class="form-control" autocomplete="off" placeholder="Payment Id" readonly="true"/>
				<div class="form-group">
					<form:label path="paymentMode">Payment Mode :</form:label>
					<form:input path="paymentMode" id="paymentMode" value="${paymenttype.paymentMode}" class="form-control" autocomplete="off" placeholder="Payment Mode"/>
					<small id="paymentmodechk"></small>
				</div>
				<button class="btn btn-primary" id="savebtn">Save</button>
			</div>
			</form:form>
			<div class="col-md-6">
				<h3>Payment Mode List</h3>
				<c:if test="${!empty paymenttypes }">
					<table id="tbid" class="table table-striped display table-hover" style="width:100%;">
						<thead>
							<tr>
								<th>Payment ID</th>
								<th>Payment Mode</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody id="tableid">
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>