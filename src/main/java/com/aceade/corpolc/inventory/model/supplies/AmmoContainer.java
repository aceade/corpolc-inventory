/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.supplies;

import com.aceade.corpolc.inventory.model.supplies.types.Calibre;

/**
 * Represents a magazine, clip or similar collection of ammo.
 * @author philip
 */
public class AmmoContainer extends Item {
    
    private Calibre calibre;
    
    private int roundsPerLoad;
    
    public AmmoContainer() {
        super();
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

    /**
     * @return the roundsPerLoad
     */
    public int getRoundsPerLoad() {
        return roundsPerLoad;
    }

    /**
     * @param roundsPerLoad the roundsPerLoad to set
     */
    public void setRoundsPerLoad(int roundsPerLoad) {
        this.roundsPerLoad = roundsPerLoad;
    }
    
    
}
