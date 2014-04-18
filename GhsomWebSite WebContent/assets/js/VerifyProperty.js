function VerifyProperty()
{
	$('#contact-form').validate({
		rules : {
			pp_name : {
				required : true,
				NoSpace : true,
				NameCheck : true,
				minlength : 1
			},
			tau_1 : {
				number : true
			},
			tau_2 : {
				number : true
			},
			expand_Cycles : {
				digits : true
			},
			init_Learnrate : {
				number : true
			},
			nr : {
				number : true
			},
			html_Prefix : {
			// alpha : true
			},
			datafile_Extension : {
			// alpha : true
			},
			norm_Input_Vectors : {
				selectcheck : true
			},
			random_Seed : {
				digits : true
			},
			init_X_Size : {
				digits : true
			},
			init_Y_Size : {
				digits : true
			},
			labels_Num : {
				digits : true
			},
			labels_Threshold : {
				number : true
			},
			attr_Filter : {
				digits : true
			}

		},
		highlight : function(element)
		{
			$(element).closest('.control-group').removeClass('success').addClass('error');
		},
		success : function(element)
		{
			element.text('OK!').addClass('valid').closest('.control-group').removeClass('error').addClass('success');
		}
	});
	jQuery.validator.addMethod('selectcheck', function(value)
	{
		return (value != '#');
	}, "Selection required");

	jQuery.validator.addMethod("NoSpace", function(value, element)
	{
		return value.indexOf(" ") < 0 && value != "";
	}, "No space allowed !");
	jQuery.validator.addMethod("alpha", function(value, element)
	{
		return this.optional(element) || /^[A-Za-z]+$/.test(value);
		// just ascii letters
	}, "Alpha only");
	jQuery.validator.addMethod('NameCheck', function(value)
	{
		VerifyName();
		if ($("#n").val() == "0")
		{
			$("#n").val('');
			return false;
		}
		else
		{
			return true;
		}
	}, "The name has been used!");
}// end document.ready

function VerifyName()
{
	$('#submit').attr('disabled', false);
	$.ajax({

		url : location.protocol + "//" + location.host + "/XGhsomServiceWeb/GetProperty",
		type : "POST",
		contentType : "application/json",
		datatype : "json",
		async : false,
		data : "",
		error : function(xhr)
		{
			alert(xhr.status + ":" + xhr.statusText);
		},
		success : function(response)
		{
			var col = "property_Name";
			var ary = response.properties;
			for ( var p = 0; p < ary.length; p++)
			{
				if ($.trim(ary[p][col]) == $.trim($("#pp_name").val()))
				{
					$("#n").val("0");
					// return false;
					break;
				}
			}

			// return true;

		}
	});
}
