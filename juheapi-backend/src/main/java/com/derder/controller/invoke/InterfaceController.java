package com.derder.controller.invoke;

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
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/invoke")
public class InterfaceController {

    @Resource
    private InterfaceInfoService interfaceInfoService;
    @Resource
    private UserService userService;


    @GetMapping("/RandomNumber")
    public String RandomNumber(HttpServletRequest request){
        User loginUser = userService.getLoginUser(request);
        String accessKey = loginUser.getAccessKey();
        String secretKey = loginUser.getSecretKey();
        JuHeApiClient tempClient = new JuHeApiClient(accessKey, secretKey);
        return tempClient.getRandomNumber();
    }

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
        String accessKey = loginUser.getAccessKey();
        String secretKey = loginUser.getSecretKey();
        JuHeApiClient tempClient = new JuHeApiClient(accessKey, secretKey);
        Gson gson = new Gson();
        com.derder.model.User user = gson.fromJson(userRequestParams, com.derder.model.User.class);
        String usernameByPost = tempClient.getUsernameByPost(user);
        return ResultUtils.success(usernameByPost);
    }

}
