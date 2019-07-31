package com.zw.demo;

import com.zw.demo.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private AsyncService asyncService;


    @RequestMapping("/")
    public String submit(){
        //调用service层的任务
        for(int i=0;i<1000;i++){
            asyncService.executeAsync();
        }
        return "success";
    }
}
