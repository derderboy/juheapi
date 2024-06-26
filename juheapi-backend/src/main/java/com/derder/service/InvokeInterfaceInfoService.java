package com.derder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.derder.model.entity.InvokeInterfaceInfo;

import java.util.List;

/**
* @author derder
* @description 针对表【invoke_interface_info(调用接口)】的数据库操作Service
* @createDate 2024-05-12 22:19:06
*/
public interface InvokeInterfaceInfoService extends IService<InvokeInterfaceInfo> {
    List<InvokeInterfaceInfo> interfaceDetection();
}
