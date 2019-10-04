$(document).ready(function() {
	Dtable = $('#tbid').DataTable({
		//	processing: true,
	    //  serverSide: true,
			ajax:{ 
				url: '/salesPayments',
				dataSrc: ''
			},
	        columns: [
	            { data: 'sid' },
	            { data: 'invoiceDate' },
	            { data: 'paymentdate' },
	            { data: 'partyName' },
	            { data: 'invoiceNo' },
	            { data: 'invoiceAmount' },
	            { data: 'paidAmount' },
	            { data: 'unpaidAmount' },
	            { data: 'paymenttype.paymentMode' },
	            { data: 'chequeNoOrTransactionId' },
	            { data: 'sid',
	            	'render': function ( sid, type, row) {
		            	return '<td><button class="btn btn-success" onclick="getSalesPaymentById('+sid+');">Edit</button> | <button class="btn btn-danger" onclick="deleteSalesPaymentById('+sid+');">Delete</button></td></tr>';
	            	}
	            }
	        ]
	});
	 $('#invoicedatechk').hide();
	 $('#invoicenochk').hide();
     $('#partynamechk').hide();
     $('#invoiceamountchk').hide();
     $('#paidamountchk').hide();
     $('#unpaidamountchk').hide();
     $('#chequeortranchk').hide();
     $('#paytypechk').hide();
     
     var invoiceno_err = true;
     var partyname_err = true;
     var invoiceamount_err = true;
     var paidamount_err = true;
     var unpaidamount_err = true;
     var chequeortran_err = true;
     var paymenttype_err = true;
     var invoicenoDate_err = true;
     
     /************************* Invoice No **********************************/
     $('#invoiceNo').keyup(function() {
			invoiceNo_chk();
		});
     function invoiceNo_chk() {
    	 var invoiceNo = $('#invoiceNo').val();
	   	 if((invoiceNo.length == '') || (invoiceNo.length == null)) {
			$('#invoicenochk').show();
			$('#invoicenochk').html("Invoice No is require field");
			$('#invoicenochk').focus();
			$('#invoicenochk').css("color","red");
			invoiceno_err = false;
			return false;
		} else {
			$('#invoicenochk').hide();
		}
	}
     /************************* Invoice Date **********************************/
     
     function invoiceDate_chk() {
    	 var invoiceNoDate = $('#invoiceDate').text();
    	 alert(invoiceNoDate.length);
	   	 if((invoiceNo.val() == '') || (invoiceNo.val() == null)) {
			$('#invoicedatechk').show();
			$('#invoicedatechk').html("please select invoice date");
			$('#invoicedatechk').focus();
			$('#invoicedatechk').css("color","red");
			invoicenoDate_err = false;
			return false;
		} else {
			$('#invoicedatechk').hide();
		}
	}
     
     $('#partyName').keyup(function() {
			partyName_chk();
		});
	  function partyName_chk() {
	 	 var partyName = $('#partyName').val();
		   	 if((partyName.length == '') || (partyName.length == null)) {
				$('#partynamechk').show();
				$('#partynamechk').html("Partyname is require field");
				$('#partynamechk').focus();
				$('#partynamechk').css("color","red");
				partyname_err = false;
				return false;
			} else {
				$('#partynamechk').hide();
			}
		}
	  
	  $('#invoiceAmount').keyup(function() {
			invoiceAmount_chk();
		});
	  function invoiceAmount_chk() {
	 	 var invoiceAmount = $('#invoiceAmount').val();
	 	 var pattern = /^\s*(\+|-)?((\d+(\.\d+)?)|(\.\d+))\s*$/;
		   	 if((invoiceAmount.length == '') || (invoiceAmount.length == null)) {
				$('#invoiceamountchk').show();
				$('#invoiceamountchk').html("Invoice amount is require field");
				$('#invoiceamountchk').focus();
				$('#invoiceamountchk').css("color","red");
				invoiceamount_err = false;
				return false;
			} else {
				$('#invoiceamountchk').hide();
			}
		   	if(!pattern.test(invoiceAmount)) {
		   		$('#invoiceamountchk').show();
				$('#invoiceamountchk').html("please enter valid invoice amount");
				$('#invoiceamountchk').focus();
				$('#invoiceamountchk').css("color","red");
				invoiceamount_err = false;
				return false;
		   	} else {
				$('#invoiceamountchk').hide();
		   	}
		}
	  
	  $('#paidAmount').keyup(function() {
			paidAmount_chk();
		});
	  function paidAmount_chk() {
	 	 var paidAmount = $('#paidAmount').val();
	 	 var pattern = /^\s*(\+|-)?((\d+(\.\d+)?)|(\.\d+))\s*$/;
		   	 if((paidAmount.length == '') || (paidAmount.length == null)) {
				$('#paidamountchk').show();
				$('#paidamountchk').html("Paid amount is require field");
				$('#paidamountchk').focus();
				$('#paidamountchk').css("color","red");
				paidamount_err = false;
				return false;
			} else {
				$('#paidamountchk').hide();
			}
		   	if(!pattern.test(paidAmount)) {
		   		$('#paidamountchk').show();
				$('#paidamountchk').html("please enter valid paid amount");
				$('#paidamountchk').focus();
				$('#paidamountchk').css("color","red");
				paidamount_err = false;
				return false;
		   	} else {
				$('#paidamountchk').hide();
		   	}
		}
	  
	  $('#unpaidAmount').keyup(function() {
			unpaidAmount_chk();
		});
	  function unpaidAmount_chk() {
	 	 var unpaidAmount = $('#unpaidAmount').val();
	 	 var pattern = /^\s*(\+|-)?((\d+(\.\d+)?)|(\.\d+))\s*$/;
		   	 if((unpaidAmount.length == '') || (unpaidAmount.length == null)) {
				$('#unpaidamountchk').show();
				$('#unpaidamountchk').html("Un-Paid amount is require field");
				$('#unpaidamountchk').focus();
				$('#unpaidamountchk').css("color","red");
				unpaidamount_err = false;
				return false;
			} else {
				$('#unpaidamountchk').hide();
			}
		   	if(!pattern.test(unpaidAmount)) {
		   		$('#unpaidamountchk').show();
				$('#unpaidamountchk').html("please enter valid un-paid amount");
				$('#unpaidamountchk').focus();
				$('#unpaidamountchk').css("color","red");
				unpaidamount_err = false;
				return false;
		   	} else {
				$('#unpaidamountchk').hide();
		   	}
		}
	  
	  $('#invoiceAmount').keyup(function() {
		  unpaidAmount();
		});
	  $('#paidAmount').keyup(function() {
		  unpaidAmount();
		});
		
		function unpaidAmount() {
			var invoiceAmount = $('#invoiceAmount').val();
			var paidAmount = $('#paidAmount').val();
			var unpaidAmount = parseFloat(invoiceAmount) - parseFloat(paidAmount);
			if($.isNumeric(unpaidAmount)) {
				$("#unpaidAmount").val(unpaidAmount);
			}
		}
		/***************** Payment Mode ****************************************/
		$("#paymenttype").change(function () {
	    	 paymenttype_chk();
	     });
	     function paymenttype_chk() {
	    	 if($('#paymenttype option:selected').val() == 0) {
				$('#paytypechk').show();
				$('#paytypechk').html("Please select payment mode");
				$('#paytypechk').focus();
				$('#paytypechk').css("color","red");
				paymenttype_err = false;
				return false;
			} else {
				$('#paytypechk').hide();
			}
	     }	
	  
	  $('#myForm').submit(function() {
		  	  invoiceno_err = true;
		      partyname_err = true;
		      invoiceamount_err = true;
		      paidamount_err = true;
		      unpaidamount_err = true;
		      paymenttype_err = true;
		      invoicenoDate_err = true;
		      
		      invoiceNo_chk();
		      partyName_chk();
		      invoiceAmount_chk();
		      paidAmount_chk();
		      unpaidAmount_chk();
		      paymenttype_chk();
		    //  invoiceDate_chk();
		      if((invoiceno_err == true) && (partyname_err == true) && (invoiceamount_err == true) && (paidamount_err == true) && (unpaidamount_err = true) && (paymenttype_err == true)) {
		    	  return true;
		      }else {
		    	  return false;
		      }
	  });
});

function getSalesPaymentById(sid) { 	
	
    $.ajax({
		type : "GET",
		url : "/user/getSalesPaymentById",
		dataType: "json",
		data: {sid: sid },
		success : function(data) {
			var rows = '';
			if($.trim(data)==""){
				rows += '<tr><td colspan="10" style="text-align: center;">No data available</td></tr>';
				$('#tableid').html(rows);
			}
			console.dir(data);
				var sid = data.sid;
				var invoiceDate = data.invoiceDate;
				var partyName = data.partyName;
				var invoiceNo = data.invoiceNo;
				var paymentdate = data.paymentdate;
				var invoiceAmount = data.invoiceAmount;
				var paidAmount = data.paidAmount;
				var unpaidAmount = data.unpaidAmount;
				var chequeNoOrTransactionId = data.chequeNoOrTransactionId;
				var paymenttype = data.paymenttype.payid;
				
				
				$("#sid").val(sid);
				$("#invoiceDate").val(invoiceDate);
				$("#partyName").val(partyName);
				$("#invoiceNo").val(invoiceNo);
				$("#paymentdate").val(paymentdate);
				$("#invoiceAmount").val(invoiceAmount);
				$("#paidAmount").val(paidAmount);
				$("#unpaidAmount").val(unpaidAmount);
				$("#chequeNoOrTransactionId").val(chequeNoOrTransactionId);
				$("#paymenttype").val(paymenttype);
			},
			error : function(xmlHttpRequest, textStatus, errorThrown) {
				alert("error");
			} 
    }); 
}

function deleteSalesPaymentById(sid) {
	var result = confirm("Want to delete Sales Payment details ?");
	if (result) {
		$.ajax({
			type : "GET",
			url : "/user/deleteSalesPaymentById?sid="+sid,
			
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