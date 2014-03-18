/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.power.snmpclient;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TreeEvent;
import org.snmp4j.util.TreeUtils;

/**
 *
 * @author maskimko
 */
public class SnmpExtractImplementation implements SnmpExtract {

    private List<OID> oids = new ArrayList<OID>();
    private UdpAddress addr = null;
    private OctetString community = new OctetString("public");
    private int version = SnmpConstants.version1;
    private int port = 161;
    private InetAddress hostname = null;
    private CommunityTarget target = null;
    private int retries = 2;
    private long timeout = 500;
    private TransportMapping<UdpAddress> transportMapping = null;
    private UdpAddress receiveAddress = null;
    private Snmp snmp = null;
    private boolean initialized = false;

    @Override
    public void init() throws IOException {
        if (addr == null) {
            addr = new UdpAddress(hostname, port);
        }

        target = new CommunityTarget(addr, community);
        target.setTimeout(timeout);
        target.setRetries(retries);
        target.setVersion(version);
        if (receiveAddress == null) {
            transportMapping = new DefaultUdpTransportMapping();
        } else {
            transportMapping = new DefaultUdpTransportMapping(receiveAddress);
        }
        snmp = new Snmp(transportMapping);
        transportMapping.listen();
        initialized = true;
    }

    
    
    @Override
    public void setQueryOIDs(OID[] oids) {

        this.oids = new ArrayList<OID>();
        this.oids.addAll(Arrays.asList(oids));

    }

    @Override
    public VariableBinding[] query(OID oid) {
        java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,oid.toDottedString());
        VariableBinding[] varBindings = null;
        try {
        TreeUtils treeUtils = new TreeUtils(snmp, new DefaultPDUFactory());
        List<TreeEvent> treeEvents = treeUtils.getSubtree(target, oid);
        if (treeEvents == null || treeEvents.isEmpty()) {
            Logger.getLogger(this.getClass()).warn("No results returned");
        } else {
            for (TreeEvent event : treeEvents) {
                if (event != null) {
                    if (event.isError()) {
                        Logger.getLogger(this.getClass()).error("oid [" + oid + "] " + event.getErrorMessage());
                    }

                    varBindings = event.getVariableBindings();

                    if (varBindings == null || varBindings.length == 0) {
                        Logger.getLogger(this.getClass()).debug("No result returned from TreeEvent no variableBindings trying to get singular value");
                        try {
                            List<VariableBinding> vbs = new ArrayList<VariableBinding>();
                            VariableBinding vb = new VariableBinding(oid);
                            vbs.add(vb);
                            PDU requestPDU = new PDU(PDU.GET, vbs);
                            ResponseEvent get = snmp.get(requestPDU, target);
                            varBindings = get.getResponse().getVariableBindings().toArray(varBindings);
                        } catch (IOException ex) {
                            java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            }
        }
        }
        catch (Exception e) {
            java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }
        return varBindings;

    }

    @Override
    public void setHostname(InetAddress ip) {
        this.hostname = ip;

    }

  

    @Override
    public void setCommuntity(String community) {
        this.community = new OctetString(community);

    }

    @Override
    public void setVersion(int ver) {
        switch (ver) {
            case 1:
                version = SnmpConstants.version1;
                break;
            case 2:
                version = SnmpConstants.version2c;
                break;
            default:
                throw new UnsupportedOperationException("We do not support this version of snmp");
        }

    }

    @Override
    public void setPort(int port) {
        this.port = port;

    }

    protected void setHostname(UdpAddress udpAddr) {
        this.addr = udpAddr;

    }

    protected void setCommunity(OctetString community) {
        this.community = community;

    }

    @Override
    public void setTimeout(long millis) {
        this.timeout = millis;
    }

    @Override
    public void setRetries(int retries) {
        this.retries = retries;

    }

    protected UdpAddress getAddress() {

        return addr;
    }

    @Override
    public String getCommunity() {

        return community.toString();
    }

    @Override
    public int getVersion() {

        return version;
    }

    @Override
    public int getPort() {

        return port;
    }

    @Override
    public long getTimeout() {

        return timeout;
    }

    @Override
    public int getRetries() {

        return retries;
    }

    protected void setReceiveAddress(UdpAddress receiveAddress) {
        this.receiveAddress = receiveAddress;

    }

    protected UdpAddress getreceiveAddress() {
        return receiveAddress;
    }

    @Override
    public void setQueryOIDs(String[] oids) {

        for (String oidString : oids) {
            this.oids.add(new OID(oidString));
        }
    }

    @Override
    public VariableBinding[] queryAll() {
        VariableBinding[] result = null;
        ArrayList<VariableBinding> vbArray = new ArrayList<VariableBinding>();
        for (OID oid : oids) {
             vbArray.addAll(Arrays.asList(query(oid)));           
        }
        return vbArray.toArray(result);
    }

   

    @Override
    public InetAddress getHostname() {
        return hostname;
    }

    @Override
    public void setReceiveAddress(InetAddress addr, int port) {
        receiveAddress = new UdpAddress(addr, port);
    }

    @Override
    public void setSnmpAddress(String address) {
        addr = (UdpAddress) GenericAddress.parse(address);
    }

    @Override
    public void close() {
        if (snmp != null) {
            try {
                snmp.close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void reinit() throws IOException {
        this.addr = null;
        init();
    }

    @Override
    public Variable queryExact(OID oid) {
        Variable result = null;
        VariableBinding vb =new VariableBinding(oid);
        List<VariableBinding> vbList = new ArrayList<VariableBinding>();
        vbList.add(vb);
        PDU pdu = new PDU(PDU.GET, vbList);
        ResponseEvent get;
        try {
            
            get = snmp.get(pdu, target);
            result = get.getResponse().get(0).getVariable();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(SnmpExtractImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public void addQueryOID(OID oid) {
       oids.add(oid);
    }

    @Override
    public boolean isInitialized() {
        if (initialized && snmp != null) return true;
        else return false;
    }

   
}
