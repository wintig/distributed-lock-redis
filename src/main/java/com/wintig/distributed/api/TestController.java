package com.wintig.distributed.api;

import com.wintig.distributed.annotation.RepetitiveRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 测试controller
 * @Author wintig
 * @Create 2018-09-25 下午8:21
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    @RepetitiveRequest(limitTime = 500)
    public String test(HttpServletRequest request) {
        String id = request.getSession().getId();
        return id;
    }

}
