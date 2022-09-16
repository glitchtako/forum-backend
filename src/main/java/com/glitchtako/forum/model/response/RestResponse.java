package com.glitchtako.forum.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class RestResponse<T> {

    private boolean success;

    private T data;

    private Long error;

    private String errorMessage;

    @JsonIgnore
    private HttpStatus httpStatus;

    public static <E> RestResponse ok(E data) {
        return RestResponse.builder().data(data).success(true).build();
    }

    public static <E> RestResponse ok(E data, HttpStatus status) {
        return RestResponse.builder().data(data).success(true).httpStatus(status).build();
    }

    public static RestResponse fail(Long error) {
        return RestResponse.builder().error(error).data(null).success(false).build();
    }

    public static RestResponse fail(Long error, String errorMessage) {
        return RestResponse.builder().error(error).errorMessage(errorMessage).data(null).success(false).build();
    }

    public static RestResponse fail(Long error, String errorMessage, HttpStatus status) {
        return RestResponse.builder().error(error).errorMessage(errorMessage).data(null).success(false).httpStatus(status).build();
    }
}
