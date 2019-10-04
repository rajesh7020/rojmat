$(document).ready(function() {
	Dtable = $('#tbid').DataTable({
		//	processing: true,
	    //  serverSide: true,
			ajax:{ 
				url: '/gsts',
				dataSrc: ''
			},
	        columns: [
	            { data: 'gstid' },
	            { data: 'percentage' },
	            { data: 'gstid',
	            	'render': function ( gstid, type, row) {
		            	return '<td><button class="btn btn-success" onclick="getGstById('+gstid+');">Edit</button> | <button class="btn btn-danger" onclick="deleteGstById('+gstid+');">Delete</button></td></tr>';
	            	}
	            }
	        ]
	});
	
	$('#percentagechk').hide();
	var percentage_err = true;
	
	$('#percentage').keyup(function() {
		percentage_chk();
	});
	
	function percentage_chk() {
		var percentage = $('#percentage').val();
		if((percentage.length == "") || (percentage.length == null)) {
			$('#percentagechk').show();
			$('#percentagechk').html("Please fill GST Percentage");
			$('#percentagechk').focus();
			$('#percentagechk').css("color","red");
			percentage_err = false;
			return false;
		} else {
			$('#percentagechk').hide();
		}
	}
	
	$('#savebtn').click(function() {
		percentage_err = true;
		percentage_chk();
		if(percentage_err == true) {
			return true;
		} else {
			return false;
		}
	});
});

function getGstById(gstid) { 	
	
    $.ajax({
		type : "GET",
		url : "/user/getGstById",
		dataType: "json",
		data: {gstid: gstid },
		success : function(data) {
			var rows = '';
			if($.trim(data)==""){
				rows += '<tr><td colspan="10" style="text-align: center;">No data available</td></tr>';
				$('#tableid').html(rows);
			}
			console.dir(data);
				var gstid = data.gstid;
				var percentage = data.percentage;
				
				$("#gstid").val(gstid);
				$("#percentage").val(percentage);
			},
			error : function(xmlHttpRequest, textStatus, errorThrown) {
				alert("error");
			} 
    }); 
}

function deleteGstById(gstid) {
	var result = confirm("Want to delete?");
	if (result) {
		$.ajax({
			type : "GET",
			url : "/user/deleteGstById?gstid="+gstid,
			
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