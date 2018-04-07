/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.supplies;

import com.aceade.corpolc.inventory.model.supplies.types.AlcoholType;
import com.aceade.corpolc.inventory.model.supplies.types.FoodType;

/**
 * Booze, because dorfs.
 * @author philip
 */
public class Alcohol extends FoodSupply {
    
    private double strength;
    
    private AlcoholType type;
    
    // What? Booze that cannot be drunk? Inconceivable!
    // Oh, it's for antifreeze or similar...
    private boolean isDrinkable = true;
    
    public Alcohol() {
        super();
        super.setFoodType(FoodType.DRINK);
    }

    /**
     * @return the strength
     */
    public double getStrength() {
        return strength;
    }

    /**
     * @param strength the strength to set
     */
    public void setStrength(double strength) {
        this.strength = strength;
    }

    /**
     * @return the type
     */
    public AlcoholType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(AlcoholType type) {
        this.type = type;
    }

    /**
     * @return the isDrinkable
     */
    public boolean isIsDrinkable() {
        return isDrinkable;
    }

    /**
     * @param isDrinkable the isDrinkable to set
     */
    public void setIsDrinkable(boolean isDrinkable) {
        this.isDrinkable = isDrinkable;
    }
    
    
}
