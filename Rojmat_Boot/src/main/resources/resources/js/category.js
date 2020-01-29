$(document).ready(function() {
	    var userId = $('#userId').val();
		Dtable = $('#tbid').DataTable({
			//	processing: true,
		    //  serverSide: true,
				ajax:{ 
					url: '/categories?userId='+userId,
					dataSrc: ''
				},
		        columns: [
		            { data: 'cid' },
		            { data: 'categoryname' },
		            { data: 'cid',
		            	'render': function ( cid, type, row) {
			            	return '<td><button class="btn btn-success" onclick="getCategoryById('+cid+');">Edit</button> | <button class="btn btn-danger" onclick="getdeleteCategoryById('+cid+');">Delete</button></td></tr>';
		            	}
		            }
		        ]
		});
	
	
	$('#categorynamechk').hide();
	var categoryName_err = true;
	
	$('#categoryname').keyup(function() {
		categoryName_chk();
	});
	
	function categoryName_chk() {
		var catName = $('#categoryname').val();
		if((catName.length == "") || (catName.length == null)) {
			$('#categorynamechk').show();
			$('#categorynamechk').html("Please fill Category Name");
			$('#categorynamechk').focus();
			$('#categorynamechk').css("color","red");
			categoryName_err = false;
			return false;
		} else {
			$('#categorynamechk').hide();
		}
	}
	
	$('#savebtn').click(function() {
		categoryName_err = true;
		categoryName_chk();
		if(categoryName_err == true) {
			return true;
		} else {
			return false;
		}
	});
});

function getCategoryById(cid) { 	
	
    $.ajax({
		type : "GET",
		url : "/user/getCetagoryById",
		dataType: "json",
		data: {cid: cid },
		success : function(data) {
			var rows = '';
			if($.trim(data)==""){
				rows += '<tr><td colspan="10" style="text-align: center;">No data available</td></tr>';
				$('#tableid').html(rows);
			}
			console.dir(data);
				var cid = data.cid;
				var catname = data.categoryname;
				
				$("#cid").val(cid);
				$("#categoryname").val(catname);
			},
			error : function(xmlHttpRequest, textStatus, errorThrown) {
				alert("error");
			} 
    }); 
}

function getdeleteCategoryById(cid1) {
	var result = confirm("Want to delete?");
	if (result) {
		$.ajax({
			type : "GET",
			url : "/user/deleteCategoryById?cid="+cid1,
			
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

/*function saveCreditDebit() {
	
		$.ajax({
			type: "POST",
			url: "/user/savecategory",
			dataType: "json",
			data: {
				categoryname: $('#categoryname').val(),
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
}*/