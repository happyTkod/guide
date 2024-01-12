package cn.weichao.huaxing.bean;

import java.util.List;

public class CoordsWs {

    private String macAddr;

    private List<List<Float>> msg;

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public List<List<Float>> getMsg() {
        return msg;
    }

    public void setMsg(List<List<Float>> msg) {
        this.msg = msg;
    }
}
