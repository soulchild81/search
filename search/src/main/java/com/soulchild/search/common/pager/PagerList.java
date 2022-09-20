package com.soulchild.search.common.pager;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PagerList<T> {
    private Pager pager;
    private T list;
}
