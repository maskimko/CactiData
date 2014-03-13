/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.pp.msk.learn.javaee.cacti.jsf.util;

import org.apache.log4j.Logger;

/**
 *
 * @author maskimko
 */
public class Breaker {
    
    //PowerNet-MIB::isxModularDistModuleBreakerModIndex.1.phaseL1 = INTEGER: 1
    //.1.3.6.1.4.1.318.1.1.22.2.4.1.1.1.1
    private int index;
    
    private Phase[] phases = new Phase[3];

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Phase[] getPhases() {
        return phases;
    }

    public void setPhases(Phase[] phases) {
        this.phases = phases;
    }
    
    public Phase getPhase(int index) {
         if (index <0 || index > 2) {
            Logger.getLogger(this.getClass().getName()).error("Invalid phase index " + index + "Must be between 0,1 or 2");
            return null;
        }
        if (phases[index] == null) {
            phases[index] = new Phase();
        }
        return phases[index];
    }
    
    public class Phase{
        //PowerNet-MIB::isxModularDistModuleBreakerPhaseIndex.1.phaseL1 = INTEGER: phaseL1(1)
        //.1.3.6.1.4.1.318.1.1.22.2.4.1.2.1.1
        private byte index;
        //PowerNet-MIB::isxModularDistModuleBreakerPosition.22.phaseL2 = INTEGER: closed(2)
        //.1.3.6.1.4.1.318.1.1.22.2.4.1.7.22.2
        private byte position;
        //PowerNet-MIB::isxModularDistModuleBreakerCurrent.1.phaseL1 = INTEGER: -1
        //.1.3.6.1.4.1.318.1.1.22.2.4.1.9.1.1
        private float current;
        //PowerNet-MIB::isxModularDistModuleBreakerPercent.1.phaseL1 = INTEGER: -1
        //.1.3.6.1.4.1.318.1.1.22.2.4.1.10.1.1
        private float percent;
        //PowerNet-MIB::isxModularDistModuleBreakerPower.1.phaseL1 = INTEGER: -1
        //.1.3.6.1.4.1.318.1.1.22.2.4.1.11.1.1
        private float power;

        public byte getIndex() {
            return index;
        }

        public void setIndex(byte index) {
            this.index = index;
        }

        public byte getPosition() {
            return position;
        }

        public void setPosition(byte position) {
            this.position = position;
        }

        public float getCurrent() {
            return current;
        }

        public void setCurrent(float current) {
            this.current = current;
        }

        public float getPercent() {
            return percent;
        }

        public void setPercent(float percent) {
            this.percent = percent;
        }

        public float getPower() {
            return power;
        }

        public void setPower(float power) {
            this.power = power;
        }
        
        
    }
    
}
