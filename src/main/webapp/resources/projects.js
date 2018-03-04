(function(projects, inventory){
    'use strict';
    
    var url = "/projects";
    
    var getNewProjectDetails = function() {
        return {
            "title" : inventory.getElementValue("#newProjectTitle"),
            "summary" : inventory.getElementValue("#newProjectSummary"),
            "budget" : inventory.getElementValue("#newProjectBudget"),
            "securityLevel" : inventory.getElementValue("#newProjectSecurity")
        };
    };
    
    projects.getAll = function(){
        inventory.makeAjaxCall(url+"/all", 'GET');
    };
    
    projects.get = function() {
        var id = inventory.getElementValue("#projectIdInput");
        inventory.makeAjaxCall(url+"?id="+id, 'GET');
    };
    
    projects.add = function() {
        var json = getNewProjectDetails();
        inventory.makeAjaxCall(url, 'POST', json);
    };
    
    projects.setProjectStatus = function(newStatus) {
        var json = {
            "projectId" : inventory.getElementValue("#projectIdInput"),
            "newProjectStatus" : newStatus
        };
        inventory.makeAjaxCall(url + "/status", "POST", json);
    };
    
    return projects;
})(window.projects = window.projects || {}, window.inventory);
