package com.derder.scheduled;

import com.derder.model.entity.IpBlacklist;
import com.derder.service.IpBlacklistService;
import com.derder.utils.redis.RedisUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 定时任务，每60秒同步黑名单到Redis
     */
    @Scheduled(fixedDelay = 60000) // 每60秒执行一次
    public void syncBlacklistToRedis() {
        List<IpBlacklist> blacklistedIPs = ipBlacklistService.list();
        blacklistedIPs.forEach(ipInfo -> {
            System.out.println("ipInfo: " + ipInfo);
        });
        redisUtil.del("blacklist");
        blacklistedIPs.forEach(ipInfo -> {
            redisUtil.sSet("blacklist", ipInfo.getIpAddress());
        });
        rabbitTemplate.convertAndSend("", "ipBlack.queue", "");
    }

}
