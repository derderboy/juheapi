package com.derder.controller;

import cn.hutool.core.util.RandomUtil;
import com.derder.common.BaseResponse;
import com.derder.model.enums.ResultTypeEnum;
import com.derder.model.vo.ResponseVO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping()
public class RandomController {
    @Autowired
    private ResourceLoader resourceLoader;
    @GetMapping("/randomNumber")
    public String getRandomNumber(){
        return new Gson().toJson(new BaseResponse<>(200, new ResponseVO(ResultTypeEnum.STRING.getValue(),RandomUtil.randomNumbers(3))));
    }

    @GetMapping("/randomImage")
    public String getRandomImage(){
        int randomIndex = RandomUtil.randomInt(1,6);
        System.out.println(randomIndex);
        Resource resource = resourceLoader.getResource("classpath:static/image/" + randomIndex + ".jpg");
        try {
            InputStream inputStream = resource.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] imageBytes = outputStream.toByteArray();
            inputStream.close();
            outputStream.close();
            return new Gson().toJson(new BaseResponse<>(200, new ResponseVO(ResultTypeEnum.IMAGE.getValue(), ResponseEntity.ok()
                                .body(imageBytes))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/food")
    public String getRandomFood(){
        List<String> list = new ArrayList<>();
        list.add("大盘鸡");
        list.add("小包子");
        list.add("西瓜");
        list.add("葱花饼");
        list.add("小掌柜");
        list.add("豆腐汤");
        list.add("牛肉汤");
        list.add("兰州拉面");
        int randomIndex = RandomUtil.randomInt(0,list.size());
        return new Gson().toJson(new BaseResponse<>(200, new ResponseVO(ResultTypeEnum.STRING.getValue(),list.get(randomIndex))));
    }
}
