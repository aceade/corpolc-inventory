/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

import com.aceade.corpolc.inventory.database.ConnectionFactory;
import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author philip
 */
public class BaseDao {
    
    @Inject
    protected ConnectionFactory connectionFactory;
    
    @Inject
    protected JdbcTemplate jdbcTemplate;
}
