function isNumber(n) {
	return !isNaN(parseFloat(n)) && isFinite(n);
};

function placeBet(queryString) {
	$.ajax({
		type : 'POST',
		url : '../bet',
		data : queryString,
		success : function(data) {
			if (data == "true") {
				alert("saved");
			} else {
				alert("Sorry, please try again later.");
			}
		},
		error : function() {
			alert("Sorry, please try again later.");
		},
		dataType : 'text'
	});
};

$(document).bind("mobileinit", function() {
	$.mobile.page.prototype.options.addBackBtn = true;
	
	$("a.placeBet").live("click", function(e){ 
		e.stopImmediatePropagation();
		e.preventDefault();
		
		var form = $(this).parent();
		var queryString = form.serialize();
		var error = false;
		
		form.find("input").each(function(index) {
			var value = parseInt($(this).val());
			
			if (!isNumber($(this).val()) || !(value >= 0)) {
				$(this).css("border-color","#ff0000");
				error = true;
			} else {
				$(this).css("border-color","");
			}
		});
		
		if (!error) {
			placeBet(queryString);
		}
	});   
});
