(function(supplies, inventory){
	"use strict";
	
	var baseUrl = "/supplies";
	
	supplies.getAllItems = function() {
		inventory.makeAjaxCall(baseUrl + "/item/all");
	}
	
	return supplies;
})(window.supplies = window.supplies || {}, window.inventory);
