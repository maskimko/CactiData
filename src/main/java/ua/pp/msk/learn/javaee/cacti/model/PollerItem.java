/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.pp.msk.learn.javaee.cacti.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maskimko
 */
@Entity
@Table(name = "poller_item", catalog = "cacti", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PollerItem.findAll", query = "SELECT p FROM PollerItem p"),
    @NamedQuery(name = "PollerItem.findByLocalDataId", query = "SELECT p FROM PollerItem p WHERE p.pollerItemPK.localDataId = :localDataId"),
    @NamedQuery(name = "PollerItem.findByPollerId", query = "SELECT p FROM PollerItem p WHERE p.pollerId = :pollerId"),
    @NamedQuery(name = "PollerItem.findByHostId", query = "SELECT p FROM PollerItem p WHERE p.hostId = :hostId"),
    @NamedQuery(name = "PollerItem.findByAction", query = "SELECT p FROM PollerItem p WHERE p.action = :action"),
    @NamedQuery(name = "PollerItem.findByPresent", query = "SELECT p FROM PollerItem p WHERE p.present = :present"),
    @NamedQuery(name = "PollerItem.findByHostname", query = "SELECT p FROM PollerItem p WHERE p.hostname = :hostname"),
    @NamedQuery(name = "PollerItem.findBySnmpCommunity", query = "SELECT p FROM PollerItem p WHERE p.snmpCommunity = :snmpCommunity"),
    @NamedQuery(name = "PollerItem.findBySnmpVersion", query = "SELECT p FROM PollerItem p WHERE p.snmpVersion = :snmpVersion"),
    @NamedQuery(name = "PollerItem.findBySnmpUsername", query = "SELECT p FROM PollerItem p WHERE p.snmpUsername = :snmpUsername"),
    @NamedQuery(name = "PollerItem.findBySnmpPassword", query = "SELECT p FROM PollerItem p WHERE p.snmpPassword = :snmpPassword"),
    @NamedQuery(name = "PollerItem.findBySnmpAuthProtocol", query = "SELECT p FROM PollerItem p WHERE p.snmpAuthProtocol = :snmpAuthProtocol"),
    @NamedQuery(name = "PollerItem.findBySnmpPrivPassphrase", query = "SELECT p FROM PollerItem p WHERE p.snmpPrivPassphrase = :snmpPrivPassphrase"),
    @NamedQuery(name = "PollerItem.findBySnmpPrivProtocol", query = "SELECT p FROM PollerItem p WHERE p.snmpPrivProtocol = :snmpPrivProtocol"),
    @NamedQuery(name = "PollerItem.findBySnmpContext", query = "SELECT p FROM PollerItem p WHERE p.snmpContext = :snmpContext"),
    @NamedQuery(name = "PollerItem.findBySnmpPort", query = "SELECT p FROM PollerItem p WHERE p.snmpPort = :snmpPort"),
    @NamedQuery(name = "PollerItem.findBySnmpTimeout", query = "SELECT p FROM PollerItem p WHERE p.snmpTimeout = :snmpTimeout"),
    @NamedQuery(name = "PollerItem.findByRrdName", query = "SELECT p FROM PollerItem p WHERE p.pollerItemPK.rrdName = :rrdName"),
    @NamedQuery(name = "PollerItem.findByRrdPath", query = "SELECT p FROM PollerItem p WHERE p.rrdPath = :rrdPath"),
    @NamedQuery(name = "PollerItem.findByRrdNum", query = "SELECT p FROM PollerItem p WHERE p.rrdNum = :rrdNum"),
    @NamedQuery(name = "PollerItem.findByRrdStep", query = "SELECT p FROM PollerItem p WHERE p.rrdStep = :rrdStep"),
    @NamedQuery(name = "PollerItem.findByRrdNextStep", query = "SELECT p FROM PollerItem p WHERE p.rrdNextStep = :rrdNextStep"),
    @NamedQuery(name = "PollerItem.findByArg2", query = "SELECT p FROM PollerItem p WHERE p.arg2 = :arg2"),
    @NamedQuery(name = "PollerItem.findByArg3", query = "SELECT p FROM PollerItem p WHERE p.arg3 = :arg3")})
public class PollerItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PollerItemPK pollerItemPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "poller_id")
    private short pollerId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "host_id")
    private int hostId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "action")
    private short action;
    @Basic(optional = false)
    @NotNull
    @Column(name = "present")
    private short present;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "hostname")
    private String hostname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "snmp_community")
    private String snmpCommunity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "snmp_version")
    private boolean snmpVersion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "snmp_username")
    private String snmpUsername;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "snmp_password")
    private String snmpPassword;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "snmp_auth_protocol")
    private String snmpAuthProtocol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "snmp_priv_passphrase")
    private String snmpPrivPassphrase;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "snmp_priv_protocol")
    private String snmpPrivProtocol;
    @Size(max = 64)
    @Column(name = "snmp_context")
    private String snmpContext;
    @Basic(optional = false)
    @NotNull
    @Column(name = "snmp_port")
    private int snmpPort;
    @Basic(optional = false)
    @NotNull
    @Column(name = "snmp_timeout")
    private int snmpTimeout;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "rrd_path")
    private String rrdPath;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rrd_num")
    private short rrdNum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rrd_step")
    private int rrdStep;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rrd_next_step")
    private int rrdNextStep;
    @Lob
    @Size(max = 65535)
    @Column(name = "arg1")
    private String arg1;
    @Size(max = 255)
    @Column(name = "arg2")
    private String arg2;
    @Size(max = 255)
    @Column(name = "arg3")
    private String arg3;

    public PollerItem() {
    }

    public PollerItem(PollerItemPK pollerItemPK) {
        this.pollerItemPK = pollerItemPK;
    }

    public PollerItem(PollerItemPK pollerItemPK, short pollerId, int hostId, short action, short present, String hostname, String snmpCommunity, boolean snmpVersion, String snmpUsername, String snmpPassword, String snmpAuthProtocol, String snmpPrivPassphrase, String snmpPrivProtocol, int snmpPort, int snmpTimeout, String rrdPath, short rrdNum, int rrdStep, int rrdNextStep) {
        this.pollerItemPK = pollerItemPK;
        this.pollerId = pollerId;
        this.hostId = hostId;
        this.action = action;
        this.present = present;
        this.hostname = hostname;
        this.snmpCommunity = snmpCommunity;
        this.snmpVersion = snmpVersion;
        this.snmpUsername = snmpUsername;
        this.snmpPassword = snmpPassword;
        this.snmpAuthProtocol = snmpAuthProtocol;
        this.snmpPrivPassphrase = snmpPrivPassphrase;
        this.snmpPrivProtocol = snmpPrivProtocol;
        this.snmpPort = snmpPort;
        this.snmpTimeout = snmpTimeout;
        this.rrdPath = rrdPath;
        this.rrdNum = rrdNum;
        this.rrdStep = rrdStep;
        this.rrdNextStep = rrdNextStep;
    }

    public PollerItem(int localDataId, String rrdName) {
        this.pollerItemPK = new PollerItemPK(localDataId, rrdName);
    }

    public PollerItemPK getPollerItemPK() {
        return pollerItemPK;
    }

    public void setPollerItemPK(PollerItemPK pollerItemPK) {
        this.pollerItemPK = pollerItemPK;
    }

    public short getPollerId() {
        return pollerId;
    }

    public void setPollerId(short pollerId) {
        this.pollerId = pollerId;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public short getAction() {
        return action;
    }

    public void setAction(short action) {
        this.action = action;
    }

    public short getPresent() {
        return present;
    }

    public void setPresent(short present) {
        this.present = present;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getSnmpCommunity() {
        return snmpCommunity;
    }

    public void setSnmpCommunity(String snmpCommunity) {
        this.snmpCommunity = snmpCommunity;
    }

    public boolean getSnmpVersion() {
        return snmpVersion;
    }

    public void setSnmpVersion(boolean snmpVersion) {
        this.snmpVersion = snmpVersion;
    }

    public String getSnmpUsername() {
        return snmpUsername;
    }

    public void setSnmpUsername(String snmpUsername) {
        this.snmpUsername = snmpUsername;
    }

    public String getSnmpPassword() {
        return snmpPassword;
    }

    public void setSnmpPassword(String snmpPassword) {
        this.snmpPassword = snmpPassword;
    }

    public String getSnmpAuthProtocol() {
        return snmpAuthProtocol;
    }

    public void setSnmpAuthProtocol(String snmpAuthProtocol) {
        this.snmpAuthProtocol = snmpAuthProtocol;
    }

    public String getSnmpPrivPassphrase() {
        return snmpPrivPassphrase;
    }

    public void setSnmpPrivPassphrase(String snmpPrivPassphrase) {
        this.snmpPrivPassphrase = snmpPrivPassphrase;
    }

    public String getSnmpPrivProtocol() {
        return snmpPrivProtocol;
    }

    public void setSnmpPrivProtocol(String snmpPrivProtocol) {
        this.snmpPrivProtocol = snmpPrivProtocol;
    }

    public String getSnmpContext() {
        return snmpContext;
    }

    public void setSnmpContext(String snmpContext) {
        this.snmpContext = snmpContext;
    }

    public int getSnmpPort() {
        return snmpPort;
    }

    public void setSnmpPort(int snmpPort) {
        this.snmpPort = snmpPort;
    }

    public int getSnmpTimeout() {
        return snmpTimeout;
    }

    public void setSnmpTimeout(int snmpTimeout) {
        this.snmpTimeout = snmpTimeout;
    }

    public String getRrdPath() {
        return rrdPath;
    }

    public void setRrdPath(String rrdPath) {
        this.rrdPath = rrdPath;
    }

    public short getRrdNum() {
        return rrdNum;
    }

    public void setRrdNum(short rrdNum) {
        this.rrdNum = rrdNum;
    }

    public int getRrdStep() {
        return rrdStep;
    }

    public void setRrdStep(int rrdStep) {
        this.rrdStep = rrdStep;
    }

    public int getRrdNextStep() {
        return rrdNextStep;
    }

    public void setRrdNextStep(int rrdNextStep) {
        this.rrdNextStep = rrdNextStep;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public String getArg3() {
        return arg3;
    }

    public void setArg3(String arg3) {
        this.arg3 = arg3;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pollerItemPK != null ? pollerItemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PollerItem)) {
            return false;
        }
        PollerItem other = (PollerItem) object;
        if ((this.pollerItemPK == null && other.pollerItemPK != null) || (this.pollerItemPK != null && !this.pollerItemPK.equals(other.pollerItemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.pp.msk.learn.javaee.cacti.model.PollerItem[ pollerItemPK=" + pollerItemPK + " ]";
    }
    
}
