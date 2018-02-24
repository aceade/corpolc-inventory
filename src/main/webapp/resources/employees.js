(function(employees, inventory){
    'use strict';
    
    var url = "/employees/";
    
    var getNewEmployeeDetails = function(){
        return {
            "name" : inventory.getElementValue("#newDroneName"),
            "departmentId" : inventory.getElementValue("#newDroneDept"),
            "birthday" : new Date(inventory.getElementValue("#newDroneBirthday")).getTime(),
            "siteId" : inventory.getElementValue("#newDroneSite"),
            "securityLevel" : inventory.getElementValue("#newDroneSecurity"),
            "salary" : inventory.getElementValue("#newDroneSalary")
        };
    };
    
    employees.getUser = function() {
        var id = inventory.getElementValue("#employeeIdInput");
        inventory.makeAjaxCall(url + "?id="+id, 'GET');
    };
    
    employees.getAllUsers = function() {
        inventory.makeAjaxCall(url+"all", 'GET');
    };
    
    employees.addUser = function() {
        var json = getNewEmployeeDetails();
        inventory.makeAjaxCall(url, 'POST', json);
    };
    
    employees.deleteUser = function() {
        if (window.confirm("Are you sure?")) {
            var id = inventory.getElementValue("#employeeIdInput");
            inventory.makeAjaxCall(url+"?id="+id, 'DELETE');
        }
        
    };
    
    return employees;
    
})(window.employees = window.employees || {}, window.inventory);
