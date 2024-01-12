package cn.weichao.huaxing.cache;

import cn.hutool.core.lang.hash.Hash;
import cn.weichao.huaxing.vo.CoordVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DrawLineCache {

    private List<CoordVO> coordsCache;

    private Set<String> userIdList;

    private Set<String> producerUserIdList;
    public List<CoordVO> getCoordsCache() {
        return coordsCache;
    }

    public void setCoordsCache(List<CoordVO> coordsCache) {
        this.coordsCache = coordsCache;
    }

    public Set<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(Set<String> userIdList) {
        this.userIdList = userIdList;
    }

    public void addUserId(String userId) {
        if (this.userIdList == null) {
            this.userIdList = new HashSet<>();
        }
        this.userIdList.add(userId);
    }

    public void removeUserId(String userId) {
        if (this.userIdList == null) {
            this.userIdList = new HashSet<>();
        }
        this.userIdList.remove(userId);
    }

    public Set<String> getProducerUserIdList() {
        return producerUserIdList;
    }

    public void setProducerUserIdList(Set<String> producerUserIdList) {
        this.producerUserIdList = producerUserIdList;
    }


    public void addProducerUserId(String userId) {
        if (this.producerUserIdList == null) {
            this.producerUserIdList = new HashSet<>();
        }
        this.producerUserIdList.add(userId);
    }

    public void removeProducerUserId(String userId) {
        if (this.producerUserIdList == null) {
            this.producerUserIdList = new HashSet<>();
        }
        this.producerUserIdList.remove(userId);
    }
}
