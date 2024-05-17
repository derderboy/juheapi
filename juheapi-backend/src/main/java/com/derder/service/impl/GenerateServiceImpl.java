package com.derder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.derder.mapper.GenerateMapper;
import com.derder.model.entity.Generate;
import com.derder.service.GenerateService;
import org.springframework.stereotype.Service;

/**
* @author derder
* @description 针对表【generate】的数据库操作Service实现
* @createDate 2024-05-17 21:03:40
*/
@Service
public class GenerateServiceImpl extends ServiceImpl<GenerateMapper, Generate>
    implements GenerateService {
}




