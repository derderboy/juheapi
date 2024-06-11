package com.derder.scheduled;

import com.derder.model.entity.InvokeInterfaceInfo;
import com.derder.model.entity.IpBlacklist;
import com.derder.service.InvokeInterfaceInfoService;
import com.derder.service.IpBlacklistService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class InterfaceDetectionScheduled {
    @Resource
    private InvokeInterfaceInfoService invokeInterfaceInfoService;
    @Resource
    private IpBlacklistService ipBlacklistService;
    @Scheduled(fixedDelay = 10000) // 每10秒执行一次,筛选出最近十秒调用超过1000次的ip
    public void addBlacklistToMysql() {
        List<InvokeInterfaceInfo> blackList = invokeInterfaceInfoService.interfaceDetection();
        Collection<IpBlacklist> blackIPList = new ArrayList<>();
        if(!blackList.isEmpty()){
            for (InvokeInterfaceInfo invokeInterfaceInfo : blackList) {
                blackIPList.add(new IpBlacklist(invokeInterfaceInfo.getIp(), invokeInterfaceInfo.getUserid()));
            }
            ipBlacklistService.saveBatch(blackIPList);
        }
    }
}
