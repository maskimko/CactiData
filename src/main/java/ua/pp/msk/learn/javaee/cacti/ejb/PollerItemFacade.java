/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.pp.msk.learn.javaee.cacti.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ua.pp.msk.learn.javaee.cacti.model.PollerItem;

/**
 *
 * @author maskimko
 */
@Stateless
public class PollerItemFacade extends AbstractFacade<PollerItem> {
    @PersistenceContext(unitName = "vm-cacti-new")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PollerItemFacade() {
        super(PollerItem.class);
    }
    
}
