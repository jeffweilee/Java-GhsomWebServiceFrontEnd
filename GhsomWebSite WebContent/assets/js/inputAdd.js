function processUrl()
{

	var parameters = location.search.substring(1).split("&");
	var temp = parameters[0].split("=");
	var ptext = unescape(temp[1]);
	temp = parameters[1].split("=");
	var pid = unescape(temp[1]);
	document.getElementById("selectedproject").innerHTML = ptext + "&nbsp; Project";
	document.getElementById("project_Id").value = pid;

}
function inputAdd()
{

	$("#input_file_name").change(function()
	{
		if ($("#input_file_name").val() != '' && $("#input_file_desc").val() != '')
		{
			$('#rootwizard').find('.pager .next').show();
			$('#rootwizard').find('.pager .finish').hide();

		}
	});

	$("#input_file_desc").change(function()
	{
		if ($("#input_file_desc").val() != '' && $("#input_file_name").val() != '')
		{
			$('#rootwizard').find('.pager .next').show();
			$('#rootwizard').find('.pager .finish').hide();
		}
	});
	$(".previous").click(function()
	{
		// $('#rootwizard').find('.pager .next').show();
		$('#rootwizard').find('.pager .finish').hide();
	});
}
function processUrlokBack()
{
	var parameters = location.search.substring(1).split("&");

	var temp1 = parameters[1].split("=");
	var temp2 = parameters[2].split("=");
	if (unescape(temp1[0]) == "ok" && unescape(temp2[0]) == "pn")
	{
		var ptext = unescape(temp1[1]);
		if (ptext == "1")
		{
			$("#alertTitle").html("message");
			$("#alertContent").html("<font color='green'>Input Add Success!!</font>");
			$("#alert").trigger("click");
			setTimeout(function()
			{
				location.replace("input.html?pn=" + temp2[1] + "&ok=1");
			}, 1500);
		}
		else
		{
			$("#alertTitle").html("message");
			$("#alertContent").html("<font color='red'>Input Add Failed!!</font>");
			$("#alert").trigger("click");
			setTimeout(function()
			{
				location.replace("input.html");
			}, 1500);
		}
	}
}