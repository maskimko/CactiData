/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.learn.javaee.cacti.jsf.util;

import java.util.Date;
import javax.validation.constraints.Digits;
import org.apache.log4j.Logger;

/**
 *
 * @author maskimko
 */
public class Output {
//PowerNet-MIB::isxModularDistModuleOutputModIndex.1.1
    //.1.3.6.1.4.1.318.1.1.22.2.6.1.1.1.1

    private int index;
  //PowerNet-MIB::isxModularDistModuleInfoModelNumber.1
    //.1.3.6.1.4.1.318.1.1.22.2.2.1.3.1
    private String model;
        //PowerNet-MIB::isxModularDistModuleInfoSerialNumber.1
    //.1.3.6.1.4.1.318.1.1.22.2.2.1.4.1
    private String serialNumber;

        //PowerNet-MIB::isxModularDistModuleInfoDateOfManufacture.1 = ""
    //.1.3.6.1.4.1.318.1.1.22.2.2.1.5.1
    private Date manufactureDate;
    
    private Phase[] phases = new Phase[]{new Phase(),new Phase(),new Phase()};

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    
    
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public Phase[] getPhases() {
        return phases;
    }

    public void setPhases(Phase[] phases) {
        this.phases = phases;
    }

    public Phase getPhase( int index) {
        if (index <0 || index > 2) {
            Logger.getLogger(this.getClass().getName()).error("Invalid phase index " + index + "Must be between 0,1 or 2");
            return null;
        }
        if (phases[index] == null) {
            phases[index] = new Phase();
        }
        return phases[index];
    }
    
    
    public class Phase {
        public Phase() {}

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public byte getStatus() {
            return status;
        }

        public void setStatus(byte status) {
            this.status = status;
        }

        public String getConnectorType() {
            return connectorType;
        }

        public void setConnectorType(String connectorType) {
            this.connectorType = connectorType;
        }

        public float getTotalPower() {
            return totalPower;
        }

        public void setTotalPower(float totalPower) {
            this.totalPower = totalPower;
        }

        public float getkWhUsage() {
            return kWhUsage;
        }

        public void setkWhUsage(float kWhUsage) {
            this.kWhUsage = kWhUsage;
        }

        public Date getkWhResetDate() {
            return kWhResetDate;
        }

        public void setkWhResetDate(Date kWhResetDate) {
            this.kWhResetDate = kWhResetDate;
        }

        
    }

}
