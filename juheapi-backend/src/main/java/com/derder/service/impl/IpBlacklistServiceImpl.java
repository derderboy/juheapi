package com.derder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.derder.mapper.IpBlacklistMapper;
import com.derder.service.IpBlacklistService;
import com.derder.model.entity.IpBlacklist;
import org.springframework.stereotype.Service;

/**
* @author derder
* @description 针对表【ip_blacklist(IP黑名单)】的数据库操作Service实现
* @createDate 2024-06-10 00:25:15
*/
@Service
public class IpBlacklistServiceImpl extends ServiceImpl<IpBlacklistMapper, IpBlacklist>
    implements IpBlacklistService {

}




