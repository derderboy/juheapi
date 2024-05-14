package com.derder.service.impl.inner;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.derder.mapper.InvokeInterfaceInfoMapper;
import com.derder.model.entity.InvokeInterfaceInfo;
import com.derder.service.InnerInvokeInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.Date;

/**
* @author derder
* &#064;description  针对表【invoke_interface_info(调用接口)】的数据库操作Service实现
* &#064;createDate  2024-05-12 22:19:06
 */
@DubboService
public class InnerInvokeInterfaceInfoServiceImpl extends ServiceImpl<InvokeInterfaceInfoMapper, InvokeInterfaceInfo>
implements InnerInvokeInterfaceInfoService {
    @Resource
    private InvokeInterfaceInfoMapper invokeInterfaceInfoMapper;

    @Override
    public boolean addInvokeInterfaceInfo(Long uid, Long interfaceId, String ip) {
        InvokeInterfaceInfo invokeInterfaceInfo = new InvokeInterfaceInfo();
        invokeInterfaceInfo.setUserid(uid);
        invokeInterfaceInfo.setInterfaceinfoid(interfaceId);
        invokeInterfaceInfo.setIp(ip);
        invokeInterfaceInfo.setCreatetime(new Date());
        int result = invokeInterfaceInfoMapper.insert(invokeInterfaceInfo);
        return result==1;
    }
}
