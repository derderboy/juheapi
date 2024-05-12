package com.derder.controller.invoke;

import com.derder.JuHeApiConfig;
import com.derder.apiservice.impl.ApiServiceImpl;
import com.derder.client.JuHeApiClient;
import com.derder.common.BaseResponse;
import com.derder.common.ErrorCode;
import com.derder.common.ResultUtils;
import com.derder.exception.BusinessException;
import com.derder.model.dto.interfaceinfo.InterfaceInfoInvokeRequest;
import com.derder.model.entity.InterfaceInfo;
import com.derder.model.entity.User;
import com.derder.model.enums.InterfaceInfoStatusEnum;
import com.derder.service.InterfaceInfoService;
import com.derder.service.UserService;
import com.derder.strategy.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/invoke")
public class InterfaceController {

    @Resource
    private BaseContext baseContext;


    @Resource
    private InterfaceInfoService interfaceInfoService;
    @Resource
    private UserService userService;

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
        User loginUser = userService.getLoginUser(request);
        String method = oldInterfaceInfo.getMethod();
//        BaseContext baseContext = definitionBaseContext(loginUser);
        String uri = oldInterfaceInfo.getUri();
        log.info(uri);
        String result = baseContext.handler(uri, userRequestParams, method);
        return ResultUtils.success(result);
    }
//    private BaseContext definitionBaseContext(User loginUser) {
//        String accessKey = loginUser.getAccessKey();
//        String secretKey = loginUser.getSecretKey();
//
//        // 创建ApiServiceImpl实例
//        ApiServiceImpl apiService = new ApiServiceImpl();
//        // 设置ApiServiceImpl的ApiClient属性
//        apiService.setApiClient(new JuHeApiClient(accessKey, secretKey));
////        apiService.setApiClient(juHeApiClient);
//        // 设置baseContext的ApiClient属性
//
//        baseContext.setApiClient(apiService);
//        return baseContext;
//    }
}
