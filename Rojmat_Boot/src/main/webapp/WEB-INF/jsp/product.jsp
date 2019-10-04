<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Product</title>	
	<link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet"/>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="<c:url value="/resources/css/product.css"/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.9.0/jquery.validate.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.5/validator.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<script src="<c:url value="/resources/js/product.js"/>"></script>
</head>
<body>
	<!-- Navigation Bar -->	
	<jsp:include page="userheader.jsp"/>	
	<h1 style="text-align:center;">Products Details</h1>
	<div class="container">
		<div class="row">
			<form:form action="${pageContext.request.contextPath}/user/saveProduct" method="POST" id="myForm" modelAttribute="command">
				<h3>Product</h3>
				<div class="col-md-4">
					<div class="form-group">
						<form:label path="pid">Product Id :</form:label>
						<form:input path="pid" id="pid" value="${product.pid}" class="form-control" autocomplete="off" placeholder="Product Id" readonly="true" />
						<small id="productidhk"></small>
					</div>
				</div>
				<div class="col-md-4">	
					<div class="form-group">
						<form:label path="name">Product Name :</form:label>
						<form:input path="name" id="name" value="${product.name}" class="form-control" autocomplete="off" placeholder="Product Name"/>
						<small id="namechk"></small>
					</div>
				</div>	
				<div class="col-md-4">	
					<div class="form-group">
						<form:label path="hsncode">HSN Code :</form:label>
						<form:input path="hsncode" id="hsncode" value="${product.hsncode}" class="form-control" autocomplete="off" placeholder="HSN Code"/>
						<small id="hsncodechk"></small>
					</div>
				</div>
				<div class="col-md-4">	
					<div class="form-group">
						<form:label path="price">Price :</form:label>
						<input name="price" id="price" value="${product.price}" class="form-control" autocomplete="off" placeholder="Price"/>
						<small id="pricechk"></small>
					</div>
				</div>
				<div class="col-md-4">	
					<div class="form-group">
						<form:label path="category.cid">Product Category: </form:label>
						<form:select path="category.cid" cssStyle="width:225px;" id="category" name="category">
						<option value="0">Select a Type</option>
						<c:forEach items="${categories}" var="category">
							<option value="${category.cid}">${category.categoryname}</option>
						</c:forEach>
						</form:select>
						<small id="categorychk"></small>
					</div>
				</div>
				<div class="col-md-4">	
					<div class="form-group">
						<form:label path="unit.unitid">Unit: </form:label>
						<form:select path="unit.unitid" cssStyle="width:310px;" id="unit" name="unit">
						<option value="0">Select a Type</option>
						<c:forEach items="${units}" var="unit">
							<option value="${unit.unitid}">${unit.unitname}</option>
						</c:forEach>
					</form:select>
					<small id="unitchk"></small>
					</div>
				</div>
				<div class="col-md-4">	
					<div class="form-group">
						<form:label path="gst.gstid">GST: </form:label>
						<form:select path="gst.gstid" cssStyle="width:315px;" id="gst" name="gst">
							<option value="0" >Select a Type</option>
							<c:forEach items="${gsts}" var="gst">
								<option value="${gst.gstid}">${gst.percentage}</option>
							</c:forEach>
						</form:select>
						<small id="gstchk"></small>
					</div>
				</div>	
				<div class="col-md-4">	
					<div class="form-group">
						<form:label path="sgst">SGST :</form:label>
						<form:input path="sgst" id="sgst" value="${product.sgst}" class="form-control" autocomplete="off" placeholder="SGST" readonly="true"/>
					</div>
				</div>
				<div class="col-md-4">	
					<div class="form-group">
						<form:label path="cgst">CGST :</form:label>
						<form:input path="cgst" id="cgst" value="${product.cgst}" class="form-control" autocomplete="off" placeholder="CGST" readonly="true"/>
					</div>
				</div>
				<div class="col-md-4">	
					<div class="form-group">
						<form:label path="igst">IGST :</form:label>
						<form:input path="igst" id="igst" value="${product.igst}" class="form-control" autocomplete="off" placeholder="Price"/>
					</div>
				</div>
				<input type="submit" class="btn btn-primary" id="savebtn" value="Save"/>
			</form:form>
		</div>
		<div class="row1">	
			<div class="col-md-12">
				<h3>Product List</h3>
				<c:if test="${!empty products }">
					<table id="tbid" class="table table-striped display table-hover" style="width:100%;">
						<thead>
							<tr>
								<th>Pid</th>
								<th>Name</th>
								<th>HSN Code</th>
								<th>Price</th>
								<th>Category</th>
								<th>Unit</th>
								<th>GST</th>
								<th>SGST</th>
								<th>CGST</th>
								<th>IGST</th>
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