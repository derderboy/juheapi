package com.derder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.derder.model.entity.InvokeInterfaceInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author derder
* @description 针对表【invoke_interface_info(调用接口)】的数据库操作Mapper
* @createDate 2024-05-12 22:19:06
* @Entity generator.domain.InvokeInterfaceInfo
*/
public interface InvokeInterfaceInfoMapper extends BaseMapper<InvokeInterfaceInfo> {
    @Select("SELECT `ip`,`userId` FROM `invoke_interface_info` WHERE `createTime` > NOW() - INTERVAL 10 second GROUP BY `ip` HAVING COUNT(*) > 10;")
    List<InvokeInterfaceInfo> interfaceDetection();
}
