package com.glitchtako.forum.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glitchtako.forum.annotation.LogActivity;
import com.glitchtako.forum.model.dto.UserDetailsDTO;
import com.glitchtako.forum.model.entity.ActivityLog;
import com.glitchtako.forum.model.entity.User;
import com.glitchtako.forum.repository.ActivityLogRepository;
import com.glitchtako.forum.repository.UserRepository;
import com.glitchtako.forum.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class LogActivityAspect {

  @Autowired private ObjectMapper objectMapper;

  @Autowired private ActivityLogRepository activityLogRepository;

  @Autowired private UserRepository userRepository;

  @Pointcut("@annotation(com.glitchtako.forum.annotation.LogActivity)")
  public void logPointCut() {}

  @Around("logPointCut()")
  public Object around(ProceedingJoinPoint point) throws Throwable {
    long startTime = System.currentTimeMillis();
    Object result = point.proceed();
    long processTime = System.currentTimeMillis() - startTime;

    try {
      saveActivityLog(point, processTime);
    } catch (Exception ex) {
      log.error("Log Activity Fail: {}", ex.getMessage());
    }

    return result;
  }

  private void saveActivityLog(ProceedingJoinPoint joinPoint, long processTime) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    ActivityLog activityLog = new ActivityLog();

    activityLog.setProcessTime(processTime);

    LogActivity logAnnotation = method.getAnnotation(LogActivity.class);
    if (logAnnotation != null) {
      activityLog.setOperation(logAnnotation.title() + "-" + logAnnotation.action());
    }

    String className = joinPoint.getTarget().getClass().getName();
    String methodName = signature.getName();
    activityLog.setMethod(className + "." + methodName);
    try {
      Object[] args = joinPoint.getArgs();
      String params = null;
      if (args.length != 0) {
        params = objectMapper.writeValueAsString(args);
      }
      activityLog.setParameters(params);
    } catch (Exception ex) {
      log.error("Log activity fail: Fail to log param - {}", ex.getMessage());
    }

    try {
      HttpServletRequest request =
          ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
      activityLog.setIp(RequestUtils.getIp(request));
    } catch (Exception ex) {
      log.error("Log activity fail: Fail to log ip - {}", ex.getMessage());
    }

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    try {
      UserDetailsDTO userDetails = (UserDetailsDTO) authentication.getPrincipal();
      User user = userRepository.findById(userDetails.getUserId()).orElse(null);
      activityLog.setUser(user);

    } catch (Exception ex) {
      log.warn("Log activity fail: Fail to log user - {}", ex.getMessage());
      activityLog.setUser(null);
    }
    log.info("system activity log: {}", activityLog);
    this.activityLogRepository.save(activityLog);
  }
}
