package com.soulchild.search.api.service;

import com.soulchild.search.api.model.BlogDto;
import com.soulchild.search.common.Constant;
import com.soulchild.search.common.pager.PagerList;

public interface SearchService {
    PagerList<BlogDto> getBlog(String query , Constant.SEARCH_SORT_PARAMETER sort , int page , int size);
}
