$(document).ready(function(){
	var userId = $('#userId').val();
	Dtable = $('#tbid').DataTable({
		//	processing: true,
	    //  serverSide: true,
			ajax:{ 
				url: '/products?userId='+userId,
				dataSrc: ''
			},
	        columns: [
	            { data: 'pid' },
	            { data: 'name' },
	            { data: 'hsncode' },
	            { data: 'price' },
	            { data: 'category.categoryname' },
	            { data: 'unit.unitname' },
	            { data: 'gst.percentage' },
	            { data: 'sgst' },
	            { data: 'cgst' },
	            { data: 'igst' },
	            { data: 'pid',
	            	'render': function ( pid, type, row) {
		            	return '<td><button class="btn btn-success" onclick="getProductById('+pid+');">Edit</button> | <button class="btn btn-danger" onclick="deleteProductById('+pid+');">Delete</button></td></tr>';
	            	}
	            }
	        ]
	});
		
		 $('#namechk').hide();
	     $('#hsncodechk').hide();
	     $('#pricechk').hide();
	     $('#categorychk').hide();
	     $('#gstchk').hide();
	     $('#unitchk').hide();
	     $('#gstchk').hide();
	     
	     var name_err = true;
	     var hsncode_err = true;
	     var price_err = true;
	     var category_err = true;
	     var gst_err = true;
	     var unit_err = true;
	     /********************Product Name *********************/
	     $('#name').keyup(function() {
			name_chk();
		});
	     
	     function name_chk() {
	    	 var pname = $('#name').val();
		   	 if((pname.length == '') || (pname.length == null)) {
				$('#namechk').show();
				$('#namechk').html("Product name require");
				$('#namechk').focus();
				$('#namechk').css("color","red");
				name_err = false;
				return false;
			} else {
				$('#namechk').hide();
			}
		}
	     /********************HSN CODE *********************/
	     $('#hsncode').keyup(function() {
	 		hsncode_chk();
	 	});
	     
	     function hsncode_chk() {
	    	 var pname = $('#hsncode').val();
		   	 if((pname.length == '') || (pname.length == null)) {
				$('#hsncodechk').show();
				$('#hsncodechk').html("HSN Code is require");
				$('#hsncodechk').focus();
				$('#hsncodechk').css("color","red");
				hsncode_err = false;
				return false;
			} else {
				$('#hsncodechk').hide();
			}
		}
	     /********************Price *********************/
	     $('#price').keyup(function() {
	  		price_chk();
	  	});
	     
	     function price_chk() {
	    	 var price = $('#price').val();
		   	 if((price.length == '') || (price.length == null)) {
				$('#pricechk').show();
				$('#pricechk').html("Price is require");
				$('#pricechk').focus();
				$('#pricechk').css("color","red");
				price_err = false;
				return false;
			} else {
				$('#pricechk').hide();
			}
		}
	     /********************Category *********************/
	     $("#category").change(function () {
	    	 category_chk();
	     });
	     function category_chk() {
	    	 //alert($('#category option:selected').val());
	    	if($('#category option:selected').val() == 0) {
				$('#categorychk').show();
				$('#categorychk').html("Please select category");
				$('#categorychk').focus();
				$('#categorychk').css("color","red");
				category_err = false;
				return false;
			} else {
				$('#categorychk').hide();
			}
	     }	
	     
	     /********************Unit *********************/
	     $("#unit").change(function () {
	    	 unit_chk();
	     });
	     function unit_chk() {
	    	 if($('#unit option:selected').val() == 0) {
				$('#unitchk').show();
				$('#unitchk').html("Please select unit");
				$('#unitchk').focus();
				$('#unitchk').css("color","red");
				unit_err = false;
				return false;
			} else {
				$('#unitchk').hide();
			}
	     }	
	     
	     /********************GST *********************/
	     
	     function gst_chk() {
			if($('#gst option:selected').val() == 0) {
				$('#gstchk').show();
				$('#gstchk').html("Please select gst");
				$('#gstchk').focus();
				$('#gstchk').css("color","red");
				gst_err = false;
				return false;
			} else {
				$('#gstchk').hide();
			}
	     }	
	     
	     $("#gst").change(function () {
	    	 gst_chk();
	    	 var gst = $('#gst option:selected').text();
	    	 var gsts = gst / 2;
	    	 if($.isNumeric(gsts)) {
	    		 $('#sgst').val(gsts);
		    	 $('#cgst').val(gsts);
				}
	     });
	     
	     function dropDown() {
			var gst = $('#gst option:selected').val();
			alert(gst);
		}
	     
	     $('#myForm').submit(function() {
	    	 name_err = true;
	         hsncode_err = true;
	         price_err = true;
	         category_err = true;
	         unit_err = true;
	         gst_err = true;
	         
	         name_chk();
	         hsncode_chk();
	         price_chk();
	         category_chk();
	         unit_chk();
	         gst_chk();
	         
	         if((name_err == true) && (hsncode_err == true) && (price_err == true) && (category_err == true) && (unit_err == true) && (gst_err == true)) {
	        	 return true;
	         } else {
	        	 return false;
	         }
	     });
	 });

function getProductById(pid) { 	
	
    $.ajax({
		type : "GET",
		url : "/user/getProductById",
		dataType: "json",
		data: {pid: pid },
		success : function(data) {
			var rows = '';
			if($.trim(data)==""){
				rows += '<tr><td colspan="10" style="text-align: center;">No data available</td></tr>';
				$('#tableid').html(rows);
			}
			console.dir(data);
				var pid = data.pid;
				var name = data.name;
				var hsncode = data.hsncode;
				var price = data.price;
				var categoryname = data.category.cid;
				var unitname = data.unit.unitid;
				var percentage = data.gst.gstid;
				var sgst = data.sgst;
				var cgst = data.cgst;
				var igst = data.igst;
				
				$("#pid").val(pid);
				$("#name").val(name);
				$("#hsncode").val(hsncode);
				$("#price").val(price);
				$("#category").val(categoryname);
				$("#unit").val(unitname);
				$("#gst").val(percentage);
				$("#sgst").val(sgst);
				$("#cgst").val(cgst);
				$("#igst").val(igst);
			},
			error : function(xmlHttpRequest, textStatus, errorThrown) {
				alert("error");
			} 
    }); 
}

function deleteProductById(pid) {
	var result = confirm("Want to delete product ?");
	if (result) {
		$.ajax({
			type : "GET",
			url : "/user/deleteProductById?pid="+pid,
			
			success : function(data) {
				console.log(data);
				if(data == "success"){
					Dtable.ajax.reload();    					
				}
			},
			error : function(xmlHttpRequest, textStatus, errorThrown) {
				alert("error");
			}
		});
	}
}