/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.database;

/**
 *
 * @author philip
 */
public class Queries {
    
    /**
     * EMPLOYEE related
     */
    
    public static final String SELECT_EMPLOYEE = "SELECT e.*, s.* FROM employees as e, sites as s WHERE e.id = ? AND s.id = e.workplace;";
    public static final String SELECT_ALL_EMPLOYEES = "SELECT e.*, s.* FROM employees as e, sites AS s where e.workplace = s.id ORDER BY e.id;";
    public static final String COUNT_EMPLOYEES = "SELECT COUNT(id) FROM employees";
    public static final String SELECT_EMPLOYEES_BY_SITE = "SELECT e.*, s.* FROM employees as e, sites AS s WHERE e.workplace = ?;";
    public static final String ADD_EMPLOYEE = "INSERT INTO employees (id, name, birthday, department_type, workplace, security_level, salary, currently_employed) VALUES (?, ?, ?, ?, ?, ?, ?, true)";
    public static final String DELETE_EMPLOYEE = "UPDATE employees SET current = false WHERE id = ?";
    public static final String GET_EMPLOYEE_WORKPLACE = "SELECT * FROM sites WHERE id = (SELECT workplace FROM employees WHERE id = ?)";
    public static final String GET_EMPLOYEE_ID = "SELECT id FROM employees WHERE name = ? AND birthday = ? AND workplace = ?";
    
    /**
     * SITE related
     */
    
    public static final String SELECT_SITE = "SELECT * FROM sites WHERE id = ?";
    public static final String SELECT_ALL_SITES = "SELECT * FROM sites";
    public static final String COUNT_SITES = "SELECT COUNT(id) FROM sites";
    public static final String SELECT_SITES_BY_COUNTRY = "SELECT * FROM sites WHERE country LIKE ?";
    public static final String ADD_SITE = "INSERT INTO sites (id, country, region, address, security_Level) VALUES (?,?,?,?,?)";
    public static final String DELETE_SITE = "DELETE FROM sites WHERE id = ?";
    public static final String GET_SITE_ID = "SELECT id FROM sites WHERE country = ? AND region = ? AND address = ?";
    
    /**
     * PROJECT related
     */
    public static final String SELECT_PROJECT = "SELECT * FROM projects WHERE id = ?";
    public static final String SELECT_ALL_PROJECTS = "SELECT * FROM projects";
    public static final String COUNT_PROJECTS = "SELECT COUNT(id) as numberOfProjects FROM projects";
    public static final String SELECT_EMPLOYEES_FOR_PROJECT = "SELECT e.*, ep.project_id FROM employees as e, employee_projects AS ep WHERE ep.project_id = ? AND ep.employee_id = e.id AND e.current = true";
    public static final String SELECT_SITES_FOR_PROJECT = "SELECT s.* FROM sites AS s, sites_projects AS sp WHERE sp.project_id = ? AND sp.site_id = s.id";
    public static final String SELECT_PROJECTS_FOR_EMPLOYEE = "SELECT p.*, ep.employee_id FROM projects AS p, employee_projects AS ep WHERE ep.employee_id = ? AND ep.project_id = p.id";
    public static final String ADD_PROJECT = "INSERT INTO projects (id, title, summary, budget, security_level, status) VALUES(?, ?, ?, ?, ?, 'PROPOSED')";
    public static final String SET_PROJECT_STATUS = "UPDATE projects SET status = ?::project_status WHERE id = ?";
    public static final String SELECT_PROJECTS_FOR_USER = "SELECT * FROM employee_projects ep WHERE ep.project_id=? AND ep.employee_id = (SELECT \"employeeId\" FROM users WHERE username = ?)";
    public static final String AUDIT_GET_PROJECT_ID = "SELECT id FROM projects WHERE summary = ? AND title = ? AND budget = ?";
    
    /**
     * Supply related
     */
     
    public static final String ADD_ITEM = "INSERT INTO items (name, buying_price, selling_price, weight, consumable, type) VALUES (?, ?, ?, ?, ?, ?::supply_type )";
    public static final String GET_ITEM = "SELECT * FROM items WHERE id = ?";
    public static final String ADD_ORDER = "INSERT INTO orders (\"siteId\", \"orderDate\", status, username) VALUES (?, ?, 'SUBMITTED', ?)";
    public static final String ADD_ORDER_ITEM = "INSERT INTO order_items (order_id, item_id, quantity) VALUES (?,?,?)";
    public static final String GET_ORDER = "SELECT o.*, s.* FROM orders o, sites s WHERE o.\"orderId\" = ? AND o.\"siteId\" = s.id";
    public static final String GET_ORDER_ITEMS = "SELECT i.*, oi.quantity FROM items i, order_items oi WHERE oi.item_id = i.id AND oi.order_id = ?";
    public static final String GET_ORDERS_BY_USER = "SELECT o.*, s.* FROM orders o, sites s WHERE o.username = ? AND o.\"siteId\" = s.id";
    public static final String SET_ORDER_STATUS = "UPDATE orders SET status = ?::order_status WHERE \"orderId\" = ?";
    public static final String GET_ITEMS_BY_NAME = "SELECT * FROM items WHERE name LIKE ?";
    public static final String GET_SITE_STOCKS = "SELECT s.quantity, i.* FROM site_stocks s, items i WHERE s.item_id = i.id AND s.site_id = ?";
    
    
    /**
     * User related
     */
     
    public static final String ADD_USER = "INSERT INTO users (username, password, \"employeeId\", enabled) VALUES(?,?,?,true); INSERT into authorities(username, authority) VALUES (?,?)";
    public static final String DISABLE_USER = "UPDATE users SET enabled=false WHERE username = ?";
}

