/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.rest.controllers;

import com.aceade.corpolc.inventory.model.request.ChangeOrderStatusRequest;
import com.aceade.corpolc.inventory.model.request.NewItemRequest;
import com.aceade.corpolc.inventory.model.request.NewOrderRequest;
import com.aceade.corpolc.inventory.model.supplies.Item;
import com.aceade.corpolc.inventory.model.base.Role;
import com.aceade.corpolc.inventory.model.supplies.Order;
import com.aceade.corpolc.inventory.services.SupplyService;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Interface to manage supplies.
 * @author philip
 */
@RestController
@RequestMapping("/supplies")
public class SupplyController {
    
    private static final Logger LOGGER = LogManager.getLogger(SupplyController.class);
    
    @Inject
    private SupplyService supplyService;
    
    @RequestMapping(value="/item", method=RequestMethod.GET)
    public Item getItem(@RequestParam(value="id", required=true) long itemId) {
        return supplyService.getItem(itemId);
    }
    
    @RequestMapping(value="/item/all", method = RequestMethod.GET)
    public List<Item> viewAllItems() {
		return supplyService.getAllItems();
	}
    
    @RequestMapping(value="/item/search", method = RequestMethod.GET)
    public List<Item> searchForItems(@RequestParam(value="name", required=true) String name) {
        return supplyService.getItemsWithName(name);
    }
    
    @Secured({Role.ROLE_FULL_ADMIN})
    @RequestMapping(value="/item", method=RequestMethod.POST)
    public void addItem(@RequestBody NewItemRequest newItem, HttpServletRequest req) {
        LOGGER.info("User [" + req.getRemoteUser() + "] adding new item [" + newItem + "]");
        supplyService.addItem(newItem);
    }
    
    @RequestMapping(value="/site", method=RequestMethod.GET)
    public Map<Item, Integer> getSiteSites(@RequestParam(value="siteId", required=true) long siteId){
        return supplyService.getSiteStocks(siteId);
    }
    
    @RequestMapping(value="/order", method=RequestMethod.GET)
    public Order getOrder(@RequestParam(value="id", required = true) long orderId) {
        return supplyService.viewOrder(orderId);
    }
    
    @RequestMapping(value="/order", method=RequestMethod.POST)
    public void placeOrder(@RequestBody NewOrderRequest newOrder, HttpServletRequest req) {
        LOGGER.info("User [" + req.getRemoteUser() + "] placing a new order");
        supplyService.addOrder(newOrder, req.getRemoteUser());
    }
    
    @RequestMapping(value="/order/status", method=RequestMethod.POST)
    public void changeOrderStatus(@RequestBody ChangeOrderStatusRequest changeOrder, HttpServletRequest req) {
        LOGGER.info("User [" + req.getRemoteUser() + "] changing status of order [" + changeOrder.getOrderId() + "]");
        supplyService.changeOrderStatus(changeOrder);
    }
    
    @RequestMapping(value="/order/user", method=RequestMethod.GET)
    public List<Order> viewUserOrders(@RequestParam String username, HttpServletRequest req){
        LOGGER.info("User [" + req.getRemoteUser() + "] viewing orders placed by [" + username + "]");
        // TODO: account for user roles here
        return supplyService.viewUsersOrders(username);
    }
    
    @RequestMapping(value="/order/mine", method=RequestMethod.GET)
    public List<Order> viewMyOrders(HttpServletRequest req) {
        return supplyService.viewUsersOrders(req.getRemoteUser());
    }
}
