var	Dtable;
$(document).ready(function(){
	    	var today = new Date().toISOString().split('T')[0];
	    	$('#date').attr('min',today);
	    	$('#date').attr('max',today);
	    	var d=0;
	    	var userId = $('#userId').val();
	    	alert("UserId" +userId);
	    	
			Dtable = $('#tblcreditdebit').DataTable({
				//	processing: true,
			    //  serverSide: true,
			
					ajax:{ 
						type : 'GET',
						url: '/user/creditDebitList?userId='+userId,
						dataSrc: ''
					},
			        columns: [
			            { data: 'cid' },
			            { data: 'openingbalance' },
			            { data: 'debittotal' },
			            { data: 'drawertotal' },
			            { data: 'debittotalplusdrawertotal' },
			            { data: 'todaybusiness' },
			            { data: 'date' },
			            { data: 'cid',
			            	'render': function ( cid, type, row) {
				            	return '<td><button class="btn btn-danger" onclick="deleteCreditDebitByCreditId('+cid+');">Delete</button> | <button class="btn btn-warning" onclick="getCreditDebitById('+cid+');" data-toggle="modal" data-target="#myModal">More</button></td></tr>';
			            	}
			            }
			        ]
			});
	        $("#addRow").click(function(){
	        	var dc='amount' + parseInt($('[data-val="amount"]').length +1);
	        	var dcAmount= parseInt($('[data-val="amount"]').length +1);
	        	var dc1='amountchk' + parseInt($('[data-val="amount"]').length +1);
	            var markup ="<tr><td><input type='checkbox' name='record'></td>"+
            		"<td><input type='text' name='amount' data-label="+dcAmount+" data-val='amount' data-class="+dc+" id='amount' class='form-control'/>" + "<small id='amountchk' class="+dc1+"></small></td>" +
            		"<td><input type='text' name='description' id='description' class='form-control' autocomplete='off'/></td></tr>";
	            
	            $("table#tableid tbody").append(markup);
	        });
	        $("#addRow").click(function() {
				$('input[name="amount"]').css({
					"font-size": "13px",
					"color": "#777",
					"border-radius": "20px",
					"border": "none !important"
				});
				
				$('input[name="description"]').css({
					"font-size": "13px",
					"color": "#777",
					"border-radius": "20px",
					"border": "none !important"
				});
			});
	        // remove selected table rows
	        $("#deleteRow").click(function(){
	            $("table tbody").find('input[name="record"]').each(function(){
	            	if($(this).is(":checked")){
	                    $(this).parents("tr").remove();
	                }
	            });
	        });
	        /*******Debit Total *****************/
	        $("#tableid").on('input','#amount',function(){
	        	var total = 0;
	        	$("#tableid #amount").each(function() {
					var get_value = $(this).val();
					if($.isNumeric(get_value)) {
						total += parseInt(get_value); 
					}
				});
	        	$("#debittotal").val(total);
	        });
	        
	        /*******Debit Total + Drawer Total *****************/
	        $('#debittotal').keyup(function() {
				debitPlusDrawer();
			});
			$('#drawertotal').keyup(function() {
				debitPlusDrawer();
			});
			function debitPlusDrawer() {
				var debit_total = $('#debittotal').val();
				var drawer_total = $('#drawertotal').val();
				var debit_Drawer = parseInt(debit_total) + parseInt(drawer_total);
				if($.isNumeric(debit_Drawer)) {
					$("#debittotalplusdrawertotal").val(debit_Drawer);
				}
			}
			
			/*************** DebitTotalPlus_DrawerTotal - Opening_Balance **********/
			
			$('#debittotalplusdrawertotal').keyup(function() {
				Debit_minus_OpeningBalace();
			});$('#openingbalance').keyup(function() {
				Debit_minus_OpeningBalace();
			});
			
			function Debit_minus_OpeningBalace() {
				var debit_Drawer = $('#debittotalplusdrawertotal').val();
				var opening_Balance = $('#openingbalance').val();
				var today_Business = parseInt(debit_Drawer) - parseInt(opening_Balance);
				if($.isNumeric(today_Business)) {
					$("#todaybusiness").val(today_Business);
				}
			}
	        
	        $('#openingBalancechk').hide();
	        $('#debittotalchk').hide();
	        $('#drawertotalchk').hide();
	        $('#debitplusdrawertotalchk').hide();
	        $('#todaybusinesschk').hide();
	        $('#amountchk').hide();
	       // $('#datechk').hide();
	        $('#amountchk').hide();
	        
	        var openingBalance_err = true;
	        var debittotal_err = true;
	        var drawertotal_err = true;
	        var debitdrawertotal_err = true;
	        var todaybusiness_err = true;
	        var amount_err = true;
	    //    var date_err = true;
	        var amount_err = true;
	        
	        $('#openingbalance').keyup(function() {
	        	openingBalance_chk();
			});
	        function openingBalance_chk() {
				var openingbalance = $('#openingbalance').val();
				var pattern = /^\d*(?:\.\d{1,2})?$/;
				if((openingbalance.length == '') || (openingbalance.length == null)) {
					$('#openingBalancechk').show();
					$('#openingBalancechk').html("Please Fill Opening Balance");
					$('#openingBalancechk').focus();
					$('#openingBalancechk').css("color","red");
					openingBalance_err = false;
					return false;
				} else {
					$('#openingBalancechk').hide();
				}
				if(!pattern.test(openingbalance)) {
					$('#openingBalancechk').show();
					$('#openingBalancechk').html("Please enter only numbers 0-9");
					$('#openingBalancechk').focus();
					$('#openingBalancechk').css("color","red");
					openingBalance_err = false;
					return false;
				} else {
					$('#openingBalancechk').hide();
				}
			}
	        
	        $('#debittotal').keyup(function() {
				debittotal_chk();
			});
	        
	        function debittotal_chk() {
				var debittotal = $('#debittotal').val();
				var pattern = /^\d*(?:\.\d{1,2})?$/;
				if((debittotal.length == '') || (debittotal.length == null)) {
					$('#debittotalchk').show();
					$('#debittotalchk').html("Please Fill Debit Total");
					$('#debittotalchk').focus();
					$('#debittotalchk').css("color","red");
					debittotal_err = false;
					return false;
				} else {
					$('#debittotalchk').hide();
				}
				if(!pattern.test(debittotal)) {
					$('#debittotalchk').show();
					$('#debittotalchk').html("Please enter only numbers 0-9");
					$('#debittotalchk').focus();
					$('#debittotalchk').css("color","red");
					debittotal_err = false;
					return false;
				} else {
					$('#debittotalchk').hide();
				}
			}
	        
	        $('#drawertotal').keyup(function () {
				drawertotal_chk();
			});
	        
	        function drawertotal_chk() {
	        	var drawertotal = $('#drawertotal').val();
				var pattern = /^\d*(?:\.\d{1,2})?$/;
				if((drawertotal.length == '') || (drawertotal.length == null)) {
					$('#drawertotalchk').show();
					$('#drawertotalchk').html("Please Fill Drawer Total");
					$('#drawertotalchk').focus();
					$('#drawertotalchk').css("color","red");
					drawertotal_err = false;
					return false;
				} else {
					$('#drawertotalchk').hide();
				}
				if(!pattern.test(drawertotal)) {
					$('#drawertotalchk').show();
					$('#drawertotalchk').html("Please enter only numbers 0-9");
					$('#drawertotalchk').focus();
					$('#drawertotalchk').css("color","red");
					drawertotal_err = false;
					return false;
				} else {
					$('#drawertotalchk').hide();
				}
			}
	        
	        $('#debittotalplusdrawertotal').keyup(function() {
				debitplusdrawertotal_chk();
			});
	        function debitplusdrawertotal_chk() {
	        	var debitplusdrawertotal = $('#debittotalplusdrawertotal').val();
				var pattern = /^\d*(?:\.\d{1,2})?$/;
				if((debitplusdrawertotal.length == '') || (debitplusdrawertotal.length == null)) {
					$('#debitplusdrawertotalchk').show();
					$('#debitplusdrawertotalchk').html("Please Fill Debit + Drawer Total");
					$('#debitplusdrawertotalchk').focus();
					$('#debitplusdrawertotalchk').css("color","red");
					debitdrawertotal_err = false;
					return false;
				} else {
					$('#debitplusdrawertotalchk').hide();
				}
				if(!pattern.test(debitplusdrawertotal)) {
					$('#debitplusdrawertotalchk').show();
					$('#debitplusdrawertotalchk').html("Please enter only numbers 0-9");
					$('#debitplusdrawertotalchk').focus();
					$('#debitplusdrawertotalchk').css("color","red");
					debitdrawertotal_err = false;
					return false;
				} else {
					$('#debitplusdrawertotalchk').hide();
				}
			}
	        
	        $('#todaybusiness').keyup(function() {
				todaybusiness_chk();
			});
	        function todaybusiness_chk() {
	        	var todyabusiness = $('#todaybusiness').val();
				var pattern = /^\d*(?:\.\d{1,2})?$/;
				if((todyabusiness.length == '') || (todyabusiness.length == null)) {
					$('#todaybusinesschk').show();
					$('#todaybusinesschk').html("Please Fill Today Business");
					$('#todaybusinesschk').focus();
					$('#todaybusinesschk').css("color","red");
					todaybusiness_err = false;
					return false;
				} else {
					$('#todaybusinesschk').hide();
				}
				if(!pattern.test(todyabusiness)) {
					$('#todaybusinesschk').show();
					$('#todaybusinesschk').html("Please enter only numbers 0-9");
					$('#todaybusinesschk').focus();
					$('#todaybusinesschk').css("color","red");
					todaybusiness_err = false;
					return false;
				} else {
					$('#todaybusinesschk').hide();
				}
			}
	       /* $('#date').keyup(function() {
				date_chk();
			});
	        
	        function date_chk() {
				var date = $('#date').val();
				var pattern = /^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/g;
				if((date.length == '') || (date.length == null)) {
					$('#datechk').show();
					$('#datechk').html("Please Fill Date");
					$('#datechk').focus();
					$('#datechk').css("color","red");
					date_err = false;
					return false;
				} else {
					$('#datechk').hide();
				}
				if(!pattern.test(date)) {
					$('#datechk').show();
					$('#datechk').html("Please enter valid date");
					$('#datechk').focus();
					$('#datechk').css("color","red");
					date_err = false;
					return false;
				} else {
					$('#datechk').hide();
				}
			}*/
	        $("#addRow").click(function() {
	        
	        	$('[data-val="amount"]').keyup(function () {
					var dclassname = $(this).attr('data-class');
					var dcAmountValue = $(this).attr('data-label');
					amount_chk(dclassname,dcAmountValue);
					//alert("ghfgh");
				});
			});
	        
	        
	        function amount_chk(dclassname,dcAmountValue) {
	        	var amount = $('[data-class="'+dclassname+'"]').val();
				var pattern = /^\d*(?:\.\d{1,2})?$/;
				if((amount.length == '') || (amount.length == null)) {
					$('.amountchk' +dcAmountValue).show();
					$('.amountchk' +dcAmountValue).html("Please Fill amount");
					$('.amountchk' +dcAmountValue).focus();
					$('.amountchk' +dcAmountValue).css({"color":"red","font-size":"12px"});
					amount_err = false;
					return false;
				} else {
					$('.amountchk' +dcAmountValue).hide();
				}
				if(!pattern.test(amount)) {
					$('.amountchk' +dcAmountValue).show();
					$('.amountchk' +dcAmountValue).html("Please enter only numbers 0-9");
					$('.amountchk' +dcAmountValue).focus();
					$('.amountchk' +dcAmountValue).css("color","red");
					amount_err = false;
					return false;
				} else {
					$('.amountchk').hide();
				}
			}
	        
	        $('#savebtn').click(function() {
	        	 openingBalance_err = true;
		         debittotal_err = true;
		         drawertotal_err = true;
		         debitdrawertotal_err = true;
		         todaybusiness_err = true;
		         date_err = true;
		         amount_err = true;
		         
		         openingBalance_chk();
		         debittotal_chk();
		         drawertotal_chk();
		         debitplusdrawertotal_chk();
		         todaybusiness_chk();
		         amount_chk(dclassname,dcAmountValue);
		       //  date_chk();
		         
		         if((openingBalance_err == true) && (debittotal_err == true) && (drawertotal_err == true) && (debitdrawertotal_err == true) && (todaybusiness_err == true) && (date_err == true) && (amount_err == true)) {
		        	return true; 
		         } else {
		        	 return false;
		         }
			});
 
	    }); 
	    var d=0;
    	/*function saveCreditDebit() {
    		var result = confirm("Once you will add this record you can't update ! after the saved record");
    		if (result) {
    			$.ajax({
        			type: "POST",
        			url: "/user/savecreditdebit",
        			dataType: "json",
        			data: {
        				openingbalance: $('#openingBalance').val(),
        				debittotalplusdrawertotal: $('#debitplusdrawertotal').val(),
        				todaybusiness: $('#todaybusiness').val(),
        				date: $('#date').val(),
        				debittotal: $('#debittotal').val(),
        				drawertotal: $('#drawertotal').val(),
        				amount: amount,
        				description: description,
        			},
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
    		
    	}*/
    	function deleteCreditDebitByCreditId(cid1) {
    		var result = confirm("Want to delete?");
    		if (result) {
    			$.ajax({
        			type : "GET",
        			url : "/user/deleteCreditDebitByCreditId?cid="+cid1,
        			
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
    	
     	function getCreditDebitById(cid) { 	
    			
    		    $.ajax({
    				type : "GET",
    				url : "/user/getCreditDebitByCreditId",
    				dataType: "json",
    	 		    data: {cid: cid },
    				success : function(data) {
    					var rows = '';
    					if($.trim(data)==""){
    						rows += '<tr><td colspan="10" style="text-align: center;">No data available</td></tr>';
    						$('#tableid').html(rows);
    					}
    					console.dir(data);
    						var ob = data.openingbalance;
    						var dpdt = data.debittotalplusdrawertotal;
    						var tb = data.todaybusiness;
    						var drt = data.drawertotal;
    						var det = data.date;
    						var dt = data.debittotal;
    						var dpdt1 = data.debittotalplusdrawertotal;
    						
    						$("#openingBal").val(ob);
    						$("#debitplusDrawertotal").val(dpdt);
    						$("#debitplusDrawertotal1").val(dpdt1);
    						$("#todayBusiness").val(tb);
    						$("#drawerTotal").val(drt);
    						$("#Date").val(det);
    						$("#debitTotal").val(dt);
    						var sr = 1; 
    						$.each(data.debit, function(index, item) {
	    						rows += '<tr><td >' + sr + '</td>';
	    						rows += '<td>' + item.did + '</td>';
	    						rows += '<td>' + item.amount + '</td>';
	    						rows += '<td>' + item.description + '</td>';
	    						sr++;
    						$('#tblCredit').html(rows);
    						});
    					},
    					error : function(xmlHttpRequest, textStatus, errorThrown) {
    						alert("error");
    					} 
    		    }); 
    	}
      