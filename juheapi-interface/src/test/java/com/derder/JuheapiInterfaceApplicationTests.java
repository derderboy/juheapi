package com.derder;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import com.derder.client.JuHeApiClient;
import com.derder.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

@SpringBootTest
class JuheapiInterfaceApplicationTests {

    @Resource
    private JuHeApiClient juHeApiClient;

    @Test
    void contextLoads() {
        User user=new User();
        user.setUsername("derder");
        juHeApiClient.getUsernameByPost(user);
    }

    @Test
    public ResponseEntity<byte[]> getRandomImage() {
        int randomIndex = RandomUtil.randomInt(1,6);
        System.out.println(randomIndex);
        String imageFiles = "E:\\桌面\\api\\juheapi-backend\\juheapi-interface\\src\\main\\resources\\static\\image\\"+randomIndex+".jpg";
        File randomImageFile = new File(imageFiles);
        System.out.println(randomImageFile.exists());
        try {
            byte[] imageBytes = Files.readAllBytes(randomImageFile.toPath());
            System.out.println(Arrays.toString(imageBytes));
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void a(){
        getRandomImage();
    }
}
