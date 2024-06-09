package com.derder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.derder.mapper.IpBlacklistMapper;
import com.derder.model.entity.IpBlacklist;
import com.derder.service.IpBlacklistService;
import org.springframework.stereotype.Service;

/**
* @author derder
* @description 针对表【ip_blacklist(IP黑名单)】的数据库操作Service实现
* @createDate 2024-06-08 23:45:54
*/
@Service
public class IpBlacklistServiceImpl extends ServiceImpl<IpBlacklistMapper, IpBlacklist>
    implements IpBlacklistService {

}




