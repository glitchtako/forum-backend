package com.glitchtako.forum.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class PageRequest {

    @Min(0)
    protected long page = 0;

    @Max(50)
    protected long size = 50;

    protected String sortBy;

}
