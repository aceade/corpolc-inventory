
(function(inventory, $){
    'use strict';
    
    var logResult = function(result) {
        $("#transcript > pre").append(result);
    };
    
    var logJson = function (json) {
        logResult(JSON.stringify(json, null, 4));
    };
    
    inventory.clearLogs = function() {
        $('#transcript > pre').empty();
    };
    
    inventory.toggleElement = function(id) {
        $(id).toggle('fold');
    };
    
    
    /**
     * 
     * @param {String} id - ID or CSS selector of the element
     * @returns {unresolved}
     */
    inventory.getElementValue = function(id) {
        return $(id).val();
    };
    
    inventory.makeAjaxCall = function(url, method, jsonData){
        $.ajax({
            url: url,
            method: method,
            username : sessionStorage.getItem("username"),
            password : sessionStorage.getItem("password"),
            contentType: "application/json",
            data: JSON.stringify(jsonData),
            processData: false,
            error: function(results){
                logResult(results);
            },
            statusCode: {
				200: function(results) {
					logJson(results);
				},
				401: function(results){
					alert("You are not authorised to make this request!");
					console.error(results);
				},
				404: function() {
					alert("Wrong URL");
				},
				500: function(results) {
					alert("A server error occurred!");
					console.error(results);
				}
			}
        });
    };
    
    inventory.login = function(){
        var username = document.getElementById("usernameInput").value;
        var password = document.getElementById("passwordInput").value;
        
        sessionStorage.setItem("username", username);
        sessionStorage.setItem("password", password);
        logResult("Logged in");
        
        $("#controls > label").show("fold").css("display: block");
    };
    
    
    return inventory;
    
})(window.inventory = window.inventory || {}, window.jQuery);
