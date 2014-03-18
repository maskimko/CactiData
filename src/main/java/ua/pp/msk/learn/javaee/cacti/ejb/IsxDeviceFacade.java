/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.pp.msk.learn.javaee.cacti.ejb;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import ua.pp.msk.learn.javaee.cacti.jsf.util.ISXDevice;
import ua.pp.msk.learn.javaee.cacti.model.PollerItem;

import ua.pp.msk.power.snmpclient.DeviceManager;
import ua.pp.msk.power.snmpclient.ISXDeviceManager;

/**
 *
 * @author maskimko
 */
@Stateless
public class IsxDeviceFacade extends AbstractSnmpFacade<ISXDevice>{

  @EJB
private ISXPollerItemFacade pollerItemFacade;
  @Inject
  private ISXDeviceManager deviceManager;
    
    private String isxIpPattern;
    public IsxDeviceFacade() {
        super(ISXDevice.class);
    }
    
    @PostConstruct
    private void  init(){
        isxIpPattern = ResourceBundle.getBundle("/PollerItem/Bundle").getString("ISXIpPattern");
        if (isxIpPattern == null) isxIpPattern = "10.1.20.%";
      List<PollerItem> findAll = pollerItemFacade.findAll();
        for (PollerItem pi : findAll) {
            upsertDevice(pi);
        }
    }

    
    private void upsertDevice(PollerItem pi){
        String hostname = pi.getHostname();
        int port = pi.getSnmpPort();
        InetAddress addr;
      try {
          addr = InetAddress.getByName(hostname);
          ISXDevice current = new ISXDevice(addr, port, null, 0);
          ISXDevice device = (ISXDevice) getDeviceManager().find(current);
        if (device == null) {
            current.setSnmpCommunity(pi.getSnmpCommunity());
            current.setSnmpVersion(pi.getSnmpVersion());
            getDeviceManager().addDevice(current);
        }
      } catch (UnknownHostException ex) {
          Logger.getLogger(IsxDeviceFacade.class.getName()).log(Level.SEVERE, null, ex);
      }        
    }
    
    @Override
   public DeviceManager getDeviceManager(){
       return deviceManager;
   
   }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
   
    
}

