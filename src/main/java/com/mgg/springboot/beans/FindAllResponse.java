package com.mgg.springboot.beans;

import lombok.Data;

import java.util.List;

@Data
public class FindAllResponse {
    private long total;
    private int limit;
    private int skip;
    private List data;

    public FindAllResponse(long total, int limit, int skip, List data) {
        this.total = total;
        this.limit = limit;
        this.skip = skip;
        this.data = data;
    }
}
