package com.derder.controller;

import com.derder.common.BaseResponse;
import com.derder.model.User;
import com.derder.model.enums.ResultTypeEnum;
import com.derder.model.vo.ResponseVO;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value = "/name")
public class NameController {
    @PostMapping("/user")
    public String getNameByPost(@RequestBody User user){
        return new Gson().toJson(new BaseResponse<>(
                200, new ResponseVO(ResultTypeEnum.STRING.getValue(), "用户名字是：" + user.getUsername())));
    }
}
