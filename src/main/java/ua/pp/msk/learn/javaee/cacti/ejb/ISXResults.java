/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.pp.msk.learn.javaee.cacti.ejb;

import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import ua.pp.msk.learn.javaee.cacti.model.PollerItem;
import ua.pp.msk.learn.javaee.cacti.model.PollerItemResult;
import ua.pp.msk.learn.javaee.cacti.model.PollerItem_;

/**
 *
 * @author maskimko
 */
@Singleton
public class ISXResults  {
    
    @EJB
    private ISXPollerItemFacade isxPollerItemFacade;
    private List<PollerItemResult> results;  
    
    public ISXResults() {
        
    }
    
    @PostConstruct
    private void  init(){
       
        
    }

   
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
     public PollerItemResult find(Object id) {
        PollerItem pi = isxPollerItemFacade.getEntityManager().find(PollerItem.class, id);
        //TODO add snmp support!
        PollerItemResult pir = new PollerItemResult(pi);
    }

    @Override
    public List<PollerItem> findAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root<PollerItem> root = cq.from(PollerItem.class);
        cq.where(cb.like(root.get(PollerItem_.hostname), isxIpPattern));
        cq.select(root);
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<PollerItem> findRange(int[] range) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root<PollerItem> root = cq.from(PollerItem.class);
        cq.where(cb.like(root.get(PollerItem_.hostname), isxIpPattern));
        cq.select(root);
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Override
    public int count() {
       CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root<PollerItem> root = cq.from(PollerItem.class);
        cq.where(cb.like(root.get(PollerItem_.hostname), isxIpPattern));
        cq.select(root);
        cq.select(getEntityManager().getCriteriaBuilder().count(root));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
