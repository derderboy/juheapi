package com.derder.controller.invoke;

import com.derder.common.BaseResponse;
import com.derder.common.ErrorCode;
import com.derder.common.ResultUtils;
import com.derder.exception.BusinessException;
import com.derder.model.dto.interfaceinfo.InterfaceInfoInvokeRequest;
import com.derder.model.entity.InterfaceInfo;
import com.derder.model.enums.InterfaceInfoStatusEnum;
import com.derder.service.InterfaceInfoService;
import com.derder.strategy.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/invoke")
public class InvokeInterfaceController {

    @Resource
    private BaseContext baseContext;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    /**
     * 测试调用
     *
     * @param interfaceInfoInvokeRequest
     * @param request
     * @return
     */
    @PostMapping("/invoke")
    public BaseResponse<Object> invokeInterfaceInfo(@RequestBody InterfaceInfoInvokeRequest interfaceInfoInvokeRequest,
                                                    HttpServletRequest request) {
        if (interfaceInfoInvokeRequest == null || interfaceInfoInvokeRequest.getId() < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long id = interfaceInfoInvokeRequest.getId();
        String userRequestParams = interfaceInfoInvokeRequest.getUserRequestParams();
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        if (oldInterfaceInfo.getStatus() == InterfaceInfoStatusEnum.OFFLINE.getValue()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口已关闭");
        }
        String method = oldInterfaceInfo.getMethod();
        String uri = oldInterfaceInfo.getUri();
        log.info(uri);
        String result = baseContext.handler(uri, userRequestParams, method);
        return ResultUtils.success(result);
    }
}
