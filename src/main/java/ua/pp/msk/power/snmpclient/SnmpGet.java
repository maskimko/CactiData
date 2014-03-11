package ua.pp.msk.power.snmpclient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;




public interface SnmpGet {
	

	
            public void setQueryOIDs(String[] oids);
	
	    public void init() throws IOException;
	    public Map<String, String[]> queryAll();
	    public String[] query(String oid);
	    
	    public void setHostname(InetAddress ip);
           
	    public InetAddress getHostname();
	    public void setHostname(String address);
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
