function VerifySignUp()
{
	$('#contact-form').validate({
		rules : {
			accNew : {
				required : true,
				NoSpace : true
			},
			pwNew : {
				required : true,
				NoSpace : true
			}
		},
		highlight : function(element)
		{
			$(element).closest('.control-group').removeClass('success').addClass('error');
		},
		success : function(element)
		{
			element.text('').addClass('valid').closest('.control-group').removeClass('error').addClass('success');
		}
	});
	jQuery.validator.addMethod("NoSpace", function(value, element)
	{
		return value.indexOf(" ") < 0 && value != "";
	}, "No space allowed !");
}