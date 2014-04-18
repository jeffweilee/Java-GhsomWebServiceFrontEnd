function LogIn()
{
	var ac = $.trim($('#acc').val());
	var pw = $.trim($('#pw').val());
	$('#acc').val("");
	$('#pw').val("");

	if (ac != '' && pw != '')
	{
		var reqJson = {
			acc : ac,
			pw : pw
		};

		$.ajax({
			url : location.protocol + "//" + location.host + "/XGhsomServiceWeb/LogIn",
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
				if (response.login == 1)
				{
					// alert("Log in Success!");
					$("#logout").html("Logout " + response.acc);
					$("#logout").show();
					$("#login").hide();

					$("#loginput").removeClass("slideout");
					$("#loginput").hide();

					$("#loginMsg").html("Logged in as " + response.acc);
					$("#loginMsg").show();
					$("#loginMsg").addClass("slideout_in");

					$("#alertTitle").html("Message");
					$("#alertContent").html("<font color='green'>Log in Successfully!</font>");
					$("#alert").trigger('click');
					setTimeout(function()
					{
						$(".close").trigger("click");
					}, 1000);
					setTimeout(function()
					{
						location.replace("index.html");
					}, 1500);

				}
				else
				{
					// alert("Log in Failed!");
					$("#alertTitle").html("Message");
					$("#alertContent").html("<font color='red'>Log in Failed!</font>");
					$("#alert").trigger('click');
				}
			}

		});

	}
}
function LogInStatus()
{
	var loginmsg = [];
	$.ajax({
		url : location.protocol + "//" + location.host + "/XGhsomServiceWeb/LogIn",
		type : "GET",
		contentType : "application/text",
		asnyc : "false",
		datatype : "text",
		error : function(xhr)
		{
			alert(xhr.status + ":" + xhr.statusText);
		},
		success : function(response)
		{
			loginmsg = response.split(",");

			if (loginmsg[0] == 1)
			{
				$("#logout").html("Logout " + loginmsg[1]);
				$("#logout").show();
				$("#login").hide();

				$("#loginput").removeClass("slideout");
				$("#loginput").hide();

				$("#loginMsg").html("Logged in as " + loginmsg[1]);
				$("#loginMsg").show();
				$("#loginMsg").addClass("slideout_in");

			}
			else
			{
				$("#linklist").find("a").attr("href", "login.html");
			}

		}

	});

	return true;

}