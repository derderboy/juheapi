package com.derder.controller;

import com.derder.model.entity.IpBlacklist;
import com.derder.service.IpBlacklistService;
import com.derder.utils.redis.RedisUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 黑名单控制类
 * @author derder
 * Date: 2024/6/8 22:46:55
 */
@RestController
@RequestMapping("/blackList")
public class IpBlackController implements ApplicationContextAware {
    private static ApplicationContext context;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private IpBlacklistService ipBlacklistService;

    /**
     * 获取黑名单列表
     * @return List<IpBlacklist>
     */
    @GetMapping("getBlacklist")
    public List<IpBlacklist> getBlacklist() {
        return ipBlacklistService.list();
    }

    /**
     * 判断IP是否在黑名单中
     * @param ip String
     * @return boolean
     */
    @GetMapping("isBlack")
    public boolean isBlack(@RequestParam String ip) {
        return redisUtil.sHasKey("blacklist", ip);
    }
    /**
     * 添加黑名单
     * @param ipBlacklist
     * @return
     */
    @PostMapping("addBlack")
    public boolean addBlack(@RequestBody IpBlacklist ipBlacklist) {
        return ipBlacklistService.save(ipBlacklist);
    }

    /**
     * 删除黑名单
     * @param id
     * @return
     */
    @DeleteMapping("deleteBlack/{id}")
    public boolean deleteBlack(@PathVariable Integer id) {
        return ipBlacklistService.removeById(id);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
