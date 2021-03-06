$(document).ready(function()
	{
	if ($("#alertSuccess").text().trim() == "")
	{
	$("#alertSuccess").hide();
	}
	$("#alertError").hide();
	});
	
// SAVE ============================================
	$(document).on("click", "#btnSave", function(event)
	{
		// Clear alerts---------------------
		$("#alertSuccess").text("");
		$("#alertSuccess").hide();
		$("#alertError").text("");
		$("#alertError").hide();
		
		// Form validation-------------------
	    var status = validateItemForm();
		if (status != true)
		{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
		}
		
		 // If valid------------------------
		 var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
		 $.ajax( 
		 { 
		 url : "ProductAPI", 
		 type : type, 
		 data : $("#formItem").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onItemSaveComplete(response.responseText, status); 
		 } 
 	}); 
});
		
// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
	$("#hidItemIDSave").val($(this).data("pid"));
	$("#ItemCode").val($(this).closest("tr").find('td:eq(0)').text());
	$("#ItemName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#Description").val($(this).closest("tr").find('td:eq(2)').text());
	$("#Researcher").val($(this).closest("tr").find('td:eq(3)').text());
	$("#Email").val($(this).closest("tr").find('td:eq(4)').text());
	});
	
// DELETE===========================================
	$(document).on("click", ".btnRemove", function(event)
	{ 
	 $.ajax( 
	 { 
	 url : "ProductAPI", 
	 type : "DELETE", 
	 data : "ItemID=" + $(this).data("pid"),
	 dataType : "text", 
	 complete : function(response, status) 
	 { 
	 onItemDeleteComplete(response.responseText, status); 
	 } 
	 }); 
});
// CLIENT-MODEL================================================================
function validateItemForm()
	{
	// Code
	if ($("#ItemCode").val().trim() == "")
	{
	return "Insert Item Code.";
	}
	// Name
	if ($("#ItemName").val().trim() == "")
	{
	return "Insert Item Name.";
	}

	// Description-------------------------------
	if ($("#Description").val().trim() == "")
	{
	return "Insert Item Description.";
	}
	
	// Researcher-------------------------------
	if ($("#Researcher").val().trim() == "")
	{
	return "Insert Item Researcher.";
	}
	
	// Email-------------------------------
	if ($("#Email").val().trim() == "")
	{
	return "Insert Item Email.";
	}
	
	return true;
}

function onItemSaveComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully saved."); 
	 $("#alertSuccess").show();
	 $("#divItemsGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
	 $("#alertError").text(resultSet.data); 
	 $("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
	 $("#alertError").text("Error while saving."); 
	 $("#alertError").show(); 
	 } else
	 { 
	 $("#alertError").text("Unknown error while saving.."); 
	 $("#alertError").show(); 
	 } 
	 $("#hidItemIDSave").val(""); 
	 $("#formItem")[0].reset(); 
}

function onItemDeleteComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully deleted."); 
	 $("#alertSuccess").show();
	 $("#divItemsGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
	 $("#alertError").text(resultSet.data); 
	 $("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
	 $("#alertError").text("Error while deleting."); 
	 $("#alertError").show(); 
	 } else
	 { 
	 $("#alertError").text("Unknown error while deleting.."); 
	 $("#alertError").show(); 
 } 
}




