function VerifySession()
{
	$('#contact-form').validate({
		rules : {
			sessionname : {
				required : true,
				NoSpace : true,
				NameCheck : true,
				minlength : 1
			},
			selectproject : {
				required : true,
				selectcheck : true
			},
			selectinput : {
				required : true,
				selectcheck : true
			},
			selectproperty : {
				required : true,
				selectcheck : true
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
	// end document.ready
}


function VerifyName()
{
	$('#submit').attr('disabled', false);
	$.ajax({

		url : location.protocol + "//" + location.host + "/XGhsomServiceWeb/GetSession",
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
			var col = "session_Name";
			var ary = response.sessions;
			for ( var p = 0; p < ary.length; p++)
			{
				if ($.trim(ary[p][col]) == $.trim($("#sessionname").val()))
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

function PreInputSelect(id)
{
	$('#selectinput').empty();
	var tag = "#" + id;
	var pid = $(tag).val();
	if (id == "select")
	{
		if (!$.isNumeric($('#select').val()))
		{
			pid = 0;
			$('#selectproject').val('#');
			$('#select').val('#');
			/* alert("Remeber to Choose a Project!"); */
		}
	}

	GetInputSelect('selectinput', pid);

}
