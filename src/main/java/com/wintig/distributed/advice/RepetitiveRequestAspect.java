package com.wintig.distributed.advice;

import com.wintig.distributed.annotation.RepetitiveRequest;
import com.wintig.distributed.constants.RedisKeyConstants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @Description 重复请求限制通知
 *
 * @Author wintig
 * @Create 2018-09-25 上午7:55
 */
@Component
@Aspect
public class RepetitiveRequestAspect {

    @Autowired
    private RedisTemplate redisTemplate;


    @Around(value = "@annotation(com.wintig.distributed.annotation.RepetitiveRequest)")
    public void checkRepetitiveRequest(ProceedingJoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();

        //获取注解参数
        RepetitiveRequest repetitiveRequest = targetMethod.getAnnotation(RepetitiveRequest.class);

        //获得request对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String repetitiveRequestToken = request.getRequestedSessionId();
        String repetitiveRequestLimitKey = RedisKeyConstants.UrlLimit.REPETITIVE_REQUEST_LIMIT + repetitiveRequestToken;

        //判断是否重复请求
        if (repetitiveRequest(repetitiveRequestLimitKey, repetitiveRequest.limitTime())) {
            throw new RuntimeException("请不要重复提交！");
        }

    }

    /**
     * 是否是短时间内的重复请求
     * @param redisKey 重复请求key 业务key:唯一约束字段
     * @param time 限制时间，单位毫秒
     */
    protected boolean repetitiveRequest(String redisKey, long time) {
        long count = redisTemplate.opsForValue().increment(redisKey, 1);
        if (count == 1) {
            redisTemplate.expire(redisKey, time, TimeUnit.MILLISECONDS);
            return false;
        } else {
            return true;
        }
    }

}
