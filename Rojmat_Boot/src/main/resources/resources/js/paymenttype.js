$(document).ready(function() {
	var userId = $('#userId').val();
	Dtable = $('#tbid').DataTable({
		//	processing: true,
	    //  serverSide: true,
			ajax:{ 
				url: '/paymenttypes?userId='+userId,
				dataSrc: ''
			},
	        columns: [
	            { data: 'payid' },
	            { data: 'paymentMode' },
	            { data: 'payid',
	            	'render': function ( payid, type, row) {
		            	return '<td><button class="btn btn-success" onclick="getPaymentTypeById('+payid+');">Edit</button> | <button class="btn btn-danger" onclick="deletePaymentTypeById('+payid+');">Delete</button></td></tr>';
	            	}
	            }
	        ]
	});
	
	$('#paymentmodechk').hide();
	var paymentmode_err = true;
	
	$('#paymentMode').keyup(function() {
		paymenttype_chk();
	});
	
	function paymenttype_chk() {
		var paymenttype = $('#paymentMode').val();
		if((paymenttype.length == "") || (paymenttype.length == null)) {
			$('#paymentmodechk').show();
			$('#paymentmodechk').html("Please fill payment type");
			$('#paymentmodechk').focus();
			$('#paymentmodechk').css("color","red");
			paymentmode_err = false;
			return false;
		} else {
			$('#paymentmodechk').hide();
		}
	}
	
	$('#savebtn').click(function() {
		paymentmode_err = true;
		paymenttype_chk();
		if(paymentmode_err == true) {
			return true;
		} else {
			return false;
		}
	});
});


function getPaymentTypeById(payid) { 	
	
    $.ajax({
		type : "GET",
		url : "/user/getPaymentTypeById",
		dataType: "json",
		data: {payid: payid },
		success : function(data) {
			var rows = '';
			if($.trim(data)==""){
				rows += '<tr><td colspan="10" style="text-align: center;">No data available</td></tr>';
				$('#tableid').html(rows);
			}
			console.dir(data);
				var payid = data.payid;
				var paymentMode = data.paymentMode;
				
				$("#payid").val(payid);
				$("#paymentMode").val(paymentMode);
			},
			error : function(xmlHttpRequest, textStatus, errorThrown) {
				alert("error");
			} 
    }); 
}

function deletePaymentTypeById(payid) {
	var result = confirm("Want to delete PaymentMode?");
	if (result) {
		$.ajax({
			type : "GET",
			url : "/user/deletePaymentTypeById?payid="+payid,
			
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