/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.supplies;

import com.aceade.corpolc.inventory.model.supplies.types.Calibre;
import com.aceade.corpolc.inventory.model.supplies.types.WeaponType;

/**
 *
 * @author philip
 */
public class Weapon extends Item {
    
    private WeaponType weaponType;
    
    private Calibre calibre;
    
    public Weapon() {
        super();
    }

    /**
     * @return the weaponType
     */
    public WeaponType getWeaponType() {
        return weaponType;
    }

    /**
     * @param weaponType the weaponType to set
     */
    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    /**
     * @return the calibre
     */
    public Calibre getCalibre() {
        return calibre;
    }

    /**
     * @param calibre the calibre to set
     */
    public void setCalibre(Calibre calibre) {
        this.calibre = calibre;
    }
    
    
    
}
