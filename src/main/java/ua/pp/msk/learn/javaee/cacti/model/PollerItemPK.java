/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.pp.msk.learn.javaee.cacti.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author maskimko
 */
@Embeddable
public class PollerItemPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "local_data_id")
    private int localDataId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 19)
    @Column(name = "rrd_name")
    private String rrdName;

    public PollerItemPK() {
    }

    public PollerItemPK(int localDataId, String rrdName) {
        this.localDataId = localDataId;
        this.rrdName = rrdName;
    }

    public int getLocalDataId() {
        return localDataId;
    }

    public void setLocalDataId(int localDataId) {
        this.localDataId = localDataId;
    }

    public String getRrdName() {
        return rrdName;
    }

    public void setRrdName(String rrdName) {
        this.rrdName = rrdName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) localDataId;
        hash += (rrdName != null ? rrdName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PollerItemPK)) {
            return false;
        }
        PollerItemPK other = (PollerItemPK) object;
        if (this.localDataId != other.localDataId) {
            return false;
        }
        if ((this.rrdName == null && other.rrdName != null) || (this.rrdName != null && !this.rrdName.equals(other.rrdName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.pp.msk.learn.javaee.cacti.model.PollerItemPK[ localDataId=" + localDataId + ", rrdName=" + rrdName + " ]";
    }

}
