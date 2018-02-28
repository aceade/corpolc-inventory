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
    
    /**
     * Retrieve a site using a query that's prone to SQL injection
     * @returns {undefined}
     */
    sites.getWithWeakQuery = function() {
        var id = inventory.getElementValue("#sqlInjectionId");
        inventory.makeAjaxCall(baseUrl+"/get/with/weak/query?id="+id, 'GET');
    };
    
    return sites;
    
})(window.sites = window.sites || {}, window.inventory);
