package com.soulchild.search.common.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soulchild.search.common.model.CommonResponse;
import lombok.Data;

@Data
public class SearchResult<T> extends CommonResponse {
    public SearchResult() {
        super();
    }

    public SearchResult(T result) {
        super();
        this.result = result;
    }

    @JsonIgnore
    private T result;
}