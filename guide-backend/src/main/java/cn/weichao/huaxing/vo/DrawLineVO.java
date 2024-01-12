package cn.weichao.huaxing.vo;

import java.util.List;

public class DrawLineVO {

    private String macAddr;
    private List<CoordVO> coordsList;

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public List<CoordVO> getCoordsList() {
        return coordsList;
    }

    public void setCoordsList(List<CoordVO> coordsList) {
        this.coordsList = coordsList;
    }
}
