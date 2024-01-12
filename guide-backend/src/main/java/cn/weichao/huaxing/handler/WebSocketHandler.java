package cn.weichao.huaxing.handler;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import cn.weichao.huaxing.service.WebSocketService;
import cn.weichao.huaxing.vo.CoordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    @Autowired
    public WebSocketService webSocketService;

    public void doSend(List<CoordVO> coordVOList, String macAddr) {
        if(CollectionUtil.isEmpty(coordVOList)) {
            return;
        }
        List<List<Float>> msg = new ArrayList<>();
        for(CoordVO vo : coordVOList) {
            List<Float> coords = new ArrayList<>();
            coords.add(vo.getX());
            coords.add(vo.getY());
            msg.add(coords);
        }
//        CoordsWs ws = new CoordsWs();
//        ws.setMsg(macAddr);
        webSocketService.sendOneMessage(macAddr, JSONUtil.toJsonStr(msg));
    }
}
