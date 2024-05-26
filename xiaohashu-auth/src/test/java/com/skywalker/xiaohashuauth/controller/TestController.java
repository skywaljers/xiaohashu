package com.skywalker.xiaohashuauth.controller;

import com.skywalker.framework.common.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baijj
 * @date 2024-05-27
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public Response<String> test(){
        return Response.success("skywalker");
    }
}
