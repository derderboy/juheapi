package com.derder.controller;

import cn.hutool.extra.template.TemplateException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.derder.mapper.GenerateMapper;
import com.derder.model.entity.Generate;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/strategy")
public class StrategyController {
    @Resource
    private GenerateMapper generateMapper;
    @GetMapping("/generator")
    public String getContext3() throws IOException, freemarker.template.TemplateException {
        // 0.获取要添加的接口信息
        Generate generate = new Generate("Get_Food","/api/food","今天吃什么","FoodStrategy","GET");
        // 1.添加接口进数据库
        // 1.1 查询是否有相同的接口
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("url", generate.getUrl());
        queryWrapper.eq("URLNAME", generate.getUrlname());
        queryWrapper.eq("method", generate.getMethod());
        queryWrapper.eq("strategyName", generate.getStrategyname());
        Generate query = generateMapper.selectOne(queryWrapper);
        if (query != null){
            System.out.println("该接口已存在");
            return "该接口已存在";
        }
        // 1.2 添加接口
        int insert = generateMapper.insert(generate);
        if (insert == 1){
            System.out.println("添加成功");
        }
        // 2.查询所有接口
        List<Generate> generateList = generateMapper.selectList(null);
        for (Generate generate1 : generateList) {
            System.out.println(generate1);
        }
        // 3.生成MyUrl接口
        String projectPath = System.getProperty("user.dir");
        String MyUrl_inputPath = projectPath + File.separator + "juheapi-backend/src/main/resources/templates/MyUrlTemplate.java.ftl";
        String MyUrl_outputPath = projectPath + File.separator + "juheapi-client-sdk/src/main/java/com/derder/constant/MyUrl.java";
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("generateList", generateList);
        doGenerate(MyUrl_inputPath, MyUrl_outputPath, dataMap);
        // 4.生成具体策略类
        String inputPath = projectPath + File.separator + "juheapi-backend/src/main/resources/templates/StrategyTemplate.java.ftl";
        String outputPath = projectPath + File.separator + "juheapi-client-sdk/src/main/java/com/derder/strategy/impl/"+generate.getStrategyname()+".java";
        doGenerate(inputPath, outputPath, generate);
        return "ok";
    }

    /**
     * 生成文件
     *
     * @param inputPath 模板文件输入路径
     * @param outputPath 输出路径
     * @param model 数据模型
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(String inputPath, String outputPath, Object model) throws IOException, freemarker.template.TemplateException {
        // 创建 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 指定模板文件所在的路径
        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        // 创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);

        // 生成
        Writer out = new FileWriter(outputPath);
        template.process(model, out);

        // 生成文件后别忘了关闭哦
        out.close();
    }
}
