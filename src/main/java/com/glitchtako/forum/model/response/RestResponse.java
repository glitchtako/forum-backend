package com.glitchtako.forum.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestResponse<T> {

    private Long error;

    private boolean success;

    private T data;

    public static <E> RestResponse ok(E data) {
        return RestResponse.builder().data(data).success(true).error(null).build();
    }

    public static RestResponse fail(Long error) {
        return RestResponse.builder().error(error).data(null).success(false).build();
    }
}
