package com.derder.service.impl.inner;

import com.derder.service.InnerIpBlackService;
import com.derder.utils.redis.RedisUtil;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 内部用户服务实现类
 */
@DubboService
public class InnerIpBlackServiceImpl implements InnerIpBlackService {
    @Resource
    private RedisUtil redisUtil;
    @Override
    public Set<Object> getBlackIpList() {
        System.out.println("=========InnerIpBlackServiceImpl.getBlackIpList==================");
        return redisUtil.sGet("blacklist");
    }
}
