/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.pp.msk.learn.javaee.cacti.jsf.util;

import java.net.InetAddress;
import java.util.Date;

/**
 *
 * @author maskimko
 */
public class ISXDevice {
    
    private InetAddress addr;
    private int port;
    private String snmpCommunity;
    private int snmpVersion;
    private String location;
    private String sysName;
    //PowerNet-MIB::isxModularDistSysVoltageLtoN.phase1
    //.1.3.6.1.4.1.318.1.1.22.4.1.15.1.3.1
    private float voltageLtoN1;
    //PowerNet-MIB::isxModularDistSysVoltageLtoN.phase2
    //.1.3.6.1.4.1.318.1.1.22.4.1.15.1.3.2
    private float voltageLtoN2;
    //PowerNet-MIB::isxModularDistSysVoltageLtoN.phase3
    //.1.3.6.1.4.1.318.1.1.22.4.1.15.1.3.3
    private float voltageLtoN3;
    //PowerNet-MIB::isxModularDistSysVoltageLtoL.phase1
    //.1.3.6.1.4.1.318.1.1.22.4.1.15.1.4.1
    private float voltageLtoL1;
    //PowerNet-MIB::isxModularDistSysVoltageLtoL.phase2
    //.1.3.6.1.4.1.318.1.1.22.4.1.15.1.4.2
    private float voltageLtoL2;
    //PowerNet-MIB::isxModularDistSysVoltageLtoL.phase3
    //.1.3.6.1.4.1.318.1.1.22.4.1.15.1.4.3
    private float voltageLtoL3;
    //PowerNet-MIB::isxModularDistSysOutputFrequency.0
    //.1.3.6.1.4.1.318.1.1.22.4.1.4.0
    private float frequency;
    //PowerNet-MIB::isxModularDistSysOutputVoltageNominalLineToNeutral.0
    //.1.3.6.1.4.1.318.1.1.22.4.1.2.0
    private float voltageNominalLineToNeutral;
    //PowerNet-MIB::isxModularDistSysOutputVoltageNominalLineToLine.0
    private float voltageNominalLineToLine;
    //PowerNet-MIB::isxModularDistSysPowerKw.phase1 
    //.1.3.6.1.4.1.318.1.1.22.4.3.5.1.2.1
    private float sysPower1;
      //PowerNet-MIB::isxModularDistSysPowerKw.phase2
    //.1.3.6.1.4.1.318.1.1.22.4.3.5.1.2.2
    private float sysPower2;
      //PowerNet-MIB::isxModularDistSysPowerKw.phase3
    //.1.3.6.1.4.1.318.1.1.22.4.3.5.1.2.3
    private float sysPower3;
    
    
    class ModuleOutput{
        //PowerNet-MIB::isxModularDistModuleInfoModelNumber.1
        //.1.3.6.1.4.1.318.1.1.22.2.2.1.3.1
        private String model;
        //PowerNet-MIB::isxModularDistModuleInfoSerialNumber.1
        //.1.3.6.1.4.1.318.1.1.22.2.2.1.4.1
        private String serialNumber;
        
        //PowerNet-MIB::isxModularDistModuleInfoDateOfManufacture.1 = ""
        //.1.3.6.1.4.1.318.1.1.22.2.2.1.5.1
        private Date manufactureDate;
        
        
        Breaker[] breaker = new Breaker[24];
        Output[] output = new Output[24];
    }
    
               
}
