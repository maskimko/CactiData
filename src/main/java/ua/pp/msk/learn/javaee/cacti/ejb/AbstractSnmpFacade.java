/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.learn.javaee.cacti.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.validation.constraints.Size;
import org.apache.log4j.Logger;
import ua.pp.msk.power.snmpclient.DeviceManager;

/**
 *
 * @author maskimko
 */
public abstract class AbstractSnmpFacade<T> {

    private Class<T> snmpDeviceClass;

    public AbstractSnmpFacade(Class<T> snmpDeviceClass) {
        this.snmpDeviceClass = snmpDeviceClass;
    }

    protected abstract DeviceManager getDeviceManager();

    public void create(T device) {
        getDeviceManager().addDevice(device);
    }

    public void edit(T device) {
        getDeviceManager().removeDevice(device);
        getDeviceManager().addDevice(device);

    }

    public void remove(T device) {
        getDeviceManager().addDevice(device);
    }

    @SuppressWarnings("unchecked")
    public T find(T device) {
        return (T) getDeviceManager().find(device);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return (List<T>) getDeviceManager().findAll();
    }

    public List<T> findRange(int[] range) {
        List<T> findRange = new ArrayList<T>();
        List<T> findAll = findAll();
        if (range.length != 2) {
            throw new IllegalArgumentException("Range array size must be 2");
        }
        if (range[0] > range[1]) {
            throw new IllegalArgumentException("First item in range must be less than second");
        }
        if (range[1] > findAll.size()) {
            for (int i = range[0]; i < findAll.size(); i++) {
                findRange.add(findAll.get(i));
            }
        } else {
            for (int i = range[0]; i < range[1]; i++) {
                findRange.add(findAll.get(i));
            }
        }
        return findRange;
    }

    public int count() {
        return getDeviceManager().findAll().size();
    }

    public void populate(T device){
        getDeviceManager().populateDevice((T) device);
    }
    
    
}
