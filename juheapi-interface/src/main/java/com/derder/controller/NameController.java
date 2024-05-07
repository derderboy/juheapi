package com.derder.controller;

import com.derder.model.User;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value = "/name")
public class NameController {
    @GetMapping("/")
    public String getNameByGet(String name){
        return "Get 你的名字是：" + name;
    }
    @PostMapping("/")
    public String getNameByPost(@RequestParam String name){
        return "Post 你的名字是：" + name;
    }
    @PostMapping("/user")
    public String getNameByPost(@RequestBody User user){
        return "Post 用户名字是：" + user.getUsername();
    }
}
