/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.pp.msk.learn.javaee.cacti.jsf.util;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

/**
 *
 * @author maskimko
 */
public class ISXDevice implements Serializable{
    
   
    
    
    
    private InetAddress addr;
    private int port;
    private String snmpCommunity;
    private int snmpVersion;
    //.1.3.6.1.2.1.1.6.0
    private String location;
    //.1.3.6.1.2.1.1.5.0
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
    
    private  Breaker[] breaker = new Breaker[24];
     private   Output[] output = new Output[24];
    
     public ISXDevice() {
    }
    
    public ISXDevice(InetAddress addr, int port, String snmpCommunity, int snmpVersion ){
    
        this.addr = addr;
        this.port = port;
        this.snmpCommunity = snmpCommunity;
        this.snmpVersion = snmpVersion;
    }
    
    public ISXDevice(String hostname, int port, String snmpCommunity, int snmpVersion ){
        try {
            this.addr = InetAddress.getByName(hostname);
        } catch (UnknownHostException ue) {
            Logger.getLogger(this.getClass().getName()).severe("Cannot parse hostname " + ue);
        }
        this.port = port;
        this.snmpCommunity = snmpCommunity;
        this.snmpVersion = snmpVersion;
    }

    public InetAddress getAddr() {
        return addr;
    }

    public void setAddr(InetAddress addr) {
        this.addr = addr;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getSnmpCommunity() {
        return snmpCommunity;
    }

    public void setSnmpCommunity(String snmpCommunity) {
        this.snmpCommunity = snmpCommunity;
    }

    public int getSnmpVersion() {
        return snmpVersion;
    }

    public void setSnmpVersion(int snmpVersion) {
        this.snmpVersion = snmpVersion;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public float getVoltageLtoN1() {
        return voltageLtoN1;
    }

    public void setVoltageLtoN1(float voltageLtoN1) {
        this.voltageLtoN1 = voltageLtoN1;
    }

    public float getVoltageLtoN2() {
        return voltageLtoN2;
    }

    public void setVoltageLtoN2(float voltageLtoN2) {
        this.voltageLtoN2 = voltageLtoN2;
    }

    public float getVoltageLtoN3() {
        return voltageLtoN3;
    }

    public void setVoltageLtoN3(float voltageLtoN3) {
        this.voltageLtoN3 = voltageLtoN3;
    }

    public float getVoltageLtoL1() {
        return voltageLtoL1;
    }

    public void setVoltageLtoL1(float voltageLtoL1) {
        this.voltageLtoL1 = voltageLtoL1;
    }

    public float getVoltageLtoL2() {
        return voltageLtoL2;
    }

    public void setVoltageLtoL2(float voltageLtoL2) {
        this.voltageLtoL2 = voltageLtoL2;
    }

    public float getVoltageLtoL3() {
        return voltageLtoL3;
    }

    public void setVoltageLtoL3(float voltageLtoL3) {
        this.voltageLtoL3 = voltageLtoL3;
    }

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    public float getVoltageNominalLineToNeutral() {
        return voltageNominalLineToNeutral;
    }

    public void setVoltageNominalLineToNeutral(float voltageNominalLineToNeutral) {
        this.voltageNominalLineToNeutral = voltageNominalLineToNeutral;
    }

    public float getVoltageNominalLineToLine() {
        return voltageNominalLineToLine;
    }

    public void setVoltageNominalLineToLine(float voltageNominalLineToLine) {
        this.voltageNominalLineToLine = voltageNominalLineToLine;
    }

    public float getSysPower1() {
        return sysPower1;
    }

    public void setSysPower1(float sysPower1) {
        this.sysPower1 = sysPower1;
    }

    public float getSysPower2() {
        return sysPower2;
    }

    public void setSysPower2(float sysPower2) {
        this.sysPower2 = sysPower2;
    }

    public float getSysPower3() {
        return sysPower3;
    }

    public void setSysPower3(float sysPower3) {
        this.sysPower3 = sysPower3;
    }

    public Breaker[] getBreaker() {
        return breaker;
    }

    public Breaker getBreaker(int index){
         if (index <0 || index > 23) {
            org.apache.log4j.Logger.getLogger(this.getClass().getName()).error("Invalid breaker index " + index + "Must be between 0 and 23");
            return null;
        }
        return breaker[index];
    }
    public void setBreaker(Breaker[] breaker) {
        this.breaker = breaker;
    }

    public Output[] getOutput() {
        return output;
    }

    public Output getOutput(int index) {
          if (index <0 || index > 23) {
            org.apache.log4j.Logger.getLogger(this.getClass().getName()).error("Invalid output index " + index + "Must be between 0 and 23");
            return null;
        }
          if (output[index] ==  null) {
              output[index] = new Output();
          }
        return output[index];
    }
    public void setOutput(Output[] output) {
        this.output = output;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.addr != null ? this.addr.hashCode() : 0);
        hash = 37 * hash + this.port;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ISXDevice other = (ISXDevice) obj;
        if (this.addr != other.addr && (this.addr == null || !this.addr.equals(other.addr))) {
            return false;
        }
        if (this.port != other.port) {
            return false;
        }
        return true;
    }
    
       
    
               
}
