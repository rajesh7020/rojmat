$(document).ready(function() {
	Dtable = $('#tbid').DataTable({
		//	processing: true,
	    //  serverSide: true,
			ajax:{ 
				url: '/suppliers',
				dataSrc: ''
			},
	        columns: [
	            { data: 'supid' },
	            { data: 'suppliername' },
	            { data: 'gstinno' },
	            { data: 'panno' },
	            { data: 'address' },
	            { data: 'pincode' },
	            { data: 'state.statename' },
	            { data: 'city' },
	            { data: 'mobileno' },
	            { data: 'email' },
	            { data: 'bankname' },
	            { data: 'branchname' },
	            { data: 'accountno' },
	            { data: 'ifscode' },
	            { data: 'supid',
	            	'render': function ( supid, type, row) {
		            	return '<td><button class="btn btn-success" onclick="getSupplierById('+supid+');">Edit</button> | <button class="btn btn-danger" onclick="deleteSupplierById('+supid+');">Delete</button></td></tr>';
	            	}
	            }
	        ]
	});
	
		$('#suppliernamechk').hide();
		$('#gstinnochk').hide();
		$('#pannochk').hide();
		$('#addresschk').hide();
		$('#pincodechk').hide();
		$('#citychk').hide();
		$('#mobilnochk').hide();
		$('#emailchk').hide();
		$('#statechk').hide();
		
		var name_err = true;
		var gst_err = true;
		var pan_err = true;
		var address_err = true;
		var pin_err = true;
		var city_err = true;
		var mobile_err = true;
		var email_err = true;
		var state_err = true;
		
		$('#suppliername').keyup(function() {
			custname_chk();
		});
		function custname_chk() {
			var custname = $('#suppliername').val();
			if((custname.length == " ") || (custname.length == null)) {
				$('#suppliernamechk').show();
				$('#suppliernamechk').html("Please fill supplier name");
				$('#suppliernamechk').focus();
				$('#suppliernamechk').css("color","red");
				name_err = false;
				return false;
			} else {
				$('#suppliernamechk').hide();
			}
		}
		
		$('#gstinno').keyup(function() {
			gstin_chk();
		});
		function gstin_chk() {
			var gstinno = $('#gstinno').val();
			var pattern = /^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$/;
			if((gstinno.length == " ") || (gstinno.length == null)) {
				$('#gstinnochk').show();
				$('#gstinnochk').html("Please fill GSTIN");
				$('#gstinnochk').focus();
				$('#gstinnochk').css("color","red");
				gst_err = false;
				return false;
			} else {
				$('#gstinnochk').hide();
			}
			if(!pattern.test(gstinno)) {
				$('#gstinnochk').show();
				$('#gstinnochk').html("GSTIN is not Valid");
				$('#gstinnochk').focus();
				$('#gstinnochk').css("color","red");
				gst_err = false;
				return false;
			} else {
				$('#gstinnochk').hide();
			}
		}
		
		$('#panno').keyup(function() {
			panno_chk();
		});
		function panno_chk() {
			var panno = $('#panno').val();
			var pattern = /^[A-Z]{3}[ABCFGHLJPT][A-Z][0-9]{4}[A-Z]$/;
			if((panno.length == " ") || (panno.length == null)) {
				$('#pannochk').show();
				$('#pannochk').html("Please fill PAN");
				$('#pannochk').focus();
				$('#pannochk').css("color","red");
				pan_err = false;
				return false;
			} else {
				$('#pannochk').hide();
			}
			if(!pattern.test(panno)) {
				$('#pannochk').show();
				$('#pannochk').html("PAN is not Valid");
				$('#pannochk').focus();
				$('#pannochk').css("color","red");
				pan_err = false;
				return false;
			} else {
				$('#pannochk').hide();
			}
		}
		
		$('#address').keyup(function() {
			address_check();
		});
		
		function address_check() {
			var address_val = $('#address').val();
			if((address_val.length == '') || (address_val.length == null)) {
				$('#addresschk').show();
				$('#addresschk').html("Please Fill the Address");
				$('#addresschk').focus();
				$('#addresschk').css("color","red");
				address_err = false;
				return false;
			} else {
				$('#addresschk').hide();
			}
			
			if((address_val.length < 15) || (address_val.length > 50)) {
				$('#addresschk').show();
				$('#addresschk').html("Address length must be between 15 and 50");
				$('#addresschk').focus();
				$('#addresschk').css("color","red");
				address_err = false;
				return false;
			} else {
				$('#addresschk').hide();
			}
		}
		
		$('#pincode').keyup(function() {
			pincode_chk();
		});
		function pincode_chk() {
			var pincode = $('#pincode').val();
			var pattern = /[1-9][0-9]{5}$/;
			if((pincode.length == " ") || (pincode.length == null)) {
				$('#pincodechk').show();
				$('#pincodechk').html("Please fill Pin code");
				$('#pincodechk').focus();
				$('#pincodechk').css("color","red");
				pin_err = false;
				return false;
			} else {
				$('#pincodechk').hide();
			}
			if(!pattern.test(pincode)) {
				$('#pincodechk').show();
				$('#pincodechk').html("Pin code is not Valid");
				$('#pincodechk').focus();
				$('#pincodechk').css("color","red");
				pin_err = false;
				return false;
			} else {
				$('#pincodechk').hide();
			}
		}
		
		$('#city').keyup(function() {
			city_chk();
		});
		function city_chk() {
			var city = $('#city').val();
			if((city.length == " ") || (city.length == null)) {
				$('#citychk').show();
				$('#citychk').html("Please fill city");
				$('#citychk').focus();
				$('#citychk').css("color","red");
				city_err = false;
				return false;
			} else {
				$('#citychk').hide();
			}
		}
		
		$('#mobileno').keyup(function() {
			mobile_chk();
		});
		
		function mobile_chk() {
			var mobile_val = $('#mobileno').val();
			var pattern = /^\d*(?:\.\d{1,2})?$/;
			if((mobile_val.length == " ") || (mobile_val.length == null)) {
				$('#mobilnochk').show();
				$('#mobilnochk').html("Please Fill the Mobile Number");
				$('#mobilnochk').focus();
				$('#mobilnochk').css("color","red");
				mobile_err = false;
				return false;
			} else {
				$('#mobilnochk').hide();
			}
			
			if(!pattern.test(mobile_val)) {
				$('#mobilnochk').show();
				$('#mobilnochk').html("Mobile Number is not Valid");
				$('#mobilnochk').focus();
				$('#mobilnochk').css("color","red");
				mobile_err = false;
				return false;
			} else {
				$('#mobilnochk').hide();
			}
			
			if((mobile_val.length < 10) || (mobile_val.length > 10)) {
				$('#mobilnochk').show();
				$('#mobilnochk').html("Mobile Number Length must be 10 Digit");
				$('#mobilnochk').focus();
				$('#mobilnochk').css("color","red");
				mobile_err = false;
				return false;
			} else {
				$('#mobilnochk').hide();
			}
		}
		
		$('#email').keyup(function() {
			email_chk();
		});
		
		function email_chk() {
			var email_val = $('#email').val();
			var pattern = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i;
			if((email_val.length == '') || (email_val.length == null)) {
				$('#emailchk').show();
				$('#emailchk').html("Please Fill the Email");
				$('#emailchk').focus();
				$('#emailchk').css("color","red");
				email_err = false;
				return false;
			} else {
				$('#emailchk').hide();
			}
			
			if(!pattern.test(email_val)) {
				$('#emailchk').show();
				$('#emailchk').html("Email is not Valid");
				$('#emailchk').focus();
				$('#emailchk').css("color","red");
				email_err = false;
				return false;
			} else {
				$('#emailchk').hide();
			}
		}
		
		/************************************ State *************************************/
		$('#state').change(function () {
	   	 state_chk();
	    });
	    function state_chk() {
	   	 if($('#state option:selected').val() == 0) {
				$('#statechk').show();
				$('#statechk').html("Please select state");
				$('#statechk').focus();
				$('#statechk').css("color","red");
				state_err = false;
				return false;
			} else {
				$('#statechk').hide();
			}
	    }	
		
		$('#savebtn').click(function() {
			 name_err = true;
			 gst_err = true;
			 pan_err = true;
			 address_err = true;
			 pin_err = true;
			 city_err = true;
			 mobile_err = true;
			 email_err = true;
			 state_err = true;
			 
			 custname_chk();
			 gstin_chk();
			 panno_chk();
			 address_check();
			 pincode_chk();
			 city_chk();
			 mobile_chk();
			 email_chk();
			 state_chk();
			 
			 if((name_err == true) && (gst_err == true) && (pan_err == true) && (address_err == true) && (pin_err == true) && (city_err == true) && (mobile_err == true) && (email_err == true) && (state_err == true)) {
				 return true;
			 } else {
				 return false;
			 }
		});
});	

function getSupplierById(supid) { 	
	
    $.ajax({
		type : "GET",
		url : "/user/getSupplierById",
		dataType: "json",
		data: {supid: supid },
		success : function(data) {
			var rows = '';
			if($.trim(data)==""){
				rows += '<tr><td colspan="10" style="text-align: center;">No data available</td></tr>';
				$('#tableid').html(rows);
			}
			console.dir(data);
				var supid = data.supid;
				var suppliername = data.suppliername;
				var gstinno = data.gstinno;
				var panno = data.panno;
				var address = data.address;
				var pincode = data.pincode;
				var state = data.state.stateid;
				var city = data.city;
				var mobileno = data.mobileno;
				var email = data.email;
				var bankname = data.bankname;
				var branchname = data.branchname;
				var accountno = data.accountno;
				var ifscode = data.ifscode;
				
				$("#supid").val(supid);
				$("#suppliername").val(suppliername);
				$("#gstinno").val(gstinno);
				$("#panno").val(panno);
				$("#address").val(address);
				$("#pincode").val(pincode);
				$("#state").val(state);
				$("#city").val(city);
				$("#mobileno").val(mobileno);
				$("#email").val(email);
				$("#bankname").val(bankname);
				$("#branchname").val(branchname);
				$("#accountno").val(accountno);
				$("#ifscode").val(ifscode);
			},
			error : function(xmlHttpRequest, textStatus, errorThrown) {
				alert("error");
			} 
    }); 
}

function deleteSupplierById(supid) {
	var result = confirm("Want to delete Supplier ?");
	if (result) {
		$.ajax({
			type : "GET",
			url : "/user/deleteSupplierById?supid="+supid,
			
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