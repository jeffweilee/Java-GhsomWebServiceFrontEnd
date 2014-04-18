function inputAddWizard()
{
	$('#rootwizard').bootstrapWizard({
		onTabShow : function(tab, navigation, index)
		{
			var $total = navigation.find('li').length;
			var $current = index + 1;
			var $percent = ($current / $total) * 100;
			$('#rootwizard').find('.bar').css({
				width : $percent + '%'
			});

			// If it's the last tab then hide the last button and show the
			// finish instead
			if ($current >= $total)
			{

				if ($("#input_file_name").val() != '' && $("#input_file_desc").val() != '' && $.trim($("#input_name").val()) != "")
				{
					$('#rootwizard').find('.pager .next').hide();
					$('#rootwizard').find('.pager .finish').show();

				}
				else
				{
					alert("Please fix required field!");
				}
			}
			else
			{

				$('#rootwizard').find('.pager .next').hide();
				$('#rootwizard').find('.pager .finish').hide();
			}

		}
	});
	/*
	 * $('#rootwizard .finish').click(function() { alert('Finished!, Starting
	 * over!'); $('#rootwizard').find("a[href*='tab1']").trigger('click'); });
	 */
}
