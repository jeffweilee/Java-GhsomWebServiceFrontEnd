function LogOut()
{
	$("#logout").html("");
	$("#logout").removeClass("slideout");
	$("#logout").hide();
	$("#login").show();

	$("#loginMsg").html("");
	$("#loginMsg").hide();

	$("#loginput").addClass("slideout");

	var reqJson = {
		logout : 1
	};
	$.ajax({
		url : location.protocol + "//" + location.host + "/XGhsomServiceWeb/LogOut",
		type : "POST",
		contentType : "application/json",
		datatype : "json",
		data : JSON.stringify(reqJson),
		error : function(xhr)
		{
			alert(xhr.status + ":" + xhr.statusText);
			location.reload();
		},
		success : function(response)
		{
			if (response == "0")
			{
				// alert("Log out Successfully!");
				$("#alertTitle").html("Message");
				$("#alertContent").html("<font color='blue'>Log out Successfully!</font>");
				$("#alert").trigger('click');

				setTimeout(function()
				{
					location.replace("index.html");
				}, 2000);

			}
			else
			{
				// alert("Log out Failed!");
				$("#alertTitle").html("Message");
				$("#alertContent").html("<font color='red'>Log out Failed!</font>");
				$("#alert").trigger('click');
			}
		}

	});

}