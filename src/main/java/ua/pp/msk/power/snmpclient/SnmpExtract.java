/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.pp.msk.power.snmpclient;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

/**
 *
 * @author maskimko
 */
public interface SnmpExtract {
    
            public void setQueryOIDs(String[] oids);
	
            public void reinit() throws IOException;
	    public void init() throws IOException;
	    public VariableBinding[] queryAll();
	    public VariableBinding[] query(OID oid);
            public Variable queryExact(OID oid);
            public void setQueryOIDs(OID[] oids);
            public void addQueryOID(OID oid);
	    
	    public void setHostname(InetAddress ip);
           
	    public InetAddress getHostname();
            public void setSnmpAddress(String address);
	    public void setCommuntity(String community);
	    public String getCommunity();
	    public void setVersion(int ver);
	    public int getVersion();
	    public void setPort(int port);
	    public int getPort();
	    public void setTimeout(long millis);
	    public long getTimeout();
	    public void setRetries(int retries);
	    public int getRetries();
            public void setReceiveAddress(InetAddress addr, int port);
            public void close();
	    
}
