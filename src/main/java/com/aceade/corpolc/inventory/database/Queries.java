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
    
    public static final String SELECT_EMPLOYEE = "SELECT e.id, e.name, e.birthday, e.salary, e.workplace as siteId, e.current, d.id AS department, s.country, s.region, s.\"postalAddress\", e.\"securityLevel\", s.\"securityLevel\" as siteSecurityLevel FROM employees as e, sites as s, departments as d WHERE e.id = ? AND d.id = e.department;";
    public static final String SELECT_ALL_EMPLOYEES = "SELECT e.*, s.id as siteid, s.country, s.region, s.\"postalAddress\", s.\"securityLevel\" as \"siteSecurityLevel\" from employees as e, sites as s where e.workplace = s.id ORDER BY e.id;";
    public static final String COUNT_EMPLOYEES = "SELECT COUNT(id) FROM employees";
    public static final String SELECT_EMPLOYEES_BY_SITE = "SELECT e.id, e.name, e.birthday, e.salary, d.id AS department, s.country, s.region, s.\"postalAddress\", e.\"securityLevel\" FROM employees as e, sites as s, departments as d WHERE e.workplace = ?;";
    public static final String ADD_EMPLOYEE = "INSERT INTO employees (id, name, birthday, department, workplace, \"securityLevel\", salary) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String DELETE_EMPLOYEE = "UPDATE employees SET current = false WHERE id = ?";
    
    /**
     * SITE related
     */
    
    public static final String SELECT_SITE = "SELECT * FROM sites WHERE id = ?";
    public static final String SELECT_ALL_SITES = "SELECT * FROM sites";
    public static final String COUNT_SITES = "SELECT COUNT(id) FROM sites";
    public static final String SELECT_SITES_BY_COUNTRY = "SELECT * FROM sites WHERE country LIKE ?";
    public static final String ADD_SITE = "INSERT INTO sites (id, country, region, \"postalAddress\", \"securityLevel\") VALUES (?,?,?,?,?)";
    public static final String DELETE_SITE = "DELETE FROM sites WHERE id = ?";
    
    /**
     * PROJECT related
     */
    public static final String SELECT_PROJECT = "SELECT * FROM projects WHERE id = ?";
    public static final String SELECT_ALL_PROJECTS = "SELECT * FROM projects";
    public static final String COUNT_PROJECTS = "SELECT COUNT(id) as numberOfProjects FROM projects";
    public static final String SELECT_EMPLOYEES_FOR_PROJECT = "SELECT e.id, e.name, e.salary, e.department, e.workplace, e.birthday, e.\"securityLevel\", ep.project_id FROM employees as e, employee_projects AS ep WHERE ep.project_id = ? AND ep.employee_id = e.id";
    public static final String SELECT_SITES_FOR_PROJECT = "SELECT s.id, s.country, s.region, s.\"postalAddress\", s.\"securityLevel\" FROM sites AS s, sites_projects AS sp WHERE sp.project_id = ? AND sp.site_id = s.id";
}

