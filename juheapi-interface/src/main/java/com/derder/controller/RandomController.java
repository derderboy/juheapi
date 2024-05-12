package com.derder.controller;

import cn.hutool.core.util.RandomUtil;
import com.derder.common.BaseResponse;
import com.derder.model.enums.ResultTypeEnum;
import com.derder.model.vo.ResponseVO;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController()
@RequestMapping()
public class RandomController {
    @GetMapping("/RandomNumber")
    public String getRandomNumber(){
        return new Gson().toJson(new BaseResponse<>(200, new ResponseVO(ResultTypeEnum.STRING.getValue(),RandomUtil.randomNumbers(3))));
    }

    @GetMapping("/randomImage")
    public String getRandomImage(){
        int randomIndex = RandomUtil.randomInt(1,6);
        System.out.println(randomIndex);
        String imageFiles = "E:\\桌面\\juheapi\\juheapi-interface\\src\\main\\resources\\static\\image\\"+randomIndex+".jpg";
        File randomImageFile = new File(imageFiles);
        try {
            byte[] imageBytes = Files.readAllBytes(randomImageFile.toPath());
           return new Gson().toJson(new BaseResponse<>(200, new ResponseVO(ResultTypeEnum.IMAGE.getValue(), ResponseEntity.ok()
                                .body(imageBytes))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
