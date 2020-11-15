package ru.education.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class WebServiceLogger {

    private static Logger LOG = LoggerFactory.getLogger(WebServiceLogger.class);

    @Pointcut(value = "execution(public * ru.education.service.ProductService.*(..))")
    public void serviceMethod() { }

    @Pointcut("@annotation(ru.education.annotation.Loggable)")
    public void loggableMethod() { }

//    @Around(value = "serviceMethod() && loggableMethod()")
//    public Object logWebServiceCall(ProceedingJoinPoint thisJoinPoint) throws Throwable {
//        String methodName = thisJoinPoint.getSignature().getName();
//        Object[] methodArgs = thisJoinPoint.getArgs();
//
//        LOG.info(String.format("Вызван метод %s с аргументами %s", methodName, Arrays.toString(methodArgs)));
//
//        Object result = thisJoinPoint.proceed();
//
//        LOG.info(String.format("Метод %s вернул %s", methodName, result));
//
//        return result;
//    }

    @Before(value = "loggableMethod()")
    public void logBeforeWebServiceCall(JoinPoint thisJoinPoint) {
        String methodName = thisJoinPoint.getSignature().getName();
        Object[] methodArgs = thisJoinPoint.getArgs();

        LOG.info(String.format("Вызван метод %s с аргументами %s", methodName, Arrays.toString(methodArgs)));
    }

    @AfterReturning(value = "loggableMethod()", returning = "result")
    public void logAfterWebServiceCall(JoinPoint thisJoinPoint, Object result) {
        String methodName = thisJoinPoint.getSignature().getName();

        if (result != null) {
            LOG.info(String.format("Метод %s вернул %s", methodName, result));
        } else {
            LOG.info(String.format("Метод %s вернул null", methodName));
        }
    }
}
