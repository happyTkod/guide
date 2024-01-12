package cn.weichao.huaxing.handler;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import cn.weichao.huaxing.cache.DrawLineCache;
import cn.weichao.huaxing.service.ProducerWebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Set;

@Component
public class ProducerWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    public ProducerWebSocketService producerWebSocketService;

    @Autowired
    public DrawLineCache cache;


    /**
     * 将已经链接到服务器的终端唯一标识推送给命令发出端
     * @param macAddr
     */
    public void doSendProducer() {
        Set<String> prducerUserIdList = cache.getProducerUserIdList();
        if(CollectionUtil.isEmpty(prducerUserIdList)) {
            return;
        }

//        CoordsWs ws = new CoordsWs();
//        ws.setMsg(macAddr);
        producerWebSocketService.sendMoreMessage(prducerUserIdList.toArray(new String[prducerUserIdList.size()]), JSONUtil.toJsonStr(cache.getUserIdList()));
    }
}
