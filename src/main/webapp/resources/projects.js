(function(projects, inventory){
    'use strict';
    
    var url = "/projects";
    
    projects.getAll = function(){
        inventory.makeAjaxCall(url+"/all", 'GET');
    };
    
    projects.get = function() {
        var id = inventory.getElementValue("#projectIdInput");
        inventory.makeAjaxCall(url+"?id="+id, 'GET');
    };
    
    projects.addNew = function() {
        alert("This isn't functional yet");
    };
    
    projects.setProjectStatus = function() {
        alert("This isn't functional yet");
    };
    
    return projects;
})(window.projects = window.projects || {}, window.inventory);
