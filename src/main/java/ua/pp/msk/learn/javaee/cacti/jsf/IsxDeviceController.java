/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.pp.msk.learn.javaee.cacti.jsf;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import ua.pp.msk.learn.javaee.cacti.ejb.ISXPollerItemFacade;
import ua.pp.msk.learn.javaee.cacti.jsf.util.ISXDevice;
import ua.pp.msk.learn.javaee.cacti.jsf.util.JsfUtil;
import ua.pp.msk.learn.javaee.cacti.jsf.util.PaginationHelper;
import ua.pp.msk.learn.javaee.cacti.model.PollerItem;

/**
 *
 * @author maskimko
 */
@Named("isxController")
@SessionScoped
public class IsxDeviceController implements Serializable {
    



    private int pageSize = 10;
    private ISXDevice current;
    private DataModel items = null;
    @EJB
    private ua.pp.msk.learn.javaee.cacti.ejb.ISXPollerItemFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public IsxDeviceController() {
    }

    public ISXDevice getSelected() {
        if (current == null) {
            current = new ISXDevice();
            //current.setPollerItemPK(new ua.pp.msk.learn.javaee.cacti.model.PollerItemPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private ISXDeviceFacade getFacade() {
        return ejbFacade;
    }

    public int getPageSize(){
        return this.pageSize;
    }
    public void setPageSize( int pageSize){
        this.pageSize = pageSize;
    }
    
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(pageSize) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (ISXDevice) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new ISXDevice();
        
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/PollerItem/Bundle").getString("IsxDeviceCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/PollerItem/Bundle").getString("SnmpErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (ISXDevice) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/PollerItem/Bundle").getString("PollerItemUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/PollerItem/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (ISXDevice) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/PollerItem/Bundle").getString("ISXDeivceDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/PollerItem/Bundle").getString("SnmpErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String resize() {
        pagination.setPageSize(pageSize);
        recreateModel();
        return "List";
    }
    
    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    
    //TODO rewrite converter
    @FacesConverter(forClass = PollerItem.class)
    public static class PollerItemControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ISXPollerItemController controller = (ISXPollerItemController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "isxPollerItemController");
            return controller.ejbFacade.find(getKey(value));
        }

        ua.pp.msk.learn.javaee.cacti.model.PollerItemPK getKey(String value) {
            ua.pp.msk.learn.javaee.cacti.model.PollerItemPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new ua.pp.msk.learn.javaee.cacti.model.PollerItemPK();
            key.setLocalDataId(Integer.parseInt(values[0]));
            key.setRrdName(values[1]);
            return key;
        }

        String getStringKey(ua.pp.msk.learn.javaee.cacti.model.PollerItemPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getLocalDataId());
            sb.append(SEPARATOR);
            sb.append(value.getRrdName());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PollerItem) {
                PollerItem o = (PollerItem) object;
                return getStringKey(o.getPollerItemPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PollerItem.class.getName());
            }
        }

    }

}

}
