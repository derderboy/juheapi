package com.derder;

import cn.hutool.core.util.RandomUtil;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class JuheapiInterfaceApplicationTests {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("大盘鸡");
        list.add("小包子");
        list.add("西瓜");
        list.add("葱花饼");
        list.add("小掌柜");
        list.add("豆腐汤");
        list.add("牛肉汤");
        list.add("兰州拉面");
        System.out.println(list.size());
        System.out.println(list.get(0));
        System.out.println(list.get(RandomUtil.randomInt(0,list.size())));
    }
}
