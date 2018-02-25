(function(sites, inventory){
    'use strict';
    
    var baseUrl = "/sites";
    
    var getNewSiteDetails = function(){
        return {
            "country" : inventory.getElementValue("#newSiteCountry"),
            "region" : inventory.getElementValue("#newSiteRegion"),
            "postalAddress" : inventory.getElementValue("#newSiteAddress"),
            "securityLevel" : inventory.getElementValue("#newSiteSecurity")
        };
    };
    
    sites.getAll = function() {
        inventory.makeAjaxCall(baseUrl+"/all", 'GET');
    };
    
    sites.get = function() {
        var id = inventory.getElementValue("#siteIdInput");
        inventory.makeAjaxCall(baseUrl+"?id="+id, 'GET');
    };
    
    sites.add = function(){
        var json = getNewSiteDetails();
        inventory.makeAjaxCall(baseUrl+"/new", 'POST', json);
    };
    
    return sites;
    
})(window.sites = window.sites || {}, window.inventory);
