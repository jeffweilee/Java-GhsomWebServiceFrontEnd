function VerifyProject()
{
	$('#contact-form').validate({
		rules : {
			project_name : {
				required : true,
				NoSpace : true,
				NameCheck : true,
				minlength : 1
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

		url : location.protocol + "//" + location.host + "/XGhsomServiceWeb/GetProject",
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
			var col = "project_Name";
			var ary = response.projects;
			for ( var p = 0; p < ary.length; p++)
			{
				if ($.trim(ary[p][col]) == $.trim($("#project_name").val()))
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
