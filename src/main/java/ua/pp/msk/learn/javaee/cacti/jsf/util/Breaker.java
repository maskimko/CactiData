/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.pp.msk.learn.javaee.cacti.jsf.util;

/**
 *
 * @author maskimko
 */
public class Breaker {
    
    //PowerNet-MIB::isxModularDistModuleBreakerModIndex.1.phaseL1 = INTEGER: 1
    //.1.3.6.1.4.1.318.1.1.22.2.4.1.1.1.1
    private int index;
    
    class Phase{
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
        
        
    }
    
}
