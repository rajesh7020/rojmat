$(document).ready(function() {
	$('#tbid').DataTable({
		"paging": true, 
	    "order": [0, 'desc'],
	}); 
	 $('#invoicenochk').hide();
     $('#partynamechk').hide();
     $('#invoiceamountchk').hide();
     $('#paidamountchk').hide();
     $('#unpaidamountchk').hide();
     $('#chequeortranchk').hide();
     
     var invoiceno_err = true;
     var partyname_err = true;
     var invoiceamount_err = true;
     var paidamount_err = true;
     var unpaidamount_err = true;
     var chequeortran_err = true;
     
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
	  
	  $('#myForm').submit(function() {
		  	  invoiceno_err = true;
		      partyname_err = true;
		      invoiceamount_err = true;
		      paidamount_err = true;
		      unpaidamount_err = true;
		      
		      invoiceNo_chk();
		      partyName_chk();
		      invoiceAmount_chk();
		      paidAmount_chk();
		      unpaidAmount_chk();
		      
		      if((invoiceno_err == true) && (partyname_err == true) && (invoiceamount_err == true) && (paidamount_err == true) && (unpaidamount_err = true)) {
		    	  return true;
		      }else {
		    	  return false;
		      }
	  });
});