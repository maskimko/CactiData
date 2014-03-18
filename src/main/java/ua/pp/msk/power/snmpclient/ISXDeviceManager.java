/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.power.snmpclient;

import java.io.IOException;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.faces.bean.ApplicationScoped;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;
import ua.pp.msk.learn.javaee.cacti.jsf.util.ISXDevice;

/**
 *
 * @author maskimko
 */
@Singleton
@ApplicationScoped
public class ISXDeviceManager extends DeviceManager<ISXDevice> {

    private List<ISXDevice> isxDevice = new ArrayList<ISXDevice>();
    private transient SnmpExtract snmpExtract = null;

    public ISXDeviceManager() {
        super(ISXDevice.class);
    }

    public List<ISXDevice> getIsxDevice() {
        return isxDevice;
    }

    public void setIsxDevice(List<ISXDevice> isxDevice) {
        this.isxDevice = isxDevice;
    }

    private void populateIsxBase(ISXDevice dev) {
        snmpExtract = new SnmpExtractImplementation();
        snmpExtract.setCommuntity(dev.getSnmpCommunity());
        snmpExtract.setHostname(dev.getAddr());
        snmpExtract.setPort(dev.getPort());
        snmpExtract.setVersion(dev.getSnmpVersion());

        snmpExtract.addQueryOID(currentAmpsOid);
        snmpExtract.addQueryOID(frequencyOid);
        snmpExtract.addQueryOID(sysNameOid);
        snmpExtract.addQueryOID(sysTotalPowerOid);
        snmpExtract.addQueryOID(sysPowerOid);
        snmpExtract.addQueryOID(voltageLtoLOid);
        snmpExtract.addQueryOID(voltageLtoNOid);
        snmpExtract.addQueryOID(currentAmpsOid);
        snmpExtract.addQueryOID(sysPowerOid);
        snmpExtract.addQueryOID(voltageNominalLineToLineOid);
        snmpExtract.addQueryOID(voltageNominalLineToNeutralOid);
        
        try {
            if (!snmpExtract.isInitialized()) snmpExtract.init();
        
        VariableBinding[] queryAll = snmpExtract.queryAll();

        snmpExtract.close();
        snmpExtract = null;

        //TODO implement base ISX Device population
        for (VariableBinding vb : queryAll) {

            OID currentOid = vb.getOid();
            if (currentOid.startsWith(currentAmpsOid)) {
                int phase = currentOid.last();
                switch (phase) {
                    case 1:
                        dev.setCurrent1((float) vb.getVariable().toInt() / 10);
                        break;
                    case 2:
                        dev.setCurrent2((float) vb.getVariable().toInt() / 10);
                        break;
                    case 3:
                        dev.setCurrent3((float) vb.getVariable().toInt() / 10);
                        break;
                    default:
                        Logger.getLogger(this.getClass().getName()).warning("We do not support this phase " + phase);
                }

            } else if (currentOid.startsWith(frequencyOid)) {
                dev.setFrequency((float) vb.getVariable().toInt() / 10);
            } else if (currentOid.startsWith(voltageLtoLOid)) {
                int phase = currentOid.last();
                switch (phase) {
                    case 1:
                        dev.setVoltageLtoL1((float) vb.getVariable().toInt() / 10);
                        break;
                    case 2:
                        dev.setVoltageLtoL2((float) vb.getVariable().toInt() / 10);
                        break;
                    case 3:
                        dev.setVoltageLtoL3((float) vb.getVariable().toInt() / 10);
                        break;
                    default:
                        Logger.getLogger(this.getClass().getName()).warning("We do not support this phase " + phase);
                }
            } else if (currentOid.startsWith(voltageLtoNOid)) {
                int phase = currentOid.last();
                switch (phase) {
                    case 1:
                        dev.setVoltageLtoN1((float) vb.getVariable().toInt() / 10);
                        break;
                    case 2:
                        dev.setVoltageLtoN2((float) vb.getVariable().toInt() / 10);
                        break;
                    case 3:
                        dev.setVoltageLtoN3((float) vb.getVariable().toInt() / 10);
                        break;
                    default:
                        Logger.getLogger(this.getClass().getName()).warning("We do not support this phase " + phase);
                }
            } else if (currentOid.startsWith(sysPowerOid)) {
                int phase = currentOid.last();
                switch (phase) {
                    case 1:
                        dev.setSysPower1((float) vb.getVariable().toInt() / 10);
                        break;
                    case 2:
                        dev.setSysPower2((float) vb.getVariable().toInt() / 10);
                        break;
                    case 3:
                        dev.setSysPower3((float) vb.getVariable().toInt() / 10);
                        break;
                    default:
                        Logger.getLogger(this.getClass().getName()).warning("We do not support this phase " + phase);
                }
            } else if (currentOid.startsWith(locationOid)) {
                dev.setLocation(vb.getVariable().toString());
            } else if (currentOid.startsWith(sysTotalPowerOid)) {
                dev.setTotalPower((float) vb.getVariable().toInt() / 10);
            } else if (currentOid.startsWith(voltageNominalLineToLineOid)) {
                dev.setVoltageNominalLineToLine((float) vb.getVariable().toInt() / 10);
            } else if (currentOid.startsWith(voltageNominalLineToNeutralOid)) {
                dev.setVoltageNominalLineToNeutral((float) vb.getVariable().toInt() / 10);
            } else if (currentOid.startsWith(sysNameOid)) {
                dev.setSysName(vb.getVariable().toString());
            }
        }
        } catch (IOException ex) {
            Logger.getLogger(ISXDeviceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void populateDevice(ISXDevice dev, boolean full) {
        //populateBreakers(dev);
       // populateModules(dev);
        if (full) {
            populateIsxBase(dev);
        }
    }

    public void populateDevice(ISXDevice dev) {
        populateDevice(dev, true);
    }

    public void populateDevice(List<ISXDevice> devices, boolean full) {
        Iterator<ISXDevice> iterator = devices.iterator();
        while (iterator.hasNext()) {
            populateDevice(iterator.next(), full);
        }
    }

    public void populateDevice(List<ISXDevice> devices) {
        populateDevice(devices, true);
    }

    public void populateBreakers(List<ISXDevice> devices) {
        Iterator<ISXDevice> iterator = devices.iterator();
        while (iterator.hasNext()) {
            populateBreakers(iterator.next());
        }
    }

    public void populateBreakers(ISXDevice dev) {
        snmpExtract = new SnmpExtractImplementation();
        snmpExtract.setCommuntity(dev.getSnmpCommunity());
        snmpExtract.setHostname(dev.getAddr());
        snmpExtract.setPort(dev.getPort());
        snmpExtract.setVersion(dev.getSnmpVersion());

        snmpExtract.addQueryOID(breakerIndex);
        snmpExtract.addQueryOID(phaseBreakerCurrent);
        snmpExtract.addQueryOID(phaseBreakerIndex);
        snmpExtract.addQueryOID(phaseBreakerPercent);
        snmpExtract.addQueryOID(phaseBreakerPosition);
        snmpExtract.addQueryOID(phaseBreakerPower);
        try {
            if (!snmpExtract.isInitialized()) snmpExtract.init();
       
        VariableBinding[] queryAll = snmpExtract.queryAll();

        snmpExtract.close();
        snmpExtract = null;

        for (VariableBinding vb : queryAll) {

            OID currentOid = vb.getOid();
            if (currentOid.startsWith(breakerIndex)) {
                currentOid.removeLast();
                int breaker = currentOid.last();
                dev.getBreaker(breaker).setIndex(vb.getVariable().toInt());
            } else {
                if (currentOid.startsWith(phaseBreakerCurrent)) {
                    int phase = currentOid.removeLast();
                    int output = currentOid.last();
                    dev.getBreaker(output).getPhase(phase).setCurrent((float) vb.getVariable().toInt() / 10);
                } else if (currentOid.startsWith(phaseBreakerIndex)) {
                    int phase = currentOid.removeLast();
                    int output = currentOid.last();

                    dev.getBreaker(output).getPhase(phase).setIndex((byte) vb.getVariable().toInt());

                } else if (currentOid.startsWith(phaseBreakerPercent)) {
                    int phase = currentOid.removeLast();
                    int output = currentOid.last();
                    dev.getBreaker(output).getPhase(phase).setCurrent((float) vb.getVariable().toInt() / 10);
                } else if (currentOid.startsWith(phaseBreakerPosition)) {
                    int phase = currentOid.removeLast();
                    int output = currentOid.last();

                    dev.getBreaker(output).getPhase(phase).setIndex((byte) vb.getVariable().toInt());
                } else if (currentOid.startsWith(phaseBreakerPower)) {
                    int phase = currentOid.removeLast();
                    int output = currentOid.last();
                    dev.getBreaker(output).getPhase(phase).setCurrent((float) vb.getVariable().toInt() / 10);
                }

            }

        }
         } catch (IOException ex) {
            Logger.getLogger(ISXDeviceManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void populateModules(List<ISXDevice> devices) {
        Iterator<ISXDevice> iterator = devices.iterator();
        while (iterator.hasNext()) {
            populateModules(iterator.next());
        }
    }

    public void populateModules(ISXDevice dev) {

        snmpExtract = new SnmpExtractImplementation();
        snmpExtract.setCommuntity(dev.getSnmpCommunity());
        snmpExtract.setHostname(dev.getAddr());
        snmpExtract.setPort(dev.getPort());
        snmpExtract.setVersion(dev.getSnmpVersion());

        snmpExtract.addQueryOID(phaseModuleOutputKWResetDateOid);
        snmpExtract.addQueryOID(phaseModuleOutputConnectorTypeOid);
        snmpExtract.addQueryOID(phaseModuleOutputkWhUsageOid);
        snmpExtract.addQueryOID(phaseModuleOutputLocationOid);
        snmpExtract.addQueryOID(phaseModuleOutputNameOid);
        snmpExtract.addQueryOID(phaseModuleOutputStatusOid);
        snmpExtract.addQueryOID(phaseModuleOutputTotalPowerOid);
        snmpExtract.addQueryOID(moduleOutputIndexOid);
        snmpExtract.addQueryOID(moduleOutputModelOid);
        snmpExtract.addQueryOID(moduleOutputSerialNumberOid);
        snmpExtract.addQueryOID(moduleOutputManufactureDate);

        try {
        if (!snmpExtract.isInitialized())  snmpExtract.init();
        
        VariableBinding[] queryAll = snmpExtract.queryAll();

        snmpExtract.close();
        snmpExtract = null;

        for (VariableBinding vb : queryAll) {
            try {
                OID currentOid = vb.getOid();
                if (currentOid.startsWith(moduleOutputManufactureDate)) {
                    int output = currentOid.last();
                    Date manuDate = new SimpleDateFormat("MM/DD/YY").parse(vb.getVariable().toString());
                    dev.getOutput(output).setManufactureDate(manuDate);
                } else if (currentOid.startsWith(moduleOutputSerialNumberOid)) {
                    int output = currentOid.last();
                    dev.getOutput(output).setSerialNumber(vb.getVariable().toString());
                } else if (currentOid.startsWith(moduleOutputModelOid)) {
                    int output = currentOid.last();
                    dev.getOutput(output).setModel(vb.getVariable().toString());
                } else if (currentOid.startsWith(moduleOutputIndexOid)) {
                    int output = currentOid.last();
                    dev.getOutput(output).setIndex(vb.getVariable().toInt());
                } else {
                    if (currentOid.startsWith(phaseModuleOutputConnectorTypeOid)) {
                        int phase = currentOid.removeLast();
                        int output = currentOid.last();
                        dev.getOutput(output).getPhase(phase).setConnectorType(vb.getVariable().toString());
                    } else if (currentOid.startsWith(phaseModuleOutputKWResetDateOid)) {
                        int phase = currentOid.removeLast();
                        int output = currentOid.last();
                        Date kWhResetDate = new SimpleDateFormat("DD.MM.YYYY").parse(vb.getVariable().toString());
                        dev.getOutput(output).getPhase(phase).setkWhResetDate(kWhResetDate);

                    } else if (currentOid.startsWith(phaseModuleOutputLocationOid)) {
                        int phase = currentOid.removeLast();
                        int output = currentOid.last();
                        dev.getOutput(output).getPhase(phase).setLocation(vb.getVariable().toString());
                    } else if (currentOid.startsWith(phaseModuleOutputNameOid)) {
                        int phase = currentOid.removeLast();
                        int output = currentOid.last();
                        dev.getOutput(output).getPhase(phase).setName(vb.getVariable().toString());
                    } else if (currentOid.startsWith(phaseModuleOutputStatusOid)) {
                        int phase = currentOid.removeLast();
                        int output = currentOid.last();
                        dev.getOutput(output).getPhase(phase).setStatus((byte) vb.getVariable().toInt());
                    } else if (currentOid.startsWith(phaseModuleOutputTotalPowerOid)) {
                        int phase = currentOid.removeLast();
                        int output = currentOid.last();
                        dev.getOutput(output).getPhase(phase).setTotalPower((float) vb.getVariable().toInt() / 10);
                    } else if (currentOid.startsWith(phaseModuleOutputkWhUsageOid)) {
                        int phase = currentOid.removeLast();
                        int output = currentOid.last();
                        dev.getOutput(output).getPhase(phase).setkWhUsage((float) vb.getVariable().toInt() / 10);
                    }

                    //TODO add unimplemented oid;
                }
            } catch (ParseException pe) {
                Logger.getLogger(this.getClass().getName()).warning("Cannot parse manufacture date " + vb.getVariable().toString());
            }
        }

        } catch (IOException ex) {
            Logger.getLogger(ISXDeviceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //.1.3.6.1.2.1.1.6.0
    public static final OID locationOid = new OID(".1.3.6.1.2.1.1.6.0");
    //.1.3.6.1.2.1.1.5.0
    public static final OID sysNameOid = new OID(".1.3.6.1.2.1.1.5.0");
    //PowerNet-MIB::isxModularDistSysVoltageLtoN.phase1
    //.1.3.6.1.4.1.318.1.1.22.4.1.15.1.3.1
    public static final OID voltageLtoNOid = new OID(".1.3.6.1.4.1.318.1.1.22.4.1.15.1.3");
    /* //PowerNet-MIB::isxModularDistSysVoltageLtoN.phase2
     //.1.3.6.1.4.1.318.1.1.22.4.1.15.1.3.2
     public static final OID voltageLtoN2Oid = new OID(".1.3.6.1.4.1.318.1.1.22.4.1.15.1.3.2");
     //PowerNet-MIB::isxModularDistSysVoltageLtoN.phase3
     //.1.3.6.1.4.1.318.1.1.22.4.1.15.1.3.3
     public static final OID voltageLtoN3Oid = new OID(".1.3.6.1.4.1.318.1.1.22.4.1.15.1.3.3");*/
    //PowerNet-MIB::isxModularDistSysVoltageLtoL.phase1
    //.1.3.6.1.4.1.318.1.1.22.4.1.15.1.4.1
    public static final OID voltageLtoLOid = new OID(".1.3.6.1.4.1.318.1.1.22.4.1.15.1.4");
    /* //PowerNet-MIB::isxModularDistSysVoltageLtoL.phase2
     //.1.3.6.1.4.1.318.1.1.22.4.1.15.1.4.2
     public static final OID voltageLtoL2Oid = new OID(".1.3.6.1.4.1.318.1.1.22.4.1.15.1.4.2");
     //PowerNet-MIB::isxModularDistSysVoltageLtoL.phase3
     //.1.3.6.1.4.1.318.1.1.22.4.1.15.1.4.3
     public static final OID voltageLtoL3Oid = new OID(".1.3.6.1.4.1.318.1.1.22.4.1.15.1.4.3");
     //PowerNet-MIB::isxModularDistSysOutputFrequency.0
     //.1.3.6.1.4.1.318.1.1.22.4.1.4.0*/

    /*
     PowerNet-MIB::isxModularDistSysCurrentAmps.phase1 = INTEGER: 494
     PowerNet-MIB::isxModularDistSysCurrentAmps.phase2 = INTEGER: 489
     PowerNet-MIB::isxModularDistSysCurrentAmps.phase3 = INTEGER: 658

     */
    public static final OID currentAmpsOid = new OID(".1.3.6.1.4.1.318.1.1.22.4.2.11.1.3");

    public static final OID frequencyOid = new OID(".1.3.6.1.4.1.318.1.1.22.4.1.4.0");
    //PowerNet-MIB::isxModularDistSysOutputVoltageNominalLineToNeutral.0
    //.1.3.6.1.4.1.318.1.1.22.4.1.1.0
    public static final OID voltageNominalLineToNeutralOid = new OID(".1.3.6.1.4.1.318.1.1.22.4.1.1.0");
    //PowerNet-MIB::isxModularDistSysOutputVoltageNominalLineToLine.0
    public static final OID voltageNominalLineToLineOid = new OID(".1.3.6.1.4.1.318.1.1.22.4.1.2.0");
    //PowerNet-MIB::isxModularDistSysPowerKw.phase1 
    //.1.3.6.1.4.1.318.1.1.22.4.3.5.1.2.1
    public static final OID sysPowerOid = new OID(".1.3.6.1.4.1.318.1.1.22.4.3.5.1.2");
    /* //PowerNet-MIB::isxModularDistSysPowerKw.phase2
     //.1.3.6.1.4.1.318.1.1.22.4.3.5.1.2.2
     public static final OID sysPower2Oid = new OID(".1.3.6.1.4.1.318.1.1.22.4.3.5.1.2.2");
     //PowerNet-MIB::isxModularDistSysPowerKw.phase3
     //.1.3.6.1.4.1.318.1.1.22.4.3.5.1.2.3
     public static final OID sysPower3Oid = new OID(".1.3.6.1.4.1.318.1.1.22.4.3.5.1.2.3");*/

    //PowerNet-MIB::isxModularDistSysPowerTotal.0
    public static final OID sysTotalPowerOid = new OID(".1.3.6.1.4.1.318.1.1.22.4.3.1.0");

    //=====================================Output section=======================
    public static final OID moduleOutputIndexOid = new OID(".1.3.6.1.4.1.318.1.1.22.2.6.1.1");
    //PowerNet-MIB::isxModularDistModuleInfoModelNumber.1
    //.1.3.6.1.4.1.318.1.1.22.2.2.1.3.1
    public static final OID moduleOutputModelOid = new OID(".1.3.6.1.4.1.318.1.1.22.2.2.1.3");
    //PowerNet-MIB::isxModularDistModuleInfoSerialNumber.1
    //.1.3.6.1.4.1.318.1.1.22.2.2.1.4.1
    public static final OID moduleOutputSerialNumberOid = new OID(".1.3.6.1.4.1.318.1.1.22.2.2.1.4");

    //PowerNet-MIB::isxModularDistModuleInfoDateOfManufacture.1 = ""
    //.1.3.6.1.4.1.318.1.1.22.2.2.1.5.1
    public static final OID moduleOutputManufactureDate = new OID(".1.3.6.1.4.1.318.1.1.22.2.2.1.5");

    //===========Phases
    //PowerNet-MIB::isxModularDistModuleOutputModIndex.1.1
    //PowerNet-MIB::isxModularDistModuleOutputModIndex.1.1
    public static final OID phaseModuleOutputNameOid = new OID(".1.3.6.1.4.1.318.1.1.22.2.6.1.4");
    //PowerNet-MIB::isxModularDistModuleOutputLocation.1.2 = ""
    //.1.3.6.1.4.1.318.1.1.22.2.6.1.5.1.2
    public static final OID phaseModuleOutputLocationOid = new OID(".1.3.6.1.4.1.318.1.1.22.2.6.1.5");
    //PowerNet-MIB::isxModularDistModuleOutputStatus.1.1 = 
    //.1.3.6.1.4.1.318.1.1.22.2.6.1.6.1.1
    public static final OID phaseModuleOutputStatusOid = new OID(".1.3.6.1.4.1.318.1.1.22.2.6.1.6");
    //PowerNet-MIB::isxModularDistModuleOutputConnectorType.1.1
    //.1.3.6.1.4.1.318.1.1.22.2.6.1.18.1.1
    public static final OID phaseModuleOutputConnectorTypeOid = new OID(".1.3.6.1.4.1.318.1.1.22.2.6.1.18");
    //PowerNet-MIB::isxModularDistModuleOutputTotalPower.1.1 = INTEGER: -1
    //.1.3.6.1.4.1.318.1.1.22.2.6.1.20.1.1
    public static final OID phaseModuleOutputTotalPowerOid = new OID(".1.3.6.1.4.1.318.1.1.22.2.6.1.20");
    //PowerNet-MIB::isxModularDistModuleOutputKwhUsage.1.1 = INTEGER: -1
    //.1.3.6.1.4.1.318.1.1.22.2.6.1.21.1.1
    public static final OID phaseModuleOutputkWhUsageOid = new OID(".1.3.6.1.4.1.318.1.1.22.2.6.1.21");
    //PowerNet-MIB::isxModularDistModuleOutputKwhResetDate.1.1 = ""
    //.1.3.6.1.4.1.318.1.1.22.2.6.1.23.1.1
    public static final OID phaseModuleOutputKWResetDateOid = new OID(".1.3.6.1.4.1.318.1.1.22.2.6.1.23");

    //================Breaker Section=======================================
    //PowerNet-MIB::isxModularDistModuleBreakerModIndex.1.phaseL1 = INTEGER: 1
    //.1.3.6.1.4.1.318.1.1.22.2.4.1.1.1.1
    public static final OID breakerIndex = new OID(".1.3.6.1.4.1.318.1.1.22.2.4.1.1");

    //==============Phases
    //PowerNet-MIB::isxModularDistModuleBreakerPhaseIndex.1.phaseL1 = INTEGER: phaseL1(1)
    //.1.3.6.1.4.1.318.1.1.22.2.4.1.2.1.1
    public static final OID phaseBreakerIndex = new OID(".1.3.6.1.4.1.318.1.1.22.2.4.1.2");
    //PowerNet-MIB::isxModularDistModuleBreakerPosition.22.phaseL2 = INTEGER: closed(2)
    //.1.3.6.1.4.1.318.1.1.22.2.4.1.7.22.2
    public static final OID phaseBreakerPosition = new OID(".1.3.6.1.4.1.318.1.1.22.2.4.1.7");
    //PowerNet-MIB::isxModularDistModuleBreakerCurrent.1.phaseL1 = INTEGER: -1
    //.1.3.6.1.4.1.318.1.1.22.2.4.1.9.1.1
    public static final OID phaseBreakerCurrent = new OID(".1.3.6.1.4.1.318.1.1.22.2.4.1.9");
    //PowerNet-MIB::isxModularDistModuleBreakerPercent.1.phaseL1 = INTEGER: -1
    //.1.3.6.1.4.1.318.1.1.22.2.4.1.10.1.1
    public static final OID phaseBreakerPercent = new OID(".1.3.6.1.4.1.318.1.1.22.2.4.1.10");
    //PowerNet-MIB::isxModularDistModuleBreakerPower.1.phaseL1 = INTEGER: -1
    //.1.3.6.1.4.1.318.1.1.22.2.4.1.11.1.1
    public static final OID phaseBreakerPower = new OID(".1.3.6.1.4.1.318.1.1.22.2.4.1.11");

    @Override
    public void addDevice(ISXDevice device) {
        isxDevice.add(device);
    }

    @Override
    public void removeDevice(ISXDevice device) {
        isxDevice.remove(device);
    }

    @Override
    public ISXDevice find(ISXDevice device) {

        Iterator<ISXDevice> iterator = isxDevice.iterator();
        while (iterator.hasNext()) {
            ua.pp.msk.learn.javaee.cacti.jsf.util.ISXDevice current = iterator.next();
            if (current.equals(device)) {
                return current;
            }
        }
        return null;
    }

    @Override
    public List<ISXDevice> findAll() {
        return isxDevice;
    }

}
