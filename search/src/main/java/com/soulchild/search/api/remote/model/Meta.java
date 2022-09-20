package com.soulchild.search.api.remote.model;

import lombok.Data;

@Data
public class Meta {
    private Integer total_count;
    private Integer pageable_count;
    private Boolean is_end;
}
