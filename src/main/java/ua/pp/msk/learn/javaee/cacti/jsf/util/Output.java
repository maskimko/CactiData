/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.pp.msk.learn.javaee.cacti.jsf.util;

import java.util.Date;

/**
 *
 * @author maskimko
 */
public class Output {
//PowerNet-MIB::isxModularDistModuleOutputModIndex.1.1
    //.1.3.6.1.4.1.318.1.1.22.2.6.1.1.1.1
    private int index;
 
 
 class Phase{
     //PowerNet-MIB::isxModularDistModuleOutputModIndex.1.1
     //PowerNet-MIB::isxModularDistModuleOutputModIndex.1.1
     private String name;
     //PowerNet-MIB::isxModularDistModuleOutputLocation.1.2 = ""
     //.1.3.6.1.4.1.318.1.1.22.2.6.1.5.1.2
     private String location;
     //PowerNet-MIB::isxModularDistModuleOutputStatus.1.1 = 
     //.1.3.6.1.4.1.318.1.1.22.2.6.1.6.1.1
     private byte status;
     //PowerNet-MIB::isxModularDistModuleOutputConnectorType.1.1
     //.1.3.6.1.4.1.318.1.1.22.2.6.1.18.1.1
     private String connectorType;
     //PowerNet-MIB::isxModularDistModuleOutputTotalPower.1.1 = INTEGER: -1
     //.1.3.6.1.4.1.318.1.1.22.2.6.1.20.1.1
     private float totalPower;
     //PowerNet-MIB::isxModularDistModuleOutputKwhUsage.1.1 = INTEGER: -1
     //.1.3.6.1.4.1.318.1.1.22.2.6.1.21.1.1
     private float kWhUsage;
     //PowerNet-MIB::isxModularDistModuleOutputKwhResetDate.1.1 = ""
     //.1.3.6.1.4.1.318.1.1.22.2.6.1.23.1.1
     private Date kWhResetDate;
     
     
     
 }
    
}
