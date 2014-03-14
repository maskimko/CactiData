/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.pp.msk.power.snmpclient;

import java.util.List;

/**
 *
 * @author maskimko
 */
public abstract class DeviceController<T> {
    
    private Class<T> deviceClass;
    
    public DeviceController(Class<T> deviceClass){
        this.deviceClass = deviceClass;
    }
    
    public abstract T find(T device);
    public abstract void populateDevice(T device);
    public abstract void populateDevice(List<T> devices);
    
}
