/**
 * Copyright (C), 2015-2020, 京东
 * FileName: MethodAspect
 * Author:   huangdan6
 * Date:     2020/11/13 下午4:01
 * Description: 方法切面
 */
package Week_05.spring.aop.annotation;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 *
 * 自定义切面
 *
 * @author huangdan6
 * @date 2020/11/13 16:01
 * @since 1.0.0
 */
@Aspect
@Slf4j
@Component
public class CustomAspect {

    @Around(value = "@annotation(Week_05.spring.aop.annotation.Custom)")
    public void around(ProceedingJoinPoint joinPoint){
        log.info("[CustomAspect] 进入切面！");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        log.info("[RPC调用方法入参]:方法名称{}.{}，参数：{}", method.getDeclaringClass().getName(), method.getName(), args);
        log.info("[CustomAspect] 业务处理！");
        Object returnObject = null;
        try {
            returnObject = joinPoint.proceed();
            log.info("[CustomAspect] returnObject={}", returnObject);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        log.info("[CustomAspect] 退出切面！");
    }
}
