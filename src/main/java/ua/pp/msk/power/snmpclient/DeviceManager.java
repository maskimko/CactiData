/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.pp.msk.power.snmpclient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import ua.pp.msk.learn.javaee.cacti.jsf.util.Device;
import ua.pp.msk.learn.javaee.cacti.jsf.util.ISXDevice;

/**
 *
 * @author maskimko
 * @param <T>
 */
public abstract class DeviceManager<T> implements Serializable{
    
    private Executor executor = null;
    
    private Class<T> deviceClass;
    
    public DeviceManager(Class<T> deviceClass){
        this.deviceClass = deviceClass;
        executor =  Executors.newSingleThreadExecutor();
    }
    
    public abstract T find(T device);
    public abstract List<T> findAll();
    public  void populateDevice(T device){
        Task task = new Task(device);
        executor.execute(task);
    }
    public abstract void preparePopulateDevice(T device);
    public abstract void populateDevice(List<T> devices);
    public abstract void addDevice(T device);
    public abstract void removeDevice(T device);
    
    class Task implements Runnable {
        
        T  device;
        
        Task(T device) {
            this.device = device;
        }
        @Override
        public void run() {
            preparePopulateDevice(device);
        }
        
    }
    
}
