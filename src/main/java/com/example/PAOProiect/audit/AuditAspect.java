package com.example.PAOProiect.audit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {
    @Autowired
    private AuditService auditService;

    @Before("execution(* com.example.PAOProiect.*.*.*(..) )")
    public void logAfterMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String[] params = new String[joinPoint.getArgs().length];
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            params[i] = joinPoint.getArgs()[i].toString();
        }
        auditService.logMethodCall(methodName, params);
    }
}
