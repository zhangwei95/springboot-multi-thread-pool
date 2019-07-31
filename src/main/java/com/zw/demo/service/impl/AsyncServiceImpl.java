package com.zw.demo.service.impl;

import com.zw.demo.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Override
    @Async("mySimpleAsync")
    public void executeAsync() {
        logger.info("executeAsync {}",Thread.currentThread().getName());
    }
}
