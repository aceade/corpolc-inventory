(function(supplies, inventory){
	"use strict";
	
	var baseUrl = "/supplies";
	
	supplies.getAllItems = function() {
		inventory.makeAjaxCall(baseUrl + "/item/all", "GET", "");
	};
	
	supplies.searchForItem = function() {
		var name = window.prompt("Please enter the name");
		inventory.makeAjaxCall(baseUrl + "/item/search?name=" + name, "GET", "");
	};
	
	supplies.addItem = function() {
		alert("Not implemented yet");
	};
	
	supplies.getSiteStocks = function() {
		alert("Not implemented yet");
	};
	
	supplies.placeOrder = function() {
		var json = {
			siteId : parseInt(window.prompt("Please enter the site ID")),
			orderDate : Date.now(),
			orderItems: {2 : 1}
		};
		inventory.makeAjaxCall(baseUrl + "/order", "POST", json);
	};
	
	supplies.viewOrder = function() {
		var id = parseInt(window.prompt("Please enter the order ID"));
		inventory.makeAjaxCall(baseUrl + "/order?id="+id, "GET", "");
	}
	
	return supplies;
})(window.supplies = window.supplies || {}, window.inventory);
