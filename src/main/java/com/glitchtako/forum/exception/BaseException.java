package com.glitchtako.forum.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends Exception implements Serializable {
  protected HttpStatus status;
  protected Long code;
  protected String message;
}
