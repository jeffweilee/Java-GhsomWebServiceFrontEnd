function AddProperty()
{
	var pn = $.trim($('#pp_name').val());

	if (!$("div").hasClass("error") && pn != "" && $("#norm_Input_Vectors").val() != "#")
	{
		var labels_Only = 0;
		if ($('#labels_Only').is(':checked'))
		{
			labels_Only = 1;
		}

		property_Name = $.trim($('#pp_name').val());
		tau_1 = $.trim($('#tau_1').val());
		tau_2 = $.trim($('#tau_2').val());
		expand_Cycles = $.trim($('#expand_Cycles').val());
		init_Learnrate = $.trim($('#init_Learnrate').val());
		nr = $.trim($('#nr').val());
		html_Prefix = $.trim($('#html_Prefix').val());
		datafile_Extension = $.trim($('#datafile_Extension').val());
		random_Seed = $.trim($('#random_Seed').val());
		norm_Input_Vectors = $.trim($('#norm_Input_Vectors').val());
		init_X_Size = $.trim($('#init_X_Size').val());
		init_Y_Size = $.trim($('#init_Y_Size').val());
		labels_Num = $.trim($('#labels_Num').val());

		labels_Threshold = $.trim($('#labels_Threshold').val());
		attr_Filter = $.trim($('#attr_Filter').val());
		property_Desc = $.trim($('#pp_desc').val());

		var reqJson = {
			property_Name : $.trim($('#pp_name').val()),
			tau_1 : (tau_1 == '') ? '0.25' : tau_1,
			tau_2 : (tau_2 == '') ? '0.1' : tau_2,
			expand_Cycles : (expand_Cycles == '') ? '25' : expand_Cycles,
			init_Learnrate : (init_Learnrate == '') ? '0.3' : init_Learnrate,
			nr : (nr == '') ? '0.0006' : nr,
			html_Prefix : (html_Prefix == '') ? 'map' : html_Prefix,
			datafile_Extension : (datafile_Extension == '') ? '' : datafile_Extension,
			random_Seed : (random_Seed == '') ? '123456789' : random_Seed,
			norm_Input_Vectors : (norm_Input_Vectors == '') ? 'NONE' : norm_Input_Vectors,
			init_X_Size : (init_X_Size == '') ? '2' : init_X_Size,
			init_Y_Size : (init_Y_Size == '') ? '2' : init_Y_Size,
			labels_Num : (labels_Num == '') ? '5' : labels_Num,
			labels_Only : labels_Only,
			labels_Threshold : (labels_Threshold == '') ? '0' : labels_Threshold,
			attr_Filter : (attr_Filter == '') ? '1' : attr_Filter,
			property_Desc : (property_Desc == '') ? null : property_Desc

		};
		VerifyName();
		if ($("#pn").val() == "0")
		{
			$("#pn").val('');
			alert("The name has been used!");
		}
		else
		{
			$.ajax({
				url : location.protocol + "//" + location.host + "/XGhsomServiceWeb/AddProperty",
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
					// alert("Property Add Success!");
					$("#modal-body").html("<font color='green'>Property Add Success!!</font>");

					setTimeout(function()
					{
						location.reload();
					}, 2000);
				}

			});
		}
	}
	else
	{
		alert("Please fix all fields with error!");
	}

	return false;
}
function isAlpha(str)
{
	return /^[a-zA-Z()]+$/.test(str);
}