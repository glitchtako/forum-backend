package com.glitchtako.forum.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glitchtako.forum.exception.EndpointAccessDeniedException;
import com.glitchtako.forum.model.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  @Autowired private ObjectMapper objectMapper;

  @Override
  public void handle(
      HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException accessDeniedException)
      throws IOException, ServletException {

    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(403);
    EndpointAccessDeniedException exception = new EndpointAccessDeniedException();
    RestResponse<?> body =
        RestResponse.fail(exception.getCode(), exception.getMessage(), exception.getStatus());
    response.getWriter().write(objectMapper.writeValueAsString(body));
  }
}
