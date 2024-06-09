package com.derder.scheduled;

import com.derder.model.entity.IpBlacklist;
import com.derder.service.IpBlacklistService;
import com.derder.utils.redis.RedisUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class BlackIpListScheduled {
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private IpBlacklistService ipBlacklistService;

    /**
     * 定时任务，每60秒同步黑名单到Redis
     */
    @Scheduled(fixedDelay = 6000) // 每60秒执行一次
    public void syncBlacklistToRedis() {
        List<IpBlacklist> blacklistedIPs = ipBlacklistService.list();
        redisUtil.del("blacklist");
        blacklistedIPs.forEach(ipInfo -> {
            redisUtil.sSet("blacklist", ipInfo.getIp_address());
        });
    }

}
