package com.derder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.derder.mapper.InvokeInterfaceInfoMapper;
import com.derder.model.entity.InvokeInterfaceInfo;
import com.derder.service.InvokeInterfaceInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author derder
* @description 针对表【invoke_interface_info(调用接口)】的数据库操作Service实现
* @createDate 2024-05-12 22:19:06
*/
@Service
public class InvokeInterfaceInfoServiceImpl extends ServiceImpl<InvokeInterfaceInfoMapper, InvokeInterfaceInfo>
implements InvokeInterfaceInfoService {
    @Resource
    private InvokeInterfaceInfoMapper invokeInterfaceInfoMapper;

    @Override
    public List<InvokeInterfaceInfo> interfaceDetection() {
        return invokeInterfaceInfoMapper.interfaceDetection();
    }
}
