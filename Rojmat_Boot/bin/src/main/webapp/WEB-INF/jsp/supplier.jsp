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
	<link href="<c:url value="/resources/css/suppliers.css"/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.5/validator.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
</head>
<body>
	<!-- Navigation Bar -->	
	<jsp:include page="userheader.jsp"/>	
	<div class="container">
		<div class="row">
			<h3>Add/Edit Supplier</h3>
			<form:form action="${pageContext.request.contextPath}/supp/savesupplier" method="POST" id="myForm" modelAttribute="command">
			<div class="col-md-4">
				<div class="form-group">
					<form:label path="supid">Supplier Id :</form:label>
					<form:input path="supid" id="supid" value="${supplier.supid}" class="form-control" autocomplete="off" placeholder="Sales Id" readonly="true"/>
					<small id="salespayidhk"></small>
				</div>
			</div>	
			<div class="col-md-4">	
				<div class="form-group">
					<form:label path="suppliername">Supplier Name :</form:label>
					<form:input path="suppliername" id="suppliername" value="${supplier.suppliername}" class="form-control" autocomplete="off" placeholder="Supplier Name"/>
					<small id="suppliernamechk"></small>
				</div>
			</div>
			<div class="col-md-4">	
				<div class="form-group">
					<form:label path="gstinno">GSTIN :</form:label>
					<form:input path="gstinno" id="gstinno" value="${supplier.gstinno}" class="form-control" autocomplete="off" placeholder="GSTIN NO"/>
					<small id="gstinnochk"></small>
				</div>
			</div>	
			<div class="col-md-4">
				<div class="form-group">
					<form:label path="panno">PAN :</form:label>
					<form:input path="panno" id="panno" value="${supplier.panno}" class="form-control" autocomplete="off" placeholder="PAN NO"/>
					<small id="pannochk"></small>			
				</div>
			</div>	
			<div class="col-md-4">	
				<div class="form-group">
					<form:label path="address">Address :</form:label>
					<form:input path="address" id="address" value="${supplier.address}" class="form-control" autocomplete="off" placeholder="Address"/>
					<small id="addresschk"></small>
				</div>
			</div>
			<div class="col-md-4">	
				<div class="form-group">
					<form:label path="pincode">PinCode :</form:label>
					<form:input path="pincode" id="pincode" value="${supplier.pincode}" class="form-control" autocomplete="off" placeholder="Category Name"/>
					<small id="pincodechk"></small>
				</div>
			</div>
			<div class="col-md-4">	
				<div class="form-group">
					<form:label path="state.stateid">State: </form:label>
					<form:select path="state.stateid" cssStyle="width:150px;" id="state" name="state" class="form-control" required="required">
					<option value="" selected="selected">-Select-</option>
					<c:forEach items="${states}" var="state">
						<option value="${state.stateid}">${state.statename}</option>
					</c:forEach>
					<small id="statechk"></small>
					</form:select>
				</div>
			</div>
			<div class="col-md-4">	
				<div class="form-group">
					<form:label path="city">City :</form:label>
					<form:input path="city" id="city" value="${supplier.city}" class="form-control" autocomplete="off" placeholder="City"/>
					<small id="citychk"></small>
				</div>
			</div>	
			<div class="col-md-4">	
				<div class="form-group">
					<form:label path="mobileno">Mobile :</form:label>
					<form:input path="mobileno" id="mobileno" value="${supplier.mobileno}" class="form-control" autocomplete="off" placeholder="Mobile"/>
					<small id="mobilnochk"></small>
				</div>
			</div>
			<div class="col-md-4">	
				<div class="form-group">
					<form:label path="email">Email :</form:label>
					<form:input path="email" id="email" value="${supplier.email}" class="form-control" autocomplete="off" placeholder="Email"/>
					<small id="emailchk"></small>
				</div>
			</div>	
				<button class="btn btn-primary" id="savebtn" type="submit">Save</button>
			</form:form>
		</div>
		<div class="row1">
			<div class="col-md-12">
				<h3>Supplier List</h3>
				<c:if test="${!empty suppliers }">
					<table id="tbid" class="table table-striped display table-hover" style="width:100%;">
						<thead>
							<tr>
								<th>Sid</th>
								<th>Supplier Name</th>							
								<th>GSTIN</th>
								<th>PAN</th>
								<th>Address</th>
								<th>Pincode</th>
								<th>State</th>
								<th>City</th>
								<th>Mobile</th>
								<th>Email</th>
								<th>Actions</th>
							</tr>
						</thead>
						<c:forEach items="${suppliers}" var="supplier">
							<tr>
								<td><c:out value="${supplier.supid}"/></td>
								<td><c:out value="${supplier.suppliername}"/></td>
								<td><c:out value="${supplier.gstinno}"/></td>
								<td><c:out value="${supplier.panno}"/></td>								
								<td><c:out value="${supplier.address}"/></td>
								<td><c:out value="${supplier.pincode}"/></td>
								<td><c:out value="${supplier.state.statename}"/></td>
								<td><c:out value="${supplier.city}"/></td>
								<td><c:out value="${supplier.mobileno}"/></td>
								<td><c:out value="${supplier.email}"/></td>
								<td><a href="editsupplier?supid=${supplier.supid}">Edit</a> | <a href="deletesupplier?supid=${supplier.supid}">Delete</a></td>
							</tr>
						</c:forEach>	
					</table>
				</c:if>
			</div>
		</div>
	</div>
	<script src="<c:url value="/resources/js/suppliers.js"/>"></script>
</body>
</html>