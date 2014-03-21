/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.learn.javaee.cacti.model;

/**
 *
 * @author maskimko
 */
public class PollerItemResult extends PollerItem {

    private int result;

    public PollerItemResult() {
    }

    public PollerItemResult(PollerItem pi) {
        super();
        super.setAction(pi.getAction());
        super.setArg1(pi.getArg1());
        super.setArg2(pi.getArg2());
        super.setArg3(pi.getArg3());
        super.setHostId(pi.getHostId());
        super.setHostname(pi.getHostname());
        super.setPollerId(pi.getPollerId());
        super.setPollerItemPK(pi.getPollerItemPK());
        super.setPresent(pi.getPresent());
        super.setRrdNextStep(pi.getRrdStep());
        super.setRrdNum(pi.getRrdNum());
        super.setRrdPath(pi.getRrdPath());
        super.setRrdStep(pi.getRrdStep());
        super.setSnmpAuthProtocol(pi.getSnmpAuthProtocol());
        super.setSnmpCommunity(pi.getSnmpCommunity());
        super.setSnmpContext(pi.getSnmpContext());
        super.setSnmpPassword(pi.getSnmpPassword());
        super.setSnmpPort(pi.getSnmpPort());
        super.setSnmpPrivPassphrase(pi.getSnmpPrivPassphrase());
        super.setSnmpPrivProtocol(pi.getSnmpPrivProtocol());
        super.setSnmpTimeout(pi.getSnmpTimeout());
        super.setSnmpUsername(pi.getSnmpUsername());
        super.setSnmpVersion(pi.getSnmpVersion());

    }
    
    public PollerItemResult(PollerItem pi, int result){
        this(pi);
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

}
