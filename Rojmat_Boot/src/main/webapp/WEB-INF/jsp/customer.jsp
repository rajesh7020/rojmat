<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet"/>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="<c:url value="/resources/css/customer.css?v=44"/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.5/validator.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<script src="<c:url value="/resources/js/customer.js?v=1"/>"></script>
</head>
<body>
	<!-- Navigation Bar -->	
	<jsp:include page="userheader.jsp"/>	
	<div class="container">
		<div class="row">
			<h3>Add/Edit Customer</h3>
			<form:form action="${pageContext.request.contextPath}/user/saveCustomer" method="POST" id="myForm" modelAttribute="command">
					<form:input path="custid" type="hidden" id="custid" value="${customer.custid}" class="form-control" autocomplete="off" placeholder="Customer Id" readonly="true"/>
			<div class="col-md-4">	
				<div class="form-group">
					<form:label path="customername">Customer Name :</form:label>
					<form:input path="customername" id="customername" value="${customer.customername}" class="form-control" autocomplete="off" placeholder="Customer Name"/>
					<small id="customernamechk"></small>
				</div>
			</div>
			<div class="col-md-4">	
				<div class="form-group">
					<form:label path="gstinno">GSTIN :</form:label>
					<form:input path="gstinno" id="gstinno" value="${customer.gstinno}" class="form-control" autocomplete="off" placeholder="GSTIN NO"/>
					<small id="gstinnochk"></small>
				</div>
			</div>	
			<div class="col-md-4">
				<div class="form-group">
					<form:label path="panno">PAN :</form:label>
					<form:input path="panno" id="panno" value="${customer.panno}" class="form-control" autocomplete="off" placeholder="PAN NO"/>
					<small id="pannochk"></small>			
				</div>
			</div>	
			<div class="col-md-4">	
				<div class="form-group">
					<form:label path="address">Address :</form:label>
					<form:input path="address" id="address" value="${customer.address}" class="form-control" autocomplete="off" placeholder="Address"/>
					<small id="addresschk"></small>
				</div>
			</div>
			<div class="col-md-4">	
				<div class="form-group">
					<form:label path="pincode">PinCode :</form:label>
					<input name="pincode" id="pincode" value="${customer.pincode}" class="form-control" autocomplete="off" placeholder="Pin Code"/>
					<small id="pincodechk"></small>
				</div>
			</div>
			<div class="col-md-4">	
				<div class="form-group">
					<form:label path="state.stateid">State: </form:label>
					<form:select path="state.stateid" id="state" name="state" class="form-control">
						<option value="0">---Select---</option>
						<c:forEach items="${states}" var="state">
							<option value="${state.stateid}">${state.statename}</option>
						</c:forEach>
					</form:select>
					<small id="statechk"></small>
				</div>
			</div>
			<div class="col-md-4">	
				<div class="form-group">
					<form:label path="city">City :</form:label>
					<form:input path="city" id="city" value="${customer.city}" class="form-control" autocomplete="off" placeholder="City"/>
					<small id="citychk"></small>
				</div>
			</div>	
			<div class="col-md-4">	
				<div class="form-group">
					<form:label path="mobileno">Mobile :</form:label>
					<input name="mobileno" id="mobileno" maxlength="10" value="${customer.mobileno}" class="form-control" autocomplete="off" placeholder="Mobile"/>
					<small id="mobilnochk"></small>
				</div>
			</div>
			<div class="col-md-4">	
				<div class="form-group">
					<form:label path="email">Email :</form:label>
					<form:input path="email" id="email" value="${customer.email}" class="form-control" autocomplete="off" placeholder="Email"/>
					<small id="emailchk"></small>
				</div>
			</div>	
			<div class="col-md-4">	
				<div class="form-group">
					<form:label path="bankname">Bank Name :</form:label>
					<form:input path="bankname" id="bankname" value="${customer.bankname}" class="form-control" autocomplete="off" placeholder="Bank Name"/>
					<small id="banknamechk"></small>
				</div>
			</div>	
			<div class="col-md-4">	
				<div class="form-group">
					<form:label path="branchname">Branch Name :</form:label>
					<form:input path="branchname" id="branchname" value="${customer.branchname}" class="form-control" autocomplete="off" placeholder="Branch Name"/>
					<small id="branchnamechk"></small>
				</div>
			</div>	
			<div class="col-md-4">	
				<div class="form-group">
					<form:label path="accountno">Account Number :</form:label>
					<form:input path="accountno" id="accountno" value="${customer.accountno}" class="form-control" autocomplete="off" placeholder="Account Number"/>
					<small id="emailchk"></small>
				</div>
			</div>	
			<div class="col-md-4">	
				<div class="form-group">
					<form:label path="ifscode">IFSC Code :</form:label>
					<form:input path="ifscode" id="ifscode" value="${customer.ifscode}" class="form-control" autocomplete="off" placeholder="IFSC Code"/>
					<small id="emailchk"></small>
				</div>
			</div>	
				<button class="btn btn-primary" id="savebtn">Save</button>
			</form:form>
		</div>
	</div>
	<div class="row1">
			<div class="col-md-12">
				<h3>Customer List</h3>
				<c:if test="${!empty customers }">
					<table id="tbid" class="table table-striped display table-hover" style="width:100%;">
						<thead>
							<tr>
								<th>Cid</th>
								<th>Customer Name</th>							
								<th>GSTIN</th>
								<th>PAN</th>
								<th>Address</th>
								<th>Pincode</th>
								<th>State</th>
								<th>City</th>
								<th>Mobile</th>
								<th>Email</th>
								<th>Bank Name</th>
								<th>Branch Name</th>
								<th>Acc_No</th>
								<th>IFSC_Code</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody id="tableid">
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
</body>
</html>