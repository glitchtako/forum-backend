package com.glitchtako.forum.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.List;

@Data
@Builder
public class PagedDTO<T> {

    private long total;

    private Collection<T> data;

    private long size;

    private long page;
}
