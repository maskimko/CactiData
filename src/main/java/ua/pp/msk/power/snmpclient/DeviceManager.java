/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.pp.msk.power.snmpclient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ua.pp.msk.learn.javaee.cacti.jsf.util.ISXDevice;

/**
 *
 * @author maskimko
 * @param <T>
 */
public abstract class DeviceManager<T> {
    
    
    
    private Class<T> deviceClass;
    
    public DeviceManager(Class<T> deviceClass){
        this.deviceClass = deviceClass;
    }
    
    public abstract T find(T device);
    public abstract List<T> findAll();
    public abstract void populateDevice(T device);
    public abstract void populateDevice(List<T> devices);
    public abstract void addDevice(T device);
    public abstract void removeDevice(T device);
    
}
