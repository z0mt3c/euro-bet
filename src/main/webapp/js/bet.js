function isNumber(n) {
	return !isNaN(parseFloat(n)) && isFinite(n);
};

$(document).bind("mobileinit", function() {
	$.mobile.page.prototype.options.addBackBtn = true;
	
	$("a.editBet").live("click", function(e){
		e.stopImmediatePropagation();
		e.preventDefault();
		var li = $(this).parent().parent();
		li.find("div.placeBetContainer").show();
		li.find("div.readBetContainer").hide();
	});
	
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
			$.ajax({
				type : 'POST',
				url : '../bet',
				data : queryString,
				success : function(data) {
					if (data == "true") {
						var li = form.parent().parent();
						var scoreString = form.children("input[name=home]").val() + " : " + form.children("input[name=away]").val();
						li.find("div.placeBetContainer").hide();
						li.find("div.readBetContainer").show().find("span.readOnlyBetScore").text(scoreString);
					} else {
						alert("Sorry, please try again later.");
					}
				},
				error : function() {
					alert("Sorry, please try again later.");
				},
				dataType : 'text'
			});
		}
	});   
});
