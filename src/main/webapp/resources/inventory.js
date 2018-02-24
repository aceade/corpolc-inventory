
(function(inventory, $){
    'use strict';
    
    var logResult = function(result) {
        $("#transcript").add("p").text(JSON.stringify(result, null, 4));
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
            success: function(results){
                logResult(results);
            },
            error: function(results){
                logResult(results);
            }
        });
    };
    
    inventory.login = function(){
        var username = document.getElementById("usernameInput").value;
        var password = document.getElementById("passwordInput").value;
        
        sessionStorage.setItem("username", username);
        sessionStorage.setItem("password", password);
        
    };
    
    return inventory;
    
})(window.inventory = window.inventory || {}, window.jQuery);
