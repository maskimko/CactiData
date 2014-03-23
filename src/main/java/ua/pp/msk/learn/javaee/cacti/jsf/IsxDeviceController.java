/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.learn.javaee.cacti.jsf;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;
import ua.pp.msk.learn.javaee.cacti.ejb.IsxDeviceFacade;
import ua.pp.msk.learn.javaee.cacti.jsf.util.ISXDevice;
import ua.pp.msk.learn.javaee.cacti.jsf.util.JsfUtil;
import ua.pp.msk.learn.javaee.cacti.jsf.util.PaginationHelper;

/**
 *
 * @author maskimko
 */
@ManagedBean(name = "isxDeviceController")
@SessionScoped
public class IsxDeviceController implements Serializable {

    private ISXDevice current;

    @EJB
    private IsxDeviceFacade ejbFacade;

    public IsxDeviceController() {
    }

    private IsxDeviceFacade getFacade() {
        return ejbFacade;
    }

    public int getCount() {
        return getFacade().count();
    }

    public List<ISXDevice> getIsxDevices() {
        return getFacade().findAll();
    }

    //TODO rewrite converter
    public ISXDevice getCurrent() {
        return current;
    }

    public void setCurrent(ISXDevice current) {

        if (current != null) {
            Logger.getLogger(this.getClass().getName()).info("New item has been selected: " + current.getAddr().toString());
        } else {
            Logger.getLogger(this.getClass().getName()).info("Null value selection");
        }
        this.current = current;
    }
    
    public void populate(){
        if (current != null) {
            Logger.getLogger(this.getClass().getName()).info("Start population of: " + current.getAddr().toString());
            getFacade().populate(current);
        } else {
            Logger.getLogger(this.getClass().getName()).info("Cannot populate null value ISX Device");
        } 
    }
}
