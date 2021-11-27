package one.goranson.codegenerationissue;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

/**
 * Logs request processing time to MDC.
 * <p>
 * The request processing time is the time it takes to invoke the controller method and does NOT
 * include the time it takes to deliver the response over the network.
 */
@Aspect
@Component
@RequiredArgsConstructor
class ProcessingTimeAspect {

  @Pointcut("@annotation(requestMapping)")
  void requestMappingMethod(RequestMapping requestMapping) {
    // pointcuts are empty
  }

  @Pointcut("@target(restController)")
  void restController(RestController restController) {
    // pointcuts are empty
  }

  @SuppressWarnings("null")
  @Nullable
  @Around("requestMappingMethod(requestMapping) && restController(restController)")
  Object around(ProceedingJoinPoint joinPoint, RequestMapping requestMapping,
      RestController restController) throws Throwable {
    // Some unrelated code removed
    return joinPoint.proceed();
  }

}
