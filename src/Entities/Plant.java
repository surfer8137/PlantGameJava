/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Game.*;

/**
 *
 * @author Angel
 */
public class Plant {

    public static final float TIME_SEED_TO_PLANT = 2;
    public static final char SEED = '.';
    public static final char PLANT = '$';

    private boolean state; //false = seed, true = plant
    private float timeToGrow, initTime;
    private char toDraw;

    public Plant(float initTime) {
        this.initTime = initTime;
        this.state = false;
        this.toDraw = SEED;
        this.initTime = initTime;
        this.timeToGrow = initTime + TIME_SEED_TO_PLANT; 
    }

    public void grow() {
        this.state = true;
        this.toDraw = PLANT;
        
    }

    public void replant() {
        this.state = false;
        this.toDraw = SEED;
    }

    public void reset(float time) {
        this.state = false;
        this.toDraw = SEED;
        this.initTime = time;
        this.timeToGrow = initTime + TIME_SEED_TO_PLANT;
    }

    public void update(float time, boolean takePlant) {  
        if (timeToGrow < time) {
            grow();            
        }
        if (takePlant) {
            reset(time);
        }
    }

    public char getSymbol() {
        return toDraw;
    }
}
