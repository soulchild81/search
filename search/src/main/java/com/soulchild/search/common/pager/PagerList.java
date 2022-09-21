package com.soulchild.search.common.pager;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class PagerList<T> implements Serializable {
    private Pager pager;
    private T list;
}
