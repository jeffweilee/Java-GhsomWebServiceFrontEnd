function VerifyInputadd()
{
	$('#contact-form').validate({
		rules : {
			input_name : {
				required : true,
				NoSpace : true,
				NameCheck : true,
				minlength : 1
			},

		},
		highlight : function(element)
		{
			$(element).closest('.control-group').removeClass('success').addClass('error');
			$('#rootwizard').find('.pager .next').hide();
			$('#rootwizard').find('.pager .finish').hide();
		},
		success : function(element)
		{
			element.text('OK!').addClass('valid').closest('.control-group').removeClass('error').addClass('success');
			$('#rootwizard').find('.pager .next').show();
			$('#rootwizard').find('.pager .finish').hide();
				
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

}

function VerifyName()
{
	$('#submit').attr('disabled', false);
	$.ajax({

		url : location.protocol + "//" + location.host + "/XGhsomServiceWeb/GetInput",
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
			var col = "input_Name";
			var ary = response.inputs;
			for ( var p = 0; p < ary.length; p++)
			{
				if ($.trim(ary[p][col]) == $.trim($("#input_name").val()))
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
