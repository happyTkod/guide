package cn.weichao.huaxing.controller;

import cn.hutool.json.JSONUtil;
import cn.weichao.huaxing.cache.DrawLineCache;
import cn.weichao.huaxing.handler.WebSocketHandler;
import cn.weichao.huaxing.vo.BaseResult;
import cn.weichao.huaxing.vo.DrawLineVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/draw")
@Slf4j
public class DrawLineController {

    @Autowired
    private DrawLineCache drawLineCache;

    @Autowired
    private WebSocketHandler webSocketHandler;

    @RequestMapping("/drawLine")
    public BaseResult drawLine(@RequestBody DrawLineVO drawLine) {
        if (drawLine == null || CollectionUtils.isEmpty(drawLine.getCoordsList())) {
            return BaseResult.fail(null);
        }
        drawLineCache.setCoordsCache(drawLine.getCoordsList());
        log.info("当前画线的坐标集为：{}", JSONUtil.toJsonStr(drawLine.getCoordsList()));
        // 推送
        webSocketHandler.doSend(drawLine.getCoordsList(), drawLine.getMacAddr());
        return BaseResult.success("插入成功");
    }

}
