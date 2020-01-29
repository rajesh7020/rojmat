$(document).ready(function() {
	 var userId = $('#userId').val();
		Dtable = $('#tbid').DataTable({
			//	processing: true,
		    //  serverSide: true,
				ajax:{ 
					url: '/units?userId='+userId,
					dataSrc: ''
				},
		        columns: [
		            { data: 'unitid' },
		            { data: 'unitname' },
		            { data: 'unitid',
		            	'render': function ( unitid, type, row) {
			            	return '<td><button class="btn btn-success" onclick="getUnitById('+unitid+');">Edit</button> | <button class="btn btn-danger" onclick="deleteUnitById('+unitid+');">Delete</button></td></tr>';
		            	}
		            }
		        ]
		});
	
	$('#unitnamechk').hide();
	var unitname_err = true;
	
	$('#unitname').keyup(function() {
		unitName_chk();
	});
	
	function unitName_chk() {
		var unitName = $('#unitname').val();
		if((unitName.length == "") || (unitName.length == null)) {
			$('#unitnamechk').show();
			$('#unitnamechk').html("Please fill Unit Name");
			$('#unitnamechk').focus();
			$('#unitnamechk').css("color","red");
			unitname_err = false;
			return false;
		} else {
			$('#unitnamechk').hide();
		}
	}
	
	$('#savebtn').click(function() {
		unitname_err = true;
		unitName_chk();
		if(unitname_err == true) {
			return true;
		} else {
			return false;
		}
	});
});

function getUnitById(unitid) { 	
	
    $.ajax({
		type : "GET",
		url : "/user/getUnitById",
		dataType: "json",
		data: {unitid: unitid },
		success : function(data) {
			var rows = '';
			if($.trim(data)==""){
				rows += '<tr><td colspan="10" style="text-align: center;">No data available</td></tr>';
				$('#tableid').html(rows);
			}
			console.dir(data);
				var unitid = data.unitid;
				var unitname = data.unitname;
				
				$("#unitid").val(unitid);
				$("#unitname").val(unitname);
			},
			error : function(xmlHttpRequest, textStatus, errorThrown) {
				alert("error");
			} 
    }); 
}

function deleteUnitById(unitid) {
	var result = confirm("Want to delete?");
	if (result) {
		$.ajax({
			type : "GET",
			url : "/user/deleteUnitById?unitid="+unitid,
			
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